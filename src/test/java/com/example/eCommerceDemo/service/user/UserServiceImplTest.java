package com.example.eCommerceDemo.service.user;

import com.example.eCommerceDemo.dto.request.LoginRequestDTO;
import com.example.eCommerceDemo.dto.request.RegisterRequestDTO;
import com.example.eCommerceDemo.dto.response.AuthResponseDTO;
import com.example.eCommerceDemo.exceptions.NotFoundException;
import com.example.eCommerceDemo.exceptions.UserAlreadyExistsException;
import com.example.eCommerceDemo.mapper.user.UserMapper;
import com.example.eCommerceDemo.model.Cart;
import com.example.eCommerceDemo.model.User;
import com.example.eCommerceDemo.repository.CartRepository;
import com.example.eCommerceDemo.repository.UserRepository;
import com.example.eCommerceDemo.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserMapper userMapper;
    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private RegisterRequestDTO registerRequest;
    private User user;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequestDTO(
                "testuser",
                "test@example.com",
                "password123",
                "password123"
        );

        user = new User();
        user.setEmail("test@example.com");
        user.setName("testuser");
    }

    @Test
    @DisplayName("Register: Should successfully register a new user and create a cart")
    void register_Success() {
        // Arrange
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("mock-jwt-token");

        // Act
        AuthResponseDTO response = userService.register(registerRequest);

        // Assert
        assertNotNull(response);
        assertEquals("mock-jwt-token", response.getToken());

        // Verify interactions
        verify(userRepository).save(any(User.class));
        verify(cartRepository).save(any(Cart.class));
        verify(jwtService).generateToken(any(User.class));
    }

    @Test
    @DisplayName("Register: Should throw exception if email already exists")
    void register_ThrowsUserAlreadyExists() {
        // Arrange
        when(userRepository.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.register(registerRequest);
        });

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Register: Should throw exception if passwords do not match")
    void register_PasswordsDoNotMatch() {
        // Arrange
        registerRequest.setConfirmPassword("differentPassword");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register(registerRequest);
        });

        assertEquals("Passwords do not match", exception.getMessage());
    }

    @Test
    @DisplayName("Login: Should successfully authenticate and return token")
    void login_Success() {
        // Arrange
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@example.com", "password123");
        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("mock-jwt-token");

        // Act
        AuthResponseDTO response = userService.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals("mock-jwt-token", response.getToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    @DisplayName("Login: Should throw exception if user not found after authentication")
    void login_UserNotFound() {
        // Arrange
        LoginRequestDTO loginRequest = new LoginRequestDTO("wrong@example.com", "password123");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            userService.login(loginRequest);
        });
    }
}