package com.example.eCommerceDemo.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartItemResponseDTO {
    private Long id;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
}
