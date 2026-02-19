package com.example.eCommerceDemo.service.order;

import com.example.eCommerceDemo.dto.request.OrderRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderResponseDTO;
import com.example.eCommerceDemo.exceptions.NotFoundException;
import com.example.eCommerceDemo.mapper.order.OrderMapper;
import com.example.eCommerceDemo.model.*;
import com.example.eCommerceDemo.repository.CartRepository;
import com.example.eCommerceDemo.repository.OrderRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(NotFoundException::new);

        if (cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Order order = orderMapper.toEntity(orderRequestDTO);
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setStatus(Status.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
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

        cart.getCartItems().clear();
        cartRepository.save(cart);

        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order= orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        return orderMapper.toDto(order);

    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<OrderResponseDTO> getMyOrders(Long userId) {
        List<OrderResponseDTO> orders = new ArrayList<>();

        for(Order order : orderRepository.findByUserId(userId)) {
            orders.add(orderMapper.toDto(order));
        }

        return orders;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponseDTO> getAllOrders() {
        List<OrderResponseDTO> orders = new ArrayList<>();

        for(Order order : orderRepository.findAll()) {
            orders.add(orderMapper.toDto(order));
        }

        return orders;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponseDTO updateStatus(Long orderId, Status status) {
        Order order= orderRepository.findById(orderId)
                .orElseThrow(NotFoundException::new);

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponseDTO cancelOrder(Long orderId) {
        Order order= orderRepository.findById(orderId)
                .orElseThrow(NotFoundException::new);

        order.setStatus(Status.CANCELLED);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}
