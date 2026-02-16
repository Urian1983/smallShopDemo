package com.example.eCommerceDemo.dto.request;

import com.example.eCommerceDemo.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class OrderRequestDTO {

    @NotNull
    @NotBlank
    private PaymentMethod paymentMethod;

    @NotNull
    @NotBlank
    @Length(min=1,max=1000)
    private String address;
}
