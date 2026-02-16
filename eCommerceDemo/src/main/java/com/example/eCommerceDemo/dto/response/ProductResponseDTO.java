package com.example.eCommerceDemo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponseDTO {


    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String sku;
    private String slug;
    private String name;
    private String description;
    private String shortDescription;
    private int stock;
    private BigDecimal pvp;
    private BigDecimal price;

}
