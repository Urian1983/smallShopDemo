package com.example.eCommerceDemo.mapper.cartItem;

import com.example.eCommerceDemo.dto.request.CartItemRequestDTO;
import com.example.eCommerceDemo.dto.response.CartItemResponseDTO;
import com.example.eCommerceDemo.model.CartItem;
import org.springframework.stereotype.Component;

public interface CartItemMapper {
    CartItemResponseDTO toDto(CartItem cartItem);
    CartItem toEntity(CartItemRequestDTO dto);
}
