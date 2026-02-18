package com.example.eCommerceDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_id")
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="quantity", nullable=false)
    private int quantity;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY,
    optional = false)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;
}
