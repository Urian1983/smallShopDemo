package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDTO {

    @NotBlank
    private Long productId;
    @NotBlank
    private int quantity;
}
