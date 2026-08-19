package com.POS.service;


import com.POS.dto.requestDto.PaymentRequestDto;
import com.POS.dto.responseDto.PaymentResponseDto;

import java.util.List;

public interface PaymentService {

    PaymentResponseDto createPayment(PaymentRequestDto requestDto);

    List<PaymentResponseDto> getAllPayments();

    PaymentResponseDto getPaymentById(Long id);

    PaymentResponseDto getPaymentByPaymentId(String paymentId);

    List<PaymentResponseDto> getPaymentsByTenantAndBranch(String tenantId, String branchId);

    List<PaymentResponseDto> getPaymentsByStatus(String status);

    List<PaymentResponseDto> getPaymentsByMethod(String method);

    List<PaymentResponseDto> getPaymentsByOrderId(String orderId);

    List<PaymentResponseDto> getPaymentsByCustomerId(String customerId);

    List<PaymentResponseDto> getRefundedPayments();

    // Full update - all fields expected
    PaymentResponseDto updatePayment(Long id, PaymentRequestDto requestDto);

    // Partial update - only non-null fields applied
    PaymentResponseDto patchPayment(Long id, PaymentRequestDto requestDto);

    // Convenience: process a refund (full or partial)
    PaymentResponseDto processRefund(Long id, Double refundAmount);

    void deletePayment(Long id);
}
