package com.example.eCommerceDemo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BrandResponseDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String logoURL;
    private String slug;
}
