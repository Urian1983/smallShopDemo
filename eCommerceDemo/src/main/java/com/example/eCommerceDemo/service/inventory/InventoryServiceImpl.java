package com.example.eCommerceDemo.service.inventory;

import com.example.eCommerceDemo.dto.request.OrderItemRequestDTO;
import com.example.eCommerceDemo.repository.OrderRepository;

import java.util.List;

public class InventoryServiceImpl implements InventoryService{

    private final OrderRepository orderRepository;

    public InventoryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public int getStock(Long productId) {
        return 0;
    }

    @Override
    public void removeStock(List<OrderItemRequestDTO> items) {

    }

    @Override
    public void releaseStock(List<OrderItemRequestDTO> items) {

    }

    @Override
    public void confirmStock(List<OrderItemRequestDTO> items) {

    }
}
