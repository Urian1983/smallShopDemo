package com.example.eCommerceDemo.controller;

import com.example.eCommerceDemo.dto.request.OrderRequestDTO;
import com.example.eCommerceDemo.dto.response.OrderResponseDTO;
import com.example.eCommerceDemo.model.Status;
import com.example.eCommerceDemo.model.User;
import com.example.eCommerceDemo.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Validated
@Tag(name = "Order", description = "Endpoints used for order management")
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Create a new order",
            description = "Creates a new order for the authenticated user based on the provided order details."
    )
    @ApiResponse(responseCode = "201", description = "Order created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class)))
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@AuthenticationPrincipal User user,
                                                        @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(user.getId(), orderRequestDTO));
    }

    @Operation(summary = "Get order by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Get orders of authenticated user")
    @ApiResponse(responseCode = "200", description = "List of user orders retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class)))
    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getMyOrders(user.getId()));
    }

    @Operation(summary = "Get all orders")
    @ApiResponse(responseCode = "200", description = "All orders retrieved successfully (Admin only)",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class)))
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Update order status")
    @ApiResponse(responseCode = "200", description = "Order status updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class)))
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateStatus(@PathVariable Long id,
                                                         @RequestParam Status status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }

    @Operation(summary = "Cancel order")
    @ApiResponse(responseCode = "200", description = "Order cancelled successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class)))
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}


