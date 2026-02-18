package com.example.eCommerceDemo.mapper.user;

import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import com.example.eCommerceDemo.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    UserResponseDTO toDTO(User user);
    User toEntity(UserRequestDTO dto);
}
