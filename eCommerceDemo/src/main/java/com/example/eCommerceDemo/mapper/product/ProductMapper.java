package com.example.eCommerceDemo.mapper.product;

import com.example.eCommerceDemo.dto.request.ProductRequestDTO;
import com.example.eCommerceDemo.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {
    ProductRequestDTO toDTO(Product product);
    Product toEntity(ProductRequestDTO productRequestDTO);
}
