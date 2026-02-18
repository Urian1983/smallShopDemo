package com.example.eCommerceDemo.service.order;

import com.example.eCommerceDemo.dto.request.OrderRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderResponseDTO;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderResponseDTO createOrder() {
        return null;
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderResponseDTO> getMyOrders(Long orderId) {
        return List.of();
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public OrderResponseDTO updateStatus(OrderRequestDTO orderRequestDTO) {
        return null;
    }

    @Override
    public OrderResponseDTO cancelOrder(Long orderId) {
        return null;
    }
}
