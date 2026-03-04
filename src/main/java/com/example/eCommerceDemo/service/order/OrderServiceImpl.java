package com.example.eCommerceDemo.service.order;

import com.example.eCommerceDemo.dto.request.OrderRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderResponseDTO;
import com.example.eCommerceDemo.exceptions.NotFoundException;
import com.example.eCommerceDemo.mapper.order.OrderMapper;
import com.example.eCommerceDemo.model.*;
import com.example.eCommerceDemo.repository.CartRepository;
import com.example.eCommerceDemo.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.cartRepository = cartRepository;
    }

    @Override
    public OrderResponseDTO createOrder(Long userId, OrderRequestDTO orderRequestDTO) {
        log.info("Starting order creation process for user ID: {}", userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Order creation failed: Cart not found for user ID: {}", userId);
                    return new NotFoundException();
                });

        if (cart.getCartItems().isEmpty()) {
            log.warn("Order creation aborted: Cart is empty for user ID: {}", userId);
            throw new IllegalStateException("Cart is empty");
        }

        Order order = orderMapper.toEntity(orderRequestDTO);
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setStatus(Status.PENDING);
        order.setPaymentMethod(PaymentMethod.valueOf(orderRequestDTO.getPaymentMethod()));
        order.setUser(cart.getUser());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setProductName(cartItem.getProduct().getName());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            totalPrice = totalPrice.add(
                    cartItem.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
            );
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
        log.info("Order successfully created. OrderNumber: {}, Total: {}", order.getOrderNumber(), totalPrice);

        cart.getCartItems().clear();
        cartRepository.save(cart);
        log.debug("Cart cleared for user ID: {} after successful order", userId);

        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        log.info("Fetching order details for ID: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order lookup failed: Order ID {} not found", id);
                    return new NotFoundException();
                });

        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDTO> getMyOrders(Long userId) {
        log.info("Retrieving order history for user ID: {}", userId);
        List<OrderResponseDTO> orders = new ArrayList<>();

        for(Order order : orderRepository.findByUserId(userId)) {
            orders.add(orderMapper.toDto(order));
        }

        log.debug("Found {} orders for user ID: {}", orders.size(), userId);
        return orders;
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        log.info("Admin request: Retrieving all orders from database");
        List<OrderResponseDTO> orders = new ArrayList<>();

        for(Order order : orderRepository.findAll()) {
            orders.add(orderMapper.toDto(order));
        }

        return orders;
    }

    @Override
    public OrderResponseDTO updateStatus(Long orderId, Status status) {
        log.info("Updating status for order ID: {} to {}", orderId, status);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.error("Status update failed: Order ID {} not found", orderId);
                    return new NotFoundException();
                });

        Status oldStatus = order.getStatus();
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);

        log.info("Order ID: {} status changed from {} to {}", orderId, oldStatus, status);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponseDTO cancelOrder(Long orderId) {
        log.info("Attempting to cancel order ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.error("Cancellation failed: Order ID {} not found", orderId);
                    return new NotFoundException();
                });

        order.setStatus(Status.CANCELLED);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);

        log.warn("Order ID: {} has been cancelled", orderId);
        return orderMapper.toDto(order);
    }
}