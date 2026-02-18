package com.example.eCommerceDemo.service.inventory;

import com.example.eCommerceDemo.dto.request.OrderItemRequestDTO;

import java.util.List;

public interface InventoryService {

    int getStock(Long productId);
    void removeStock(List<OrderItemRequestDTO> items);
    void releaseStock(List<OrderItemRequestDTO> items);
    void confirmStock(List<OrderItemRequestDTO> items);

}
