package com.example.eCommerceDemo.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String name;
    private Set<String> roles;
    private boolean enabled;
    private boolean locked;

}
