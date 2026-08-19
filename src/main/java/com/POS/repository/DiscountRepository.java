package com.POS.repository;

import com.POS.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {

    Optional<DiscountEntity> findByDiscountId(String discountId);

    Optional<DiscountEntity> findByCode(String code);

    List<DiscountEntity> findByTenantIdAndBranchId(String tenantId, String branchId);

    List<DiscountEntity> findByIsActive(Boolean isActive);

    List<DiscountEntity> findByDiscountType(String discountType);

    boolean existsByDiscountId(String discountId);

    boolean existsByCode(String code);
}
