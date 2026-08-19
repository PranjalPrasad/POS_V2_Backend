package com.POS.repository;

import com.POS.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findByPaymentId(String paymentId);

    List<PaymentEntity> findByTenantIdAndBranchId(String tenantId, String branchId);

    List<PaymentEntity> findByStatus(String status);

    List<PaymentEntity> findByMethod(String method);

    List<PaymentEntity> findByOrderId(String orderId);

    List<PaymentEntity> findBySaleId(String saleId);

    List<PaymentEntity> findByCustomerId(String customerId);

    List<PaymentEntity> findByIsRefunded(Boolean isRefunded);

    boolean existsByPaymentId(String paymentId);
}
