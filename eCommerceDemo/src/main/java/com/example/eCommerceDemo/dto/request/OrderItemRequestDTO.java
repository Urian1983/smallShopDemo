package com.example.eCommerceDemo.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequestDTO {

    @NotNull
    private int name;
    @NotNull
    private int quantity;
}


