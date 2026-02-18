package com.example.eCommerceDemo.mapper.product;

import com.example.eCommerceDemo.dto.request.ProductUpdateRequestDTO;
import com.example.eCommerceDemo.dto.response.ProductResponseDTO;
import com.example.eCommerceDemo.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {
    ProductResponseDTO toDTO(Product product);
    Product toEntity(ProductUpdateRequestDTO productRequestDTO);
}
