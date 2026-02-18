package com.example.eCommerceDemo.mapper.cart;

import com.example.eCommerceDemo.dto.request.CartRequestDTO;
import com.example.eCommerceDemo.dto.response.CartResponseDTO;
import com.example.eCommerceDemo.model.Cart;
import org.springframework.stereotype.Component;

@Component
public interface CartMapper {

    CartResponseDTO toDto(Cart cart);

    Cart toEntity(CartRequestDTO cartRequestDTO);
}
