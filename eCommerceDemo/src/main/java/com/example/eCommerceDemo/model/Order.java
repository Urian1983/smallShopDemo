package com.example.eCommerceDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at",nullable=false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @Column(nullable = false)
    private Status status;

    @Column(name="updated_at",nullable=false)
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Column(name="order_number",nullable = false,unique = true)
    private String orderNumber;

    @Column(name="payment_method",nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name="address",nullable = false,length = 1000)
    private String address;


    @Column(name="total_price",nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="order")
    private List<OrderItem> orderItems;
}
