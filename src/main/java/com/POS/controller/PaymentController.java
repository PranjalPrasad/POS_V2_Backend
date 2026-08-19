package com.POS.controller;

import com.POS.dto.requestDto.PaymentRequestDto;
import com.POS.dto.responseDto.PaymentResponseDto;
import com.POS.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-payment")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto requestDto) {
        PaymentResponseDto response = paymentService.createPayment(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-payments")
    public ResponseEntity<List<PaymentResponseDto>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/get-payment-by-id/{id}")
    public ResponseEntity<PaymentResponseDto> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/get-payment-by-payment-id/payment-code/{paymentId}")
    public ResponseEntity<PaymentResponseDto> getPaymentByPaymentId(@PathVariable String paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentByPaymentId(paymentId));
    }

    @GetMapping("/get-payment-by-tenant-and-branch/tenant/{tenantId}/branch/{branchId}")
    public ResponseEntity<List<PaymentResponseDto>> getPaymentsByTenantAndBranch(
            @PathVariable String tenantId,
            @PathVariable String branchId) {
        return ResponseEntity.ok(paymentService.getPaymentsByTenantAndBranch(tenantId, branchId));
    }

    @GetMapping("/get-payments-by-status/status/{status}")
    public ResponseEntity<List<PaymentResponseDto>> getPaymentsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(status));
    }

    @GetMapping("/get-payments-by-method/method/{method}")
    public ResponseEntity<List<PaymentResponseDto>> getPaymentsByMethod(@PathVariable String method) {
        return ResponseEntity.ok(paymentService.getPaymentsByMethod(method));
    }

    @GetMapping("/get-payments-by-order-id/order/{orderId}")
    public ResponseEntity<List<PaymentResponseDto>> getPaymentsByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(paymentService.getPaymentsByOrderId(orderId));
    }

    @GetMapping("/get-payments-by-customer-id/customer/{customerId}")
    public ResponseEntity<List<PaymentResponseDto>> getPaymentsByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(paymentService.getPaymentsByCustomerId(customerId));
    }

    @GetMapping("/get-refunded-payments/refunded")
    public ResponseEntity<List<PaymentResponseDto>> getRefundedPayments() {
        return ResponseEntity.ok(paymentService.getRefundedPayments());
    }

    @PutMapping("/update-payment/{id}")
    public ResponseEntity<PaymentResponseDto> updatePayment(
            @PathVariable Long id,
            @RequestBody PaymentRequestDto requestDto) {
        return ResponseEntity.ok(paymentService.updatePayment(id, requestDto));
    }

    @PatchMapping("/patch-payment/{id}")
    public ResponseEntity<PaymentResponseDto> patchPayment(
            @PathVariable Long id,
            @RequestBody PaymentRequestDto requestDto) {
        return ResponseEntity.ok(paymentService.patchPayment(id, requestDto));
    }

    @PatchMapping("/process-refund/{id}/refund")
    public ResponseEntity<PaymentResponseDto> processRefund(
            @PathVariable Long id,
            @RequestParam Double refundAmount) {
        return ResponseEntity.ok(paymentService.processRefund(id, refundAmount));
    }

    @DeleteMapping("/delete-payment/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
