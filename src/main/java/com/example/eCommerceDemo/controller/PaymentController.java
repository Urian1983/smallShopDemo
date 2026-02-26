package com.example.eCommerceDemo.controller;

import com.example.eCommerceDemo.dto.request.PaymentRequestDTO;
import com.example.eCommerceDemo.dto.response.PaymentResponseDTO;
import com.example.eCommerceDemo.model.User;
import com.example.eCommerceDemo.service.payment.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Validated
@Tag(name = "Payment", description = "Endpoints used for the management of the payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(
            summary = "Simulates a payment process, not a real one",
            description = "Simulates a payment process, not a real one"
    )
    @ApiResponse(responseCode = "201", description = "Payment processed successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDTO.class)))
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@AuthenticationPrincipal User user,
                                                             @RequestBody PaymentRequestDTO paymentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.processPayment(paymentRequestDTO, user.getId()));
    }

    @Operation(
            summary = "Returns an already existing payment process",
            description = "Returns an already existing payment process"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment details retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Payment not found for the given order", content = @Content)
    })
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }
}