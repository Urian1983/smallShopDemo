package com.example.eCommerceDemo.service.cart;

import com.example.eCommerceDemo.dto.request.CartItemRequestDTO;
import com.example.eCommerceDemo.dto.request.CartRequestDTO;
import com.example.eCommerceDemo.dto.response.CartResponseDTO;

public interface CartService {

    CartResponseDTO getCart(Long userId);
    CartResponseDTO addItem(CartItemRequestDTO cartItemRequestDTO, Long userId);
    CartResponseDTO updateItem(CartItemRequestDTO cartItemRequestDTO, Long userId);
    CartResponseDTO removeItem(Long cartItemId, Long userId);
    CartResponseDTO clearCart(Long userId);
}
