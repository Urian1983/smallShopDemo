package com.example.eCommerceDemo.dto.request;

import com.example.eCommerceDemo.model.Order;
import com.example.eCommerceDemo.model.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequestDTO {
    @NotNull
    private Order order;
    @NotNull
    private PaymentMethod paymentMethod;
}
