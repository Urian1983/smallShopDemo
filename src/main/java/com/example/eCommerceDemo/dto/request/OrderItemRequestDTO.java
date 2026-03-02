package com.example.eCommerceDemo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Product and quantity selection for an order line")
public class OrderItemRequestDTO {

    @NotNull
    @Schema(description = "ID of the product to order", example = "45")
    private Long productId;

    @Min(1)
    @Schema(description = "Quantity requested", example = "1", minimum = "1")
    private int quantity;
}


