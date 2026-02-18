package com.example.eCommerceDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "product_id"}))
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="sku",nullable = false,unique =true)
    private String sku;

    @Column(name="quantity",nullable = false)
    private int quantity;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    LocalDateTime createdAt;

    @Column(name="updated_at",nullable=false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
