package com.POS.repository;

import com.POS.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    Optional<TableEntity> findByTableId(String tableId);

    List<TableEntity> findByTenantIdAndBranchId(String tenantId, String branchId);

    List<TableEntity> findByCurrentStatus(String currentStatus);

    List<TableEntity> findBySectionId(String sectionId);

    List<TableEntity> findByIsActive(Boolean isActive);

    boolean existsByTableId(String tableId);
}
