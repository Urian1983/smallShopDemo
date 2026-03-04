package com.example.eCommerceDemo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Shipping and identification details for an order")
public class OrderRequestDTO {

    @NotBlank
    @Length(min=1, max=100)
    @Schema(description = "Unique order reference number", example = "ORD-2024-99AC", minLength = 1, maxLength = 100)
    private String orderNumber;

    @NotBlank
    @Schema(description = "Payment method", example = "CREDIT_CARD")
    private String paymentMethod;

    @NotBlank
    @Length(min=1, max=500)
    @Schema(description = "Full shipping address", example = "123 Maple Street, Apt 4B", maxLength = 500)
    private String address;

    @NotBlank
    @Schema(description = "Area or postal code", example = "28001")
    private String postalCode;

    @NotBlank
    @Schema(description = "Destination country", example = "Spain")
    private String country;
}
