package com.example.eCommerceDemo.service.payment;

import com.example.eCommerceDemo.dto.request.PaymentRequestDTO;
import com.example.eCommerceDemo.dto.response.PaymentResponseDTO;
import com.example.eCommerceDemo.exceptions.NotFoundException;
import com.example.eCommerceDemo.mapper.payment.PaymentMapper;
import com.example.eCommerceDemo.model.Order;
import com.example.eCommerceDemo.model.Payment;
import com.example.eCommerceDemo.model.PaymentStatus;
import com.example.eCommerceDemo.repository.OrderRepository;
import com.example.eCommerceDemo.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO, Long userId) {
        log.info("Processing payment for Order ID: {} by User ID: {}", paymentRequestDTO.getOrderId(), userId);

        Order order = orderRepository.findById(paymentRequestDTO.getOrderId())
                .orElseThrow(() -> {
                    log.error("Payment failed: Order ID {} not found", paymentRequestDTO.getOrderId());
                    return new NotFoundException();
                });

        Payment payment = paymentMapper.toEntity(paymentRequestDTO);
        payment.setOrder(order);
        payment.setUser(order.getUser());
        payment.setStatus(PaymentStatus.APPROVED);

        orderRepository.save(order);
        paymentRepository.save(payment);

        log.info("Payment approved and saved for Order ID: {}. Payment ID: {}", order.getId(), payment.getId());
        return paymentMapper.toDTO(payment);
    }

    @Override
    public PaymentResponseDTO getPaymentByOrderId(Long orderId) {
        log.info("Retrieving payment details for Order ID: {}", orderId);

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> {
                    log.error("Payment lookup failed: No record found for Order ID {}", orderId);
                    return new NotFoundException();
                });

        return paymentMapper.toDTO(payment);
    }
}