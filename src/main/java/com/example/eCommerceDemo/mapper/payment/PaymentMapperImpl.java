package com.example.eCommerceDemo.mapper.payment;

import com.example.eCommerceDemo.dto.request.PaymentRequestDTO;
import com.example.eCommerceDemo.dto.response.PaymentResponseDTO;
import com.example.eCommerceDemo.model.Payment;
import com.example.eCommerceDemo.model.PaymentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapperImpl implements PaymentMapper {
    @Override
    public PaymentResponseDTO toDTO(Payment payment) {
        if(payment==null){
            throw new NullPointerException("Data is null");
        }

        PaymentResponseDTO dto = new PaymentResponseDTO();

        dto.setOrderId(payment.getOrder().getId());
        dto.setUserId(payment.getUser().getId());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setProcessedAt(LocalDateTime.now());

        return dto;
    }

    @Override
    public Payment toEntity(PaymentRequestDTO paymentRequestDTO) {
        if(paymentRequestDTO==null){
            throw new NullPointerException("Data is null");
        }

        Payment payment = new Payment();

        payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setProcessedAt(LocalDateTime.now());

        return payment;
    }
}
