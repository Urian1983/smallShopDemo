package com.example.eCommerceDemo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Schema(description = "Response containing detailed user profile information")
public class UserResponseDTO {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "User's email address", example = "user@example.com")
    private String email;

    @Schema(description = "User's full name", example = "John Doe")
    private String name;

    @Schema(description = "Set of security roles assigned to the user", example = "[\"ROLE_USER\"]")
    private Set<String> roles;

    @Schema(description = "Timestamp of account creation")
    private LocalDateTime createdAt;

    @Schema(description = "Flag indicating if the account is enabled", example = "true")
    private boolean enabled;

    @Schema(description = "Flag indicating if the account is locked", example = "false")
    private boolean locked;
}
