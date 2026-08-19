package com.POS.repository;

import com.POS.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, String> {

    Optional<ReportEntity> findByReportId(String reportId);

    List<ReportEntity> findByType(String type);

    boolean existsByReportId(String reportId);
}
