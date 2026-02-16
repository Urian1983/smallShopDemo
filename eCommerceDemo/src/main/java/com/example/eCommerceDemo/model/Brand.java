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
public class Brand {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at",nullable=false)
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
        this.updatedAt = LocalDateTime.now();
    }

    @Column(nullable = false,unique = true,name="name",length = 100)
    private String name;

    @Column(nullable = false,name="logoURL",length =500)
    private String logoURL;

    @Column(nullable = false,unique = true,name="slug",length = 500)
    private String slug;

    @OneToMany(fetch=FetchType.LAZY,mappedBy ="brand" )
    private List<Product> products;
}
