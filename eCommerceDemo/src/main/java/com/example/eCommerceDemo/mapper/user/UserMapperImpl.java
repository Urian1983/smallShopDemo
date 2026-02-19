package com.example.eCommerceDemo.mapper.user;

import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public UserResponseDTO toDTO(User user) {
        if(user == null){
            throw new NullPointerException("Data cannot be null");
        }
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRoles(user.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet()));
        dto.setEnabled(user.isEnabled());
        dto.setLocked(user.isLocked());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    @Override
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) throw new NullObjectException();

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());

        return user;
    }
}
