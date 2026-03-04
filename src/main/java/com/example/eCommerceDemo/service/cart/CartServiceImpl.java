package com.example.eCommerceDemo.service.cart;

import com.example.eCommerceDemo.dto.request.CartItemRequestDTO;
import com.example.eCommerceDemo.dto.response.CartResponseDTO;
import com.example.eCommerceDemo.exceptions.NotFoundException;
import com.example.eCommerceDemo.mapper.cart.CartMapper;
import com.example.eCommerceDemo.mapper.cartItem.CartItemMapper;
import com.example.eCommerceDemo.model.Cart;
import com.example.eCommerceDemo.model.CartItem;
import com.example.eCommerceDemo.model.Product;
import com.example.eCommerceDemo.repository.CartRepository;
import com.example.eCommerceDemo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Transactional
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper, CartItemMapper cartItemMapper, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
        this.productRepository = productRepository;
    }

    @Override
    public CartResponseDTO getCart(Long userId) {
        log.info("Fetching cart for user ID: {}", userId);
        Cart cartToGet = cartRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Cart not found for user ID: {}", userId);
                    return new NotFoundException();
                });

        return cartMapper.toDto(cartToGet);
    }

    @Override
    public CartResponseDTO addItem(CartItemRequestDTO cartItemRequestDTO, Long userId) {
        log.info("Adding product ID: {} to cart for user ID: {}", cartItemRequestDTO.getProductId(), userId);
        Cart cartToGet = cartRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Cart not found for user ID: {}", userId);
                    return new NotFoundException();
                });

        Product product = productRepository.findById(cartItemRequestDTO.getProductId())
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", cartItemRequestDTO.getProductId());
                    return new NotFoundException();
                });

        CartItem cartItem = cartItemMapper.toEntity(cartItemRequestDTO);
        cartItem.setCart(cartToGet);
        cartToGet.getCartItems().add(cartItem);

        cartItem.setProduct(product);

        cartRepository.save(cartToGet);
        log.info("Product successfully added to cart for user ID: {}", userId);
        return cartMapper.toDto(cartToGet);
    }

    @Override
    public CartResponseDTO updateItem(CartItemRequestDTO cartItemRequestDTO, Long userId) {
        log.info("Updating item quantity for product ID: {} in user ID: {} cart", cartItemRequestDTO.getProductId(), userId);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Cart not found for user ID: {}", userId);
                    return new NotFoundException();
                });

        CartItem itemToUpdate = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(cartItemRequestDTO.getProductId()))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Item with product ID: {} not found in cart", cartItemRequestDTO.getProductId());
                    return new NotFoundException();
                });

        itemToUpdate.setQuantity(cartItemRequestDTO.getQuantity());
        itemToUpdate.setUpdatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);

        log.info("Cart item updated successfully");
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDTO removeItem(Long cartItemId, Long userId) {
        log.info("Removing cart item ID: {} from user ID: {} cart", cartItemId, userId);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Cart not found for user ID: {}", userId);
                    return new NotFoundException();
                });

        CartItem itemToRemove = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Cart item ID: {} not found in cart", cartItemId);
                    return new NotFoundException();
                });

        cart.getCartItems().remove(itemToRemove);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);

        log.info("Item removed successfully from cart");
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDTO clearCart(Long userId) {
        log.info("Clearing all items from cart for user ID: {}", userId);
        Cart cartToClear = cartRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Cart not found for user ID: {}", userId);
                    return new NotFoundException();
                });

        cartToClear.getCartItems().clear();
        cartToClear.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cartToClear);

        log.info("Cart cleared successfully for user ID: {}", userId);
        return cartMapper.toDto(cartToClear);
    }
}

