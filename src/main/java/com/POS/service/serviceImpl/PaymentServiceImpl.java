package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.PaymentRequestDto;
import com.POS.dto.responseDto.PaymentResponseDto;
import com.POS.entity.PaymentEntity;
import com.POS.repository.PaymentRepository;
import com.POS.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto requestDto) {
        PaymentEntity entity = new PaymentEntity();
        mapDtoToEntity(requestDto, entity);
        entity.setTransactionDate(LocalDateTime.now());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        if (entity.getIsRefunded() == null) entity.setIsRefunded(false);
        if (entity.getRefundedAmount() == null) entity.setRefundedAmount(0.0);
        PaymentEntity saved = paymentRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDto getPaymentById(Long id) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found with id: " + id));
        return mapEntityToDto(entity);
    }

    @Override
    public PaymentResponseDto getPaymentByPaymentId(String paymentId) {
        PaymentEntity entity = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new NoSuchElementException("Payment not found with paymentId: " + paymentId));
        return mapEntityToDto(entity);
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByTenantAndBranch(String tenantId, String branchId) {
        return paymentRepository.findByTenantIdAndBranchId(tenantId, branchId)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByStatus(String status) {
        return paymentRepository.findByStatus(status)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByMethod(String method) {
        return paymentRepository.findByMethod(method)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByCustomerId(String customerId) {
        return paymentRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> getRefundedPayments() {
        return paymentRepository.findByIsRefunded(true)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDto updatePayment(Long id, PaymentRequestDto requestDto) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found with id: " + id));
        mapDtoToEntity(requestDto, entity);
        entity.setUpdatedAt(LocalDateTime.now());
        PaymentEntity saved = paymentRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public PaymentResponseDto patchPayment(Long id, PaymentRequestDto requestDto) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found with id: " + id));

        if (requestDto.getPaymentId() != null) entity.setPaymentId(requestDto.getPaymentId());
        if (requestDto.getTenantId() != null) entity.setTenantId(requestDto.getTenantId());
        if (requestDto.getBranchId() != null) entity.setBranchId(requestDto.getBranchId());

        if (requestDto.getSaleId() != null) entity.setSaleId(requestDto.getSaleId());
        if (requestDto.getOrderId() != null) entity.setOrderId(requestDto.getOrderId());
        if (requestDto.getInvoiceId() != null) entity.setInvoiceId(requestDto.getInvoiceId());
        if (requestDto.getCustomerId() != null) entity.setCustomerId(requestDto.getCustomerId());

        if (requestDto.getAmount() != null) entity.setAmount(requestDto.getAmount());
        if (requestDto.getCurrency() != null) entity.setCurrency(requestDto.getCurrency());
        if (requestDto.getMethod() != null) entity.setMethod(requestDto.getMethod());
        if (requestDto.getStatus() != null) entity.setStatus(requestDto.getStatus());

        if (requestDto.getTransactionReference() != null) entity.setTransactionReference(requestDto.getTransactionReference());
        if (requestDto.getGateway() != null) entity.setGateway(requestDto.getGateway());

        if (requestDto.getIsRefunded() != null) entity.setIsRefunded(requestDto.getIsRefunded());
        if (requestDto.getRefundedAmount() != null) entity.setRefundedAmount(requestDto.getRefundedAmount());

        entity.setUpdatedAt(LocalDateTime.now());

        PaymentEntity saved = paymentRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public PaymentResponseDto processRefund(Long id, Double refundAmount) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found with id: " + id));

        double alreadyRefunded = entity.getRefundedAmount() != null ? entity.getRefundedAmount() : 0.0;
        double newRefundedAmount = alreadyRefunded + refundAmount;

        entity.setRefundedAmount(newRefundedAmount);
        entity.setIsRefunded(true);

        if (entity.getAmount() != null && newRefundedAmount >= entity.getAmount()) {
            entity.setStatus("REFUNDED");
        } else {
            entity.setStatus("PARTIALLY_REFUNDED");
        }

        entity.setUpdatedAt(LocalDateTime.now());

        PaymentEntity saved = paymentRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new NoSuchElementException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    // ---------------- helper / mapping methods ----------------

    private void mapDtoToEntity(PaymentRequestDto dto, PaymentEntity entity) {
        entity.setPaymentId(dto.getPaymentId());
        entity.setTenantId(dto.getTenantId());
        entity.setBranchId(dto.getBranchId());

        entity.setSaleId(dto.getSaleId());
        entity.setOrderId(dto.getOrderId());
        entity.setInvoiceId(dto.getInvoiceId());
        entity.setCustomerId(dto.getCustomerId());

        entity.setAmount(dto.getAmount());
        entity.setCurrency(dto.getCurrency());
        entity.setMethod(dto.getMethod());
        entity.setStatus(dto.getStatus());

        entity.setTransactionReference(dto.getTransactionReference());
        entity.setGateway(dto.getGateway());

        entity.setIsRefunded(dto.getIsRefunded());
        entity.setRefundedAmount(dto.getRefundedAmount());
    }

    private PaymentResponseDto mapEntityToDto(PaymentEntity entity) {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setId(entity.getId());
        dto.setPaymentId(entity.getPaymentId());
        dto.setTenantId(entity.getTenantId());
        dto.setBranchId(entity.getBranchId());

        dto.setSaleId(entity.getSaleId());
        dto.setOrderId(entity.getOrderId());
        dto.setInvoiceId(entity.getInvoiceId());
        dto.setCustomerId(entity.getCustomerId());

        dto.setAmount(entity.getAmount());
        dto.setCurrency(entity.getCurrency());
        dto.setMethod(entity.getMethod());
        dto.setStatus(entity.getStatus());

        dto.setTransactionReference(entity.getTransactionReference());
        dto.setGateway(entity.getGateway());
        dto.setTransactionDate(entity.getTransactionDate());

        dto.setIsRefunded(entity.getIsRefunded());
        dto.setRefundedAmount(entity.getRefundedAmount());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}
