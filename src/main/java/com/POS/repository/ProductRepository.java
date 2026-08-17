package com.POS.repository;

import com.POS.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductId(String productId);

    List<Product> findByTenantIdAndBranchId(String tenantId, String branchId);

    List<Product> findByTenantId(String tenantId);

    Optional<Product> findBySku(String sku);

    Optional<Product> findByBarcode(String barcode);

    boolean existsByProductId(String productId);
}
