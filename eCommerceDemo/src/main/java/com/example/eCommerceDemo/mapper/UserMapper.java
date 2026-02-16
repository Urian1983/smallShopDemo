package com.example.eCommerceDemo.mapper;


import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;
import com.example.eCommerceDemo.model.User;

@Component
public interface UserMapper {

    public UserResponseDTO toDTO(User user);

    public User toEntity(UserResponseDTO dto);
}
