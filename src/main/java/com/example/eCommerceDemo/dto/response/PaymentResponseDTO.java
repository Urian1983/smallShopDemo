package com.example.eCommerceDemo.dto.response;

import com.example.eCommerceDemo.model.PaymentMethod;
import com.example.eCommerceDemo.model.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponseDTO {
    private Long orderId;
    private Long userId;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime processedAt;
}
