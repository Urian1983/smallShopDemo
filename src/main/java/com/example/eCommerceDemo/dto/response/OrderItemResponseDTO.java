package com.example.eCommerceDemo.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponseDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal price;
}
