package com.example.eCommerceDemo.dto.request;

import com.example.eCommerceDemo.model.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Payment processing information")
public class PaymentRequestDTO {

    @NotNull
    @Schema(description = "Internal ID of the order to be paid", example = "5501", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long orderId;

    @NotNull
    @Schema(description = "The chosen payment method", example = "CREDIT_CARD", requiredMode = Schema.RequiredMode.REQUIRED)
    private PaymentMethod paymentMethod;
}
