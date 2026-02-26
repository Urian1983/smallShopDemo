package com.example.eCommerceDemo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String name;
    private Set<String> roles;
    private LocalDateTime createdAt;
    private boolean enabled;
    private boolean locked;

}
