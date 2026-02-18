package com.example.eCommerceDemo.mapper.cart;

import com.example.eCommerceDemo.dto.request.CartRequestDTO;
import com.example.eCommerceDemo.dto.response.CartResponseDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.model.Cart;

public class CartMapperImpl implements CartMapper {
    @Override
    public CartResponseDTO toDto(Cart cart) {
        if(cart==null){
            throw new NullObjectException();
        }
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        return null;
    }

    @Override
    public Cart toEntity(CartRequestDTO cartRequestDTO) {
        if(cartRequestDTO==null){
            throw new NullObjectException();
        }

        return null;
    }
}
