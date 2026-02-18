package com.example.eCommerceDemo.mapper.order;

import com.example.eCommerceDemo.dto.request.OrderRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderResponseDTO;
import com.example.eCommerceDemo.model.Order;
import org.springframework.stereotype.Component;

@Component
public interface OrderMapper {

    OrderResponseDTO toDto(Order order);
    Order toEntity(OrderRequestDTO orderRequestDTO);
}
