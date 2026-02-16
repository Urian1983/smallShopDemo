package com.example.eCommerceDemo.mapper;

import com.example.eCommerceDemo.dto.response.ProductResponseDTO;
import com.example.eCommerceDemo.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {
    public ProductResponseDTO toDTO(Product product);

    public Product toEntity(ProductResponseDTO dto);
}
