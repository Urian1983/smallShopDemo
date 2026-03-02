package com.example.eCommerceDemo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "Response containing product details for display")
public class ProductResponseDTO {

    @Schema(description = "Unique product identifier", example = "101")
    private Long id;

    @Schema(description = "Timestamp when the product was added to catalog")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp of the last product update")
    private LocalDateTime updatedAt;

    @Schema(description = "URL of the product image", example = "https://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "Stock Keeping Unit", example = "PROD-001")
    private String sku;

    @Schema(description = "URL-friendly product identifier", example = "product-name-slug")
    private String slug;

    @Schema(description = "Full name of the product", example = "Smartphone X")
    private String name;

    @Schema(description = "Brand name", example = "TechBrand")
    private String brand;

    @Schema(description = "Product category", example = "Electronics")
    private String category;

    @Schema(description = "Full product description", example = "High-end smartphone with advanced features")
    private String description;

    @Schema(description = "Short version of the description", example = "High-end smartphone")
    private String shortDescription;

    @Schema(description = "Available units in stock", example = "50")
    private int stock;

    @Schema(description = "Current product price", example = "599.00")
    private BigDecimal price;
}
