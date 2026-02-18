package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    @NotBlank
    private String brand;
    @NotBlank
    private String category;
}
