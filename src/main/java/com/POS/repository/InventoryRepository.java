package com.POS.repository;

import com.POS.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    Optional<InventoryEntity> findByInventoryId(String inventoryId);

    List<InventoryEntity> findByTenantIdAndBranchId(String tenantId, String branchId);

    List<InventoryEntity> findByStockStatus(String stockStatus);

    List<InventoryEntity> findByProductSku(String productSku);

    List<InventoryEntity> findByWarehouseId(String warehouseId);

    // used for a simple low-stock report: currentStock <= reorderLevel
    List<InventoryEntity> findByCurrentStockLessThanEqual(Integer reorderLevel);

    boolean existsByInventoryId(String inventoryId);
}
