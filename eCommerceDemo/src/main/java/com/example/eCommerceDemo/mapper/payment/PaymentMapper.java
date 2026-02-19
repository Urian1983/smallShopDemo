package com.example.eCommerceDemo.mapper.payment;

import com.example.eCommerceDemo.dto.request.PaymentRequestDTO;
import com.example.eCommerceDemo.dto.response.PaymentResponseDTO;
import com.example.eCommerceDemo.model.Payment;

public interface PaymentMapper {

    PaymentResponseDTO toDTO (Payment payment);
    Payment toEntity(PaymentRequestDTO paymentRequestDTO);
}
