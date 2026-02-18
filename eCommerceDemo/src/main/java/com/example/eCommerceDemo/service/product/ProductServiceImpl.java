package com.example.eCommerceDemo.service.product;

import com.example.eCommerceDemo.dto.request.ProductRequestDTO;
import com.example.eCommerceDemo.dto.response.ProductResponseDTO;

import java.util.List;

public class ProductServiceImpl implements ProductService {


    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProductResponseDTO getById(Long id) {
        return null;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return List.of();
    }
}
