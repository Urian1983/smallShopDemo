package com.example.eCommerceDemo.controller;

import com.example.eCommerceDemo.dto.request.ProductRequestDTO;
import com.example.eCommerceDemo.dto.response.ProductResponseDTO;
import com.example.eCommerceDemo.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Endpoints used for products management")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "To create a new product")
    @ApiResponse(responseCode = "201", description = "Product created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)))
    ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(productRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "To update an existent product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    ResponseEntity<ProductResponseDTO> update(@PathVariable Long id,
                                              @Valid @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO DTO = productService.updateProduct(productRequestDTO, id);
        return ResponseEntity.ok().body(DTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "To get an existant product")
    @ApiResponse(responseCode = "200", description = "Product found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)))
    ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        ProductResponseDTO DTO = productService.getById(id);
        return ResponseEntity.ok().body(DTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "To delete an existent product")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully", content = @Content)
    ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    @Operation(summary = "Returns all available products")
    @ApiResponse(responseCode = "200", description = "List of products retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)))
    ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> DTO = productService.getAllProducts();
        return ResponseEntity.ok().body(DTO);
    }
}
