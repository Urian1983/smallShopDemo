package com.example.eCommerceDemo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "Response containing details of an item in the cart")
public class CartItemResponseDTO {
    @Schema(description = "Unique identifier for the cart item", example = "1")
    private Long id;

    @Schema(description = "Quantity of the product", example = "2")
    private int quantity;

    @Schema(description = "Timestamp when the item was added")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp of the last update")
    private LocalDateTime updatedAt;

    @Schema(description = "Associated product identifier", example = "101")
    private Long productId;

    @Schema(description = "Name of the product", example = "Wireless Keyboard")
    private String productName;

    @Schema(description = "Price of the product at the time of addition", example = "49.99")
    private BigDecimal productPrice;
}

