package com.example.eCommerceDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name="created_at")

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    private LocalDateTime createdAt;
    @Column(nullable = false, name="updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Column(unique = true,nullable = false, name="SKU")
    private String sku;

    @Column(unique = true,nullable = false, name="slug")
    private String slug;

    @Column(nullable= false, name="name")
    private String name;

    @Column(name="description", length=1000)
    private String description;

    @Column(name="shortDescription")
    private String shortDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name="stock")
    private int stock;

    @Column(nullable = false,name="pvp")
    private BigDecimal pvp;

    @Column(nullable = false,name="price")
    private BigDecimal price;

}
