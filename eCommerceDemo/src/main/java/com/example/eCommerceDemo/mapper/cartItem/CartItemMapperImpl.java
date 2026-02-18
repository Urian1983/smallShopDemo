package com.example.eCommerceDemo.mapper.cartItem;

import com.example.eCommerceDemo.dto.request.CartItemRequestDTO;
import com.example.eCommerceDemo.dto.response.CartItemResponseDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapperImpl implements CartItemMapper {
    @Override
    public CartItemResponseDTO toDto(CartItem cartItem) {

        if(cartItem == null){
            throw new NullObjectException();
        }
        return null;
    }

    @Override
    public CartItem toEntity(CartItemRequestDTO dto) {
        if(dto == null){
            throw new NullObjectException();
        }
        return null;
    }
}
