package com.example.eCommerceDemo.mapper;

import com.example.eCommerceDemo.dto.response.BrandResponseDTO;
import com.example.eCommerceDemo.model.Brand;
import org.springframework.stereotype.Component;

@Component
public interface BrandMapper {

    public BrandResponseDTO toDTO(Brand brand);

    public Brand toEntity(BrandResponseDTO dto);

}
