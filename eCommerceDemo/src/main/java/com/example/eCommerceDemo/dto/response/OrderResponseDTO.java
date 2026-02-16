package com.example.eCommerceDemo.dto.response;

import com.example.eCommerceDemo.model.PaymentMethod;
import com.example.eCommerceDemo.model.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String orderNumber;
    private PaymentMethod paymentMethod;
    private String address;
    private BigDecimal totalPrice;
    private Status status;
    private List<OrderItemResponseDTO> orderItems;
}
