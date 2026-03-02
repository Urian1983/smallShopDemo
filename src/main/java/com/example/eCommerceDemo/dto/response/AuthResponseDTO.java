package com.example.eCommerceDemo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response object containing the authentication token and user details")
public class AuthResponseDTO {

    @Schema(description = "JWT access token for authenticating subsequent requests", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Information of the authenticated user")
    private UserResponseDTO user;
}
