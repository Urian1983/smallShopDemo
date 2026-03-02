package com.example.eCommerceDemo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details of an item within the shopping cart")
public class CartItemRequestDTO {

    @NotNull
    @Schema(description = "Number of units for this product", example = "2", minimum = "1")
    private int quantity;

    @NotNull
    @Schema(description = "Unique identifier of the product", example = "101")
    private Long productId;

    @NotBlank
    @Schema(description = "Name of the product at the time of adding", example = "Wireless Mouse")
    private String productName;

    @NotNull
    @Schema(description = "Unit price of the product", example = "25.50")
    private BigDecimal productPrice;
}
