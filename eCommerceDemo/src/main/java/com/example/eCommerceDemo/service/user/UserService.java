package com.example.eCommerceDemo.service.user;

import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.AuthResponseDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AuthResponseDTO login(String email, String password);
    AuthResponseDTO register(String email, String password);
    UserResponseDTO getUserById(Long userId);
    UserResponseDTO updateUser(Long userId, UserRequestDTO userRequest); // Cambiado Auth por User DTO
    void deleteUser(Long userId);
}
