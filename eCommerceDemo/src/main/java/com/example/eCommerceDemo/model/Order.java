package com.example.eCommerceDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @Column(name="order_number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name="status",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="created_at",nullable=false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable=false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name="address",nullable=false, unique = true,length = 500)
    private String address;

    @Column(name="postal_code",nullable=false,unique = true)
    private String postalCode;

    @Column(name="country",nullable=false, unique = true, length = 100)
    private String country;

    @Column(name="payment_method",nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<OrderItem> orderItems;
}
