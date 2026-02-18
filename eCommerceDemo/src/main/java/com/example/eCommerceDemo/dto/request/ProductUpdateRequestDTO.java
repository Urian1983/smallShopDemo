package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequestDTO {

   @NotBlank
    private String sku;
   @NotBlank
    private String slug;
   @NotBlank
    private String name;
   @NotBlank
   private String brand;
   @NotBlank
    private String description;
   @NotBlank
    private String shortDescription;
   @NotBlank
    private int stock;
   @NotBlank
    private BigDecimal price;


}
