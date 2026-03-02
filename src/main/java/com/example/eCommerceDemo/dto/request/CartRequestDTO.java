package com.example.eCommerceDemo.dto.request;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Shopping cart update request")
public class CartRequestDTO {

    @NotNull
    @Schema(description = "Owner of the shopping cart", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @NotNull
    @ArraySchema(schema = @Schema(description = "List of items in the cart"))
    private List<CartItemRequestDTO> items;
}
