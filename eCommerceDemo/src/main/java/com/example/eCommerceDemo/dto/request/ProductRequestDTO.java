package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ProductRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    @NotBlank
    @Length(min=1,max=1000)
    private String description;

    @NotBlank
    private String shortDescription;
}
