package com.example.eCommerceDemo.service.user;

import com.example.eCommerceDemo.dto.request.LoginRequestDTO;
import com.example.eCommerceDemo.dto.request.RegisterRequestDTO;
import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.AuthResponseDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import com.example.eCommerceDemo.exceptions.NotFoundException;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.exceptions.UserAlreadyExistsException;
import com.example.eCommerceDemo.mapper.user.UserMapper;
import com.example.eCommerceDemo.model.Cart;
import com.example.eCommerceDemo.model.Role;
import com.example.eCommerceDemo.model.User;
import com.example.eCommerceDemo.repository.CartRepository;
import com.example.eCommerceDemo.repository.UserRepository;
import com.example.eCommerceDemo.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final CartRepository cartRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserMapper userMapper, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.cartRepository = cartRepository;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        log.info("Attempting login for user: {}", request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("Login failed: User not found with email {}", request.getEmail());
                    return new NotFoundException();
                });

        String token = jwtService.generateToken(user);
        log.info("User {} successfully authenticated", request.getEmail());

        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        log.info("Starting registration for email: {}", registerRequestDTO.getEmail());

        if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            log.warn("Registration failed: Email {} already exists", registerRequestDTO.getEmail());
            throw new UserAlreadyExistsException();
        }

        if (!registerRequestDTO.getPassword().equals(registerRequestDTO.getConfirmPassword())) {
            log.warn("Registration failed: Passwords do not match for email {}", registerRequestDTO.getEmail());
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = new User();
        user.setEmail(registerRequestDTO.getEmail());
        user.setName(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(Set.of(Role.USER));
        userRepository.save(user);

        log.debug("User entity saved. Creating default cart for user ID: {}", user.getId());

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotalPrice(BigDecimal.ZERO);
        cart.setCartItems(new ArrayList<>());
        cartRepository.save(cart);

        String token = jwtService.generateToken(user);
        log.info("Registration successful for user: {}", registerRequestDTO.getEmail());

        return AuthResponseDTO.builder().token(token).build();
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        log.debug("Fetching user profile for ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", userId);
                    return new NotFoundException();
                });

        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequest) {
        log.info("Updating profile for user ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("Update failed: User ID {} not found", userId);
                    return new NotFoundException();
                });

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        log.info("User ID: {} updated successfully", userId);

        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        log.warn("Attempting to delete user ID: {}", userId);
        if(!userRepository.existsById(userId)) {
            log.error("Delete failed: User ID {} does not exist", userId);
            throw new NullObjectException();
        }
        userRepository.deleteById(userId);
        log.info("User ID: {} deleted successfully", userId);
    }
}
