package com.POS.repository;

import com.POS.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, String> {

    Optional<SaleEntity> findBySaleNumber(String saleNumber);

    List<SaleEntity> findByTenantIdAndBranchId(String tenantId, String branchId);

    List<SaleEntity> findByCustomer_CustomerId(String customerId);

    List<SaleEntity> findByStatus(String status);
}
