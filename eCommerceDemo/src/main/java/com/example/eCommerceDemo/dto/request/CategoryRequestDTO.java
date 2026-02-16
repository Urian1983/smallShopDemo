package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CategoryRequestDTO {

    @NotBlank
    @NotNull
    @Length(min=1,max=100)
    private String name;

    @NotBlank
    @NotNull
    @Length(min=1,max=1000)
    private String description;
}
