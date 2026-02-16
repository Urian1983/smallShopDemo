package com.example.eCommerceDemo.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartResponseDTO {


    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal totalPrice;
    List<CartItemResponseDTO> cartItems = new ArrayList<>();

}
