package com.example.eCommerceDemo.controller;

import com.example.eCommerceDemo.dto.request.UserRequestDTO;
import com.example.eCommerceDemo.dto.response.UserResponseDTO;
import com.example.eCommerceDemo.model.User;
import com.example.eCommerceDemo.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "Endpoints used for users management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Gets an user using its ID", tags = { "Users" })
    @ApiResponse(responseCode = "200", description = "User retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)))
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        log.info("REST request to get user by ID: {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Updates an user using its ID", tags = { "Users" })
    @ApiResponse(responseCode = "200", description = "User updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)))
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("REST request to update user ID: {}", id);
        UserResponseDTO response = userService.updateUser(id, userRequestDTO);
        log.info("User ID: {} updated successfully", id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletes an user from the database using its ID", tags = { "Users" })
    @ApiResponse(responseCode = "204", description = "User deleted successfully", content = @Content)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.warn("REST request to delete user ID: {}", id);
        userService.deleteUser(id);
        log.info("User ID: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Gets the profile of the active user", tags = { "Users" })
    @ApiResponse(responseCode = "200", description = "Profile retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)))
    public ResponseEntity<UserResponseDTO> getMyProfile(@AuthenticationPrincipal User user) {
        log.debug("REST request to get current profile for user ID: {}", user.getId());
        return ResponseEntity.ok(userService.getUserById(user.getId()));
    }
}