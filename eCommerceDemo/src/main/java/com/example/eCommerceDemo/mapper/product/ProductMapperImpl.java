package com.example.eCommerceDemo.mapper.product;

import com.example.eCommerceDemo.dto.request.ProductRequestDTO;
import com.example.eCommerceDemo.exceptions.NullObjectException;
import com.example.eCommerceDemo.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper{
    @Override
    public ProductRequestDTO toDTO(Product product) {
        if(product==null){
            throw new NullObjectException();
        }
        return null;
    }

    @Override
    public Product toEntity(ProductRequestDTO productRequestDTO) {
        if(productRequestDTO==null){
            throw new NullObjectException();
        }
        return null;
    }
}
