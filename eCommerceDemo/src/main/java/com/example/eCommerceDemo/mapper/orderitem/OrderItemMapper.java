package com.example.eCommerceDemo.mapper.orderitem;

import com.example.eCommerceDemo.dto.request.OrderItemRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderItemResponseDTO;
import com.example.eCommerceDemo.model.Order;
import com.example.eCommerceDemo.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public interface OrderItemMapper {

    OrderItemResponseDTO toDTO(OrderItem orderItem);
    OrderItem toEntity(OrderItemRequestDTO dto);
}
