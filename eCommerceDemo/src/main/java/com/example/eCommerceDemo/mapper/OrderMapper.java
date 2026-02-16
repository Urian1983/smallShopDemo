package com.example.eCommerceDemo.mapper;

import com.example.eCommerceDemo.dto.response.OrderResponseDTO;
import com.example.eCommerceDemo.model.Order;
import org.springframework.stereotype.Component;

@Component
public interface OrderMapper {

    public OrderResponseDTO toDTO(Order order);

    public Order toEntity(OrderResponseDTO dto);
}
