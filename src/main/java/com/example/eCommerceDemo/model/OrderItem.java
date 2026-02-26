package com.example.eCommerceDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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

    @Column(name="product_name")
    private String productName;

    @Column(name="quantity", nullable=false)
    @Min(value = 0, message = "Stock cannot be negative")
    private int quantity;

    @Column(name="price", nullable=false)
    @Min(value = 0, message = "Price cannot be negative")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY,
    optional = false)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;
}
