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

        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
        cartItemResponseDTO.setId(cartItem.getId());
        cartItemResponseDTO.setQuantity(cartItem.getQuantity());
        cartItemResponseDTO.setCreatedAt(cartItem.getCreatedAt());
        cartItemResponseDTO.setUpdatedAt(cartItem.getUpdatedAt());
        cartItemResponseDTO.setProductId(cartItem.getProduct().getId());
        cartItemResponseDTO.setProductName(cartItem.getProduct().getName());
        cartItemResponseDTO.setProductPrice(cartItem.getProduct().getPrice());

        return cartItemResponseDTO;
    }

    @Override
    public CartItem toEntity(CartItemRequestDTO dto) {
        if(dto == null){
            throw new NullObjectException();
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(dto.getQuantity());
        return cartItem;
    }
}
