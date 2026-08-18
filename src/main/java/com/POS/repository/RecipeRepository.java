package com.POS.repository;

import com.POS.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    Optional<RecipeEntity> findByRecipeId(String recipeId);

    List<RecipeEntity> findByTenantIdAndBranchId(String tenantId, String branchId);

    List<RecipeEntity> findByIsActive(Boolean isActive);

    List<RecipeEntity> findByProductId(String productId);

    List<RecipeEntity> findByType(String type);

    boolean existsByRecipeId(String recipeId);
}
