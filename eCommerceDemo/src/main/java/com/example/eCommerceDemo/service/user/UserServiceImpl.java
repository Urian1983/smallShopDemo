package com.example.eCommerceDemo.service.user;

import com.example.eCommerceDemo.dto.request.LoginRequestDTO;
import com.example.eCommerceDemo.dto.request.RegisterRequestDTO;
import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.AuthResponseDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.model.User;
import com.example.eCommerceDemo.repository.UserRepository;
import com.example.eCommerceDemo.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Optional<User> user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        return null;
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequest) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        if(!userRepository. existsById(userId)) {
            throw new NullObjectException();
        }
        userRepository.deleteById(userId);
    }
}
