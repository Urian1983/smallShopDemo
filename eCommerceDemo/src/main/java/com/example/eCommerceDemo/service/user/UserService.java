package com.example.eCommerceDemo.service.user;

import com.example.eCommerceDemo.dto.request.LoginRequestDTO;
import com.example.eCommerceDemo.dto.request.RegisterRequestDTO;
import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.AuthResponseDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import org.springframework.stereotype.Service;

public interface UserService {
    AuthResponseDTO login(LoginRequestDTO request);
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);
    UserResponseDTO getUserById(Long userId);
    UserResponseDTO updateUser(Long userId, UserRequestDTO userRequest);
    void deleteUser(Long userId);
}
