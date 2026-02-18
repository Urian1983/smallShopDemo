package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    @NotBlank
    private String description;

    @NotBlank
    private String shortDescription;
    @NotBlank
    private String brand;
    @NotBlank
    private String category;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private int Stock;
}
