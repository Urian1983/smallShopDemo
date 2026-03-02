package com.example.eCommerceDemo.dto.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Response representing the current state of a shopping cart")
public class CartResponseDTO {

    @Schema(description = "Unique identifier of the cart", example = "1")
    private Long id;

    @Schema(description = "Timestamp when the cart was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp of the last update to the cart")
    private LocalDateTime updatedAt;

    @ArraySchema(schema = @Schema(description = "List of items currently in the cart"))
    private List<CartItemResponseDTO> cartItems = new ArrayList<>();
}
