package com.POS.repository;

import com.POS.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByCustomerId(String customerId);

    List<CustomerEntity> findByTenantIdAndBranchId(String tenantId, String branchId);

    List<CustomerEntity> findByStatus(String status);

    boolean existsByCustomerId(String customerId);
}
