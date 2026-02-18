package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {

    @NotBlank
    private Long userId;
    @NotBlank
    private List<CartItemRequestDTO> items;
}
