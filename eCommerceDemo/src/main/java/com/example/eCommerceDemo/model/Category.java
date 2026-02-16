package com.example.eCommerceDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at",nullable=false,length=100)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @Column(name="updated_at",nullable=false)
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Column(nullable = false,unique = true,name="name")
    private String name;

    @Column(nullable = false,unique = true,name="slug")
    private String slug;

    @Column(nullable = false,unique = true,name="description", length = 1000)
    private String description;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "category_id")
    private List<Product> products;

}
