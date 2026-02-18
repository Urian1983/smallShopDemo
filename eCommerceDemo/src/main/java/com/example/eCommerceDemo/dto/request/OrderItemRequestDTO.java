package com.example.eCommerceDemo.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderItemRequestDTO {

    @NotBlank
    private int name;
    @NotBlank
    private int quantity;
}


