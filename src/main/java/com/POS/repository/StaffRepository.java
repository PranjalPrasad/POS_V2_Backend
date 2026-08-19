package com.POS.repository;

import com.POS.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, String> {

    Optional<StaffEntity> findByStaffId(String staffId);

    List<StaffEntity> findByRole(String role);

    List<StaffEntity> findByStatus(String status);

    boolean existsByStaffId(String staffId);
}
