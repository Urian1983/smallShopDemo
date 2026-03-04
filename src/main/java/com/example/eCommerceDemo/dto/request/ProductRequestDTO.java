package com.example.eCommerceDemo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Product creation or update details")
public class ProductRequestDTO {

    @NotBlank
    @Schema(description = "Unique Stock Keeping Unit", example = "TSHIRT-BLUE-L")
    private String sku;

    @NotBlank
    @Schema(description = "Display name of the product", example = "Cotton Blue T-Shirt")
    private String name;

    @NotBlank
    @Schema(description = "Brand or manufacturer", example = "Nike")
    private String brand;

    @Schema(description = "Full URL for the product image", example = "https://images.com/products/tshirt.jpg")
    private String imageUrl;

    @NotBlank
    @Schema(description = "Detailed product information", example = "100% organic cotton, breathable fabric.")
    private String description;

    @NotBlank
    @Schema(description = "Brief summary of the product", example = "Organic cotton blue shirt")
    private String shortDescription;

    @NotBlank
    @Schema(description = "Product category name", example = "Apparel")
    private String category;

    @NotNull
    @Min(value = 0)
    @Schema(description = "Available units in warehouse", example = "150", minimum = "0")
    private int stock;

    @NotNull
    @Min(value = 0)
    @Schema(description = "Unit price of the product", example = "29.99", minimum = "0")
    private BigDecimal price;
}
