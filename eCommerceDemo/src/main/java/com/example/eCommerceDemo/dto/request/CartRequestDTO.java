package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {

    @NotNull
    private Long userId;
    @NotNull
    private List<CartItemRequestDTO> items;
}
