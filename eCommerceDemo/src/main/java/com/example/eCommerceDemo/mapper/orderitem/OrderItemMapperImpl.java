package com.example.eCommerceDemo.mapper.orderitem;

import com.example.eCommerceDemo.dto.request.OrderItemRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderItemResponseDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.model.Order;
import com.example.eCommerceDemo.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapperImpl implements OrderItemMapper {
    @Override
    public OrderItemResponseDTO toDTO(OrderItem orderItem) {
        if(orderItem==null){
            throw new NullObjectException();
        }
        return null;
    }

    @Override
    public Order toEntity(OrderItemRequestDTO dto) {
        if(dto==null){
            throw new NullObjectException();
        }
        return null;
    }
}
