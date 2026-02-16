package com.example.eCommerceDemo.mapper;

import com.example.eCommerceDemo.dto.response.CartResponseDTO;
import com.example.eCommerceDemo.model.Cart;
import org.springframework.stereotype.Component;

@Component
public interface CartMapper {
    public CartResponseDTO toDTO(Cart cart);

    public Cart toEntity(CartResponseDTO dto);
}
