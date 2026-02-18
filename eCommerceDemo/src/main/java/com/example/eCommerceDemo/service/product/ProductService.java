package com.example.eCommerceDemo.service.product;

import com.example.eCommerceDemo.dto.request.ProductUpdateRequestDTO;
import com.example.eCommerceDemo.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductUpdateRequestDTO productUpdateRequestDTO);
    ProductResponseDTO updateProduct(ProductUpdateRequestDTO productUpdateRequestDTO, Long id);
    void delete(Long id);
    ProductResponseDTO getById(Long id);
    List<ProductResponseDTO> getAllProducts();
}
