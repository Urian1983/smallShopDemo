package com.example.eCommerceDemo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Basic user information summary")
public class UserSummaryDTO {
    @Schema(description = "User unique identifier", example = "1")
    private Long id;

    @Schema(description = "User email address", example = "user@example.com")
    private String email;

    @Schema(description = "Full name of the user", example = "Jane Doe")
    private String name;
}
