package com.example.eCommerceDemo.service.cart;

import com.example.eCommerceDemo.dto.response.CartResponseDTO;

public interface CartService {

    CartResponseDTO getCart();
    CartResponseDTO addItem(Long cartId);
    CartResponseDTO updateItem(Long cartId);
    CartResponseDTO deleteItem(Long cartId);
    CartResponseDTO clearCart(Long cartId);
}
