package com.example.eCommerceDemo.mapper;

import com.example.eCommerceDemo.dto.response.CategoryResponseDTO;
import com.example.eCommerceDemo.model.Category;
import org.springframework.stereotype.Component;

@Component
public interface CategoryMapper {

    public CategoryResponseDTO toDTO(Category category);

    public Category toEntity(CategoryResponseDTO dto);
}
