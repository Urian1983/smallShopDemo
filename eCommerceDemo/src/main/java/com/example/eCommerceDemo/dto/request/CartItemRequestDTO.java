package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDTO {

    @NotNull
    private int quantity;
    @NotNull
    private Long productId;
    @NotBlank
    private String productName;
    @NotNull
    private BigDecimal productPrice;
}
