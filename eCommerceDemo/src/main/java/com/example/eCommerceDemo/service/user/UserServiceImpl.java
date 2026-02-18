package com.example.eCommerceDemo.service.user;

import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.AuthResponseDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;

public class UserServiceImpl implements UserService {

    @Override
    public AuthResponseDTO login(String email, String password) {
        return null;
    }

    @Override
    public AuthResponseDTO register(String email, String password) {
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

    }
}
