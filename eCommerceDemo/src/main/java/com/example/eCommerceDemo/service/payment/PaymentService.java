package com.example.eCommerceDemo.service.payment;

import com.example.eCommerceDemo.dto.request.PaymentRequestDTO;
import com.example.eCommerceDemo.dto.response.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO, Long userId);
    PaymentResponseDTO getPaymentByOrderId(Long orderId);
}
