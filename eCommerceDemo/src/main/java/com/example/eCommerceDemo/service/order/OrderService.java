package com.example.eCommerceDemo.service.order;

import com.example.eCommerceDemo.dto.request.OrderRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder();
    OrderResponseDTO getOrderById(Long orderId);
    List<OrderResponseDTO> getMyOrders(Long orderId);
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO updateStatus(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO cancelOrder(Long orderId);
}
