package com.example.eCommerceDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    LocalDateTime createdAt;

    @Column(name="updated_at",nullable=false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name="total price",nullable=false)
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CartItem> cartItems;

}
