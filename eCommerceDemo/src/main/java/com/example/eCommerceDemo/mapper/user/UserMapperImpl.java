package com.example.eCommerceDemo.mapper.user;

import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public UserResponseDTO toDTO(User user) {
        if(user == null){
            throw new NullObjectException();
        }
        return null;
    }

    @Override
    public User toEntity(UserRequestDTO dto) {
        if(dto == null){
            throw new NullObjectException();
        }
        return null;
    }
}
