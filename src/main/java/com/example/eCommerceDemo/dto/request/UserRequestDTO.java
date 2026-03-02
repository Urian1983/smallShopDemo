package com.example.eCommerceDemo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User details for profile updates or account management")
public class UserRequestDTO {

    @NotBlank
    @Schema(
            description = "Full name of the user",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotBlank
    @Email
    @Schema(
            description = "Electronic mail address",
            example = "john.doe@example.com",
            format = "email",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @NotBlank
    @Schema(
            description = "Account password",
            example = "p4ssw0rd_Secure",
            format = "password",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;
}
