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
    private String orderNumber;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String address;
    private String postalCode;
    private String country;
    private PaymentMethod paymentMethod;
    private BigDecimal totalPrice;
    private UserSummaryDTO userSummaryDTO;
    private List<OrderItemResponseDTO> orderItems;
}
