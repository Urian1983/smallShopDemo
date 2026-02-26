package com.example.eCommerceDemo.service.product;

import com.example.eCommerceDemo.dto.request.ProductRequestDTO;
import com.example.eCommerceDemo.dto.response.ProductResponseDTO;
import com.example.eCommerceDemo.exceptions.NotFoundException;
import com.example.eCommerceDemo.mapper.product.ProductMapperImpl;
import com.example.eCommerceDemo.model.Product;
import com.example.eCommerceDemo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapperImpl productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductRequestDTO productRequestDTO;
    private ProductResponseDTO productResponseDTO;

    @BeforeEach
    void setUp() {
        productRequestDTO = new ProductRequestDTO(
                "SKU123",
                "Laptop Gaming",
                "BrandX",
                "http://image.com",
                "Long description",
                "Short description",
                "Electronics",
                10,
                new BigDecimal("1200.00")
        );

        product = new Product();
        product.setId(1L);
        product.setName("Laptop Gaming");
        product.setSku("SKU123");

        productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(1L);
        productResponseDTO.setName("Laptop Gaming");
        productResponseDTO.setSlug("laptop-gaming");
    }

    @Test
    @DisplayName("Create Product: Should generate slug and save correctly")
    void createProduct_Success() {
        when(productMapper.toEntity(any(ProductRequestDTO.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productResponseDTO);

        ProductResponseDTO result = productService.createProduct(productRequestDTO);

        assertNotNull(result);
        assertEquals("laptop-gaming", product.getSlug()); // Verificamos la lógica del slug en la entidad
        verify(productRepository, times(1)).save(product);
    }

    /*@Test
    @DisplayName("Update Product: Should update fields and change slug if name changes")
    void updateProduct_Success() {
        Long productId = 1L;
        productRequestDTO.setName("Testing Product");

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Old Name");

        Product mappedProduct = new Product();

        mappedProduct.setName("Testing Product");
        mappedProduct.setSku("SKU-VAL");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productMapper.toEntity(productRequestDTO)).thenReturn(mappedProduct); // Mock preciso
        when(productMapper.toDTO(any(Product.class))).thenReturn(productResponseDTO);

        productService.updateProduct(productRequestDTO, productId);
        assertEquals("new-laptop-name", existingProduct.getSlug());
        verify(productRepository).save(existingProduct);
    }*/

    @Test
    @DisplayName("Get By Id: Should throw NotFoundException when product missing")
    void getById_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.getById(99L));
    }

    @Test
    @DisplayName("Delete Product: Should call delete when product exists")
    void delete_Success() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.delete(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Get All: Should return list of DTOs")
    void getAllProducts_Success() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toDTO(product)).thenReturn(productResponseDTO);

        List<ProductResponseDTO> result = productService.getAllProducts();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}