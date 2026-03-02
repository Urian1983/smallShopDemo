package com.example.eCommerceDemo.dto.response;

import com.example.eCommerceDemo.model.PaymentMethod;
import com.example.eCommerceDemo.model.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Response containing payment transaction details")
public class PaymentResponseDTO {
    @Schema(description = "Identifier of the associated order", example = "10")
    private Long orderId;

    @Schema(description = "Identifier of the user who made the payment", example = "1")
    private Long userId;

    @Schema(description = "Payment method used")
    private PaymentMethod paymentMethod;

    @Schema(description = "Current status of the payment")
    private PaymentStatus status;

    @Schema(description = "Timestamp when the payment was processed")
    private LocalDateTime processedAt;
}
