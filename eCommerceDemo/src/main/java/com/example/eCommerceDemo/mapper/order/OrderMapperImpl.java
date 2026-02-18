package com.example.eCommerceDemo.mapper.order;

import com.example.eCommerceDemo.dto.request.OrderRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderResponseDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper{
    @Override
    public OrderResponseDTO toDto(Order order) {
        if(order==null){
            throw new NullObjectException();
        }
        return null;
    }

    @Override
    public Order toEntity(OrderRequestDTO orderRequestDTO) {
        if(orderRequestDTO==null){
            throw new NullObjectException();
        }
        return null;
    }
}
