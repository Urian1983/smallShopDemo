package com.example.eCommerceDemo.dto.response;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartResponseDTO {


    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItemResponseDTO> cartItems = new ArrayList<>();

}
