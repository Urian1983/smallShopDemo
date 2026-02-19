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
public class ProductUpdateRequestDTO {

   @NotBlank
    private String sku;
   @NotBlank
    private String name;
   @NotBlank
   private String brand;
   @NotBlank
    private String description;
   @NotBlank
    private String shortDescription;
   @NotBlank
   private String category;
   @NotNull
    private int stock;
   @NotNull
    private BigDecimal price;
}
