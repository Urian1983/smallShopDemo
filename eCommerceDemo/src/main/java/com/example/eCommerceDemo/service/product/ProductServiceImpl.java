package com.example.eCommerceDemo.service.product;

import com.example.eCommerceDemo.dto.request.ProductUpdateRequestDTO;
import com.example.eCommerceDemo.dto.response.ProductResponseDTO;
import com.example.eCommerceDemo.exceptions.NotFoundException;
import com.example.eCommerceDemo.mapper.product.ProductMapperImpl;
import com.example.eCommerceDemo.model.Product;
import com.example.eCommerceDemo.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapperImpl productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapperImpl productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDTO createProduct(ProductUpdateRequestDTO productUpdateRequestDTO)
    {
        Product newProduct = productMapper.toEntity(new ProductUpdateRequestDTO());

        productRepository.save(newProduct);

        return productMapper.toDTO(newProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(ProductUpdateRequestDTO productUpdateRequestDTO, Long id) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        Product tempProduct = productMapper.toEntity(productUpdateRequestDTO);

        productToUpdate.setName(tempProduct.getName());
        productToUpdate.setSku(tempProduct.getSku());
        productToUpdate.setSlug(tempProduct.getSlug());
        productToUpdate.setDescription(tempProduct.getDescription());
        productToUpdate.setShortDescription(tempProduct.getShortDescription());
        productToUpdate.setBrand(tempProduct.getBrand());
        productToUpdate.setUpdatedAt(LocalDateTime.now());
        productToUpdate.setCategory(tempProduct.getCategory());
        productToUpdate.setPrice(tempProduct.getPrice());
        productToUpdate.setStock(tempProduct.getStock());

        productRepository.save(productToUpdate);
        return productMapper.toDTO(productToUpdate);
    }

    @Override
    public void delete(Long id) {
        if(!productRepository.existsById(id))
        {
            throw new NotFoundException();
        }

        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        List<ProductResponseDTO> products = new ArrayList<>();
        for(Product product : productRepository.findAll()){
            products.add(productMapper.toDTO(product));
        }
        return products;
    }
}
