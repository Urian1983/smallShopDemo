package com.example.eCommerceDemo.controller;

import com.example.eCommerceDemo.dto.request.CartItemRequestDTO;
import com.example.eCommerceDemo.dto.response.CartResponseDTO;
import com.example.eCommerceDemo.model.User;
import com.example.eCommerceDemo.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Endpoints used for cart management")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Get current user's cart")
    @ApiResponse(responseCode = "200", description = "Cart retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)))
    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.getCart(user.getId()));
    }

    @Operation(summary = "Add item to cart")
    @ApiResponse(responseCode = "201", description = "Item added to cart successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)))
    @PostMapping("/items")
    public ResponseEntity<CartResponseDTO> addItem(@AuthenticationPrincipal User user,
                                                   @Valid @RequestBody CartItemRequestDTO cartItemRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.addItem(cartItemRequestDTO, user.getId()));
    }

    @Operation(summary = "Update cart item")
    @ApiResponse(responseCode = "200", description = "Cart item updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)))
    @PutMapping("/items")
    public ResponseEntity<CartResponseDTO> updateItem(@AuthenticationPrincipal User user,
                                                      @Valid @RequestBody CartItemRequestDTO cartItemRequestDTO) {
        return ResponseEntity.ok(cartService.updateItem(cartItemRequestDTO, user.getId()));
    }

    @Operation(summary = "Remove item from cart")
    @ApiResponse(responseCode = "200", description = "Item removed successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)))
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> removeItem(@AuthenticationPrincipal User user,
                                                      @PathVariable Long cartItemId) {
        return ResponseEntity.ok(cartService.removeItem(cartItemId, user.getId()));
    }

    @Operation(summary = "Clear cart")
    @ApiResponse(responseCode = "200", description = "Cart cleared successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)))
    @DeleteMapping
    public ResponseEntity<CartResponseDTO> clearCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.clearCart(user.getId()));
    }
}