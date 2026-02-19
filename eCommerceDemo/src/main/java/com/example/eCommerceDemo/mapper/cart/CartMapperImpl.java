package com.example.eCommerceDemo.mapper.cart;

import com.example.eCommerceDemo.dto.request.CartRequestDTO;
import com.example.eCommerceDemo.dto.response.CartResponseDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.mapper.cartItem.CartItemMapper;
import com.example.eCommerceDemo.mapper.cartItem.CartItemMapperImpl;
import com.example.eCommerceDemo.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapperImpl implements CartMapper {
    @Override
    public CartResponseDTO toDto(Cart cart) {
        if (cart == null) throw new NullObjectException();

        CartResponseDTO dto = new CartResponseDTO();
        dto.setId(cart.getId());
        dto.setCreatedAt(cart.getCreatedAt());
        dto.setUpdatedAt(cart.getUpdatedAt());
        CartItemMapper cartItemMapper = new CartItemMapperImpl();
        dto.setCartItems(
                cart.getCartItems().stream()
                        .map(cartItemMapper::toDto)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    @Override
    public Cart toEntity(CartRequestDTO cartRequestDTO) {
        if (cartRequestDTO == null) {
            throw new NullObjectException();
        }

        Cart cart = new Cart();
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());

        return cart;
    }
}
