package com.example.eCommerceDemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotBlank
    @Length(min=1, max=100)
    private String orderNumber;

   @NotBlank
   @Length(min=1, max=500)
    private String address;
   @NotBlank
    private String postalCode;
   @NotBlank
    private String country;
}
