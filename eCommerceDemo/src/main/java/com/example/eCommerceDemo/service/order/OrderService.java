package com.example.eCommerceDemo.service.order;

import com.example.eCommerceDemo.dto.request.OrderRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderResponseDTO;
import com.example.eCommerceDemo.model.Status;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(Long userId, OrderRequestDTO orderRequestDTO);
    OrderResponseDTO getOrderById(Long orderId);
    List<OrderResponseDTO> getMyOrders(Long userId);
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO updateStatus(Long orderId, Status status);
    OrderResponseDTO cancelOrder(Long orderId);
}
