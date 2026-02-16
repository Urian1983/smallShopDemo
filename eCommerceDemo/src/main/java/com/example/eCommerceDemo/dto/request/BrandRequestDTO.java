package com.example.eCommerceDemo.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class BrandRequestDTO {

    @NotNull
    @NotBlank
    @Length(min=1,max=100)
    private String name;
    @NotNull
    @NotBlank
    @Length(min=1,max=500)
    private String logoURL;
}
