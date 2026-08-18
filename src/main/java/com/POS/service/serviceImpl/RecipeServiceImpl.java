package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.IngredientRequestDto;
import com.POS.dto.requestDto.RecipeRequestDto;
import com.POS.dto.resposneDto.IngredientResponseDto;
import com.POS.dto.resposneDto.RecipeResponseDto;
import com.POS.entity.RecipeEntity;
import com.POS.entity.RecipeIngredientEntity;
import com.POS.repository.RecipeRepository;
import com.POS.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeResponseDto createRecipe(RecipeRequestDto requestDto) {
        RecipeEntity entity = new RecipeEntity();
        mapDtoToEntity(requestDto, entity);
        replaceIngredients(entity, requestDto.getIngredients());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        recalculateCosting(entity);
        RecipeEntity saved = recipeRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public List<RecipeResponseDto> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeResponseDto getRecipeById(Long id) {
        RecipeEntity entity = recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with id: " + id));
        return mapEntityToDto(entity);
    }

    @Override
    public RecipeResponseDto getRecipeByRecipeId(String recipeId) {
        RecipeEntity entity = recipeRepository.findByRecipeId(recipeId)
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with recipeId: " + recipeId));
        return mapEntityToDto(entity);
    }

    @Override
    public List<RecipeResponseDto> getRecipesByTenantAndBranch(String tenantId, String branchId) {
        return recipeRepository.findByTenantIdAndBranchId(tenantId, branchId)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeResponseDto> getActiveRecipes() {
        return recipeRepository.findByIsActive(true)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeResponseDto updateRecipe(Long id, RecipeRequestDto requestDto) {
        RecipeEntity entity = recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with id: " + id));
        mapDtoToEntity(requestDto, entity);
        replaceIngredients(entity, requestDto.getIngredients());
        entity.setUpdatedAt(LocalDateTime.now());
        recalculateCosting(entity);
        RecipeEntity saved = recipeRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public RecipeResponseDto patchRecipe(Long id, RecipeRequestDto requestDto) {
        RecipeEntity entity = recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with id: " + id));

        if (requestDto.getRecipeId() != null) entity.setRecipeId(requestDto.getRecipeId());
        if (requestDto.getTenantId() != null) entity.setTenantId(requestDto.getTenantId());
        if (requestDto.getBranchId() != null) entity.setBranchId(requestDto.getBranchId());

        if (requestDto.getProductId() != null) entity.setProductId(requestDto.getProductId());
        if (requestDto.getProductName() != null) entity.setProductName(requestDto.getProductName());
        if (requestDto.getProductSku() != null) entity.setProductSku(requestDto.getProductSku());

        if (requestDto.getType() != null) entity.setType(requestDto.getType());

        if (requestDto.getYieldQuantity() != null) entity.setYieldQuantity(requestDto.getYieldQuantity());
        if (requestDto.getYieldUnit() != null) entity.setYieldUnit(requestDto.getYieldUnit());

        if (requestDto.getTotalMaterialCost() != null) entity.setTotalMaterialCost(requestDto.getTotalMaterialCost());
        if (requestDto.getSellingPrice() != null) entity.setSellingPrice(requestDto.getSellingPrice());
        if (requestDto.getEstimatedProfit() != null) entity.setEstimatedProfit(requestDto.getEstimatedProfit());

        if (requestDto.getAutoDeductStock() != null) entity.setAutoDeductStock(requestDto.getAutoDeductStock());
        if (requestDto.getIsActive() != null) entity.setIsActive(requestDto.getIsActive());

        // if ingredients sent in PATCH, replace the whole list (partial-item patch not supported)
        if (requestDto.getIngredients() != null) {
            replaceIngredients(entity, requestDto.getIngredients());
        }

        entity.setUpdatedAt(LocalDateTime.now());
        recalculateCosting(entity);

        RecipeEntity saved = recipeRepository.save(entity);
        return mapEntityToDto(saved);
    }

    @Override
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new NoSuchElementException("Recipe not found with id: " + id);
        }
        recipeRepository.deleteById(id);
    }

    // ---------------- helper / mapping methods ----------------

    private void mapDtoToEntity(RecipeRequestDto dto, RecipeEntity entity) {
        entity.setRecipeId(dto.getRecipeId());
        entity.setTenantId(dto.getTenantId());
        entity.setBranchId(dto.getBranchId());

        entity.setProductId(dto.getProductId());
        entity.setProductName(dto.getProductName());
        entity.setProductSku(dto.getProductSku());

        entity.setType(dto.getType());

        entity.setYieldQuantity(dto.getYieldQuantity());
        entity.setYieldUnit(dto.getYieldUnit());

        entity.setTotalMaterialCost(dto.getTotalMaterialCost());
        entity.setSellingPrice(dto.getSellingPrice());
        entity.setEstimatedProfit(dto.getEstimatedProfit());

        entity.setAutoDeductStock(dto.getAutoDeductStock());
        entity.setIsActive(dto.getIsActive());
    }

    private void replaceIngredients(RecipeEntity entity, List<IngredientRequestDto> ingredientDtos) {
        entity.getIngredients().clear();
        if (ingredientDtos == null) {
            return;
        }
        for (IngredientRequestDto dto : ingredientDtos) {
            RecipeIngredientEntity ingredient = new RecipeIngredientEntity();
            ingredient.setProductId(dto.getProductId());
            ingredient.setProductName(dto.getProductName());
            ingredient.setQuantity(dto.getQuantity());
            ingredient.setUnit(dto.getUnit());
            ingredient.setCostPerUnit(dto.getCostPerUnit());

            double totalCost = dto.getTotalCost() != null
                    ? dto.getTotalCost()
                    : (dto.getQuantity() != null && dto.getCostPerUnit() != null
                    ? dto.getQuantity() * dto.getCostPerUnit() : 0.0);
            ingredient.setTotalCost(totalCost);

            ingredient.setRecipe(entity);
            entity.getIngredients().add(ingredient);
        }
    }

    private RecipeResponseDto mapEntityToDto(RecipeEntity entity) {
        RecipeResponseDto dto = new RecipeResponseDto();
        dto.setId(entity.getId());
        dto.setRecipeId(entity.getRecipeId());
        dto.setTenantId(entity.getTenantId());
        dto.setBranchId(entity.getBranchId());

        dto.setProductId(entity.getProductId());
        dto.setProductName(entity.getProductName());
        dto.setProductSku(entity.getProductSku());

        dto.setType(entity.getType());

        dto.setYieldQuantity(entity.getYieldQuantity());
        dto.setYieldUnit(entity.getYieldUnit());

        dto.setTotalMaterialCost(entity.getTotalMaterialCost());
        dto.setSellingPrice(entity.getSellingPrice());
        dto.setEstimatedProfit(entity.getEstimatedProfit());

        dto.setAutoDeductStock(entity.getAutoDeductStock());
        dto.setIsActive(entity.getIsActive());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        List<IngredientResponseDto> ingredientDtos = new ArrayList<>();
        for (RecipeIngredientEntity ingredient : entity.getIngredients()) {
            IngredientResponseDto ingredientDto = new IngredientResponseDto();
            ingredientDto.setId(ingredient.getId());
            ingredientDto.setProductId(ingredient.getProductId());
            ingredientDto.setProductName(ingredient.getProductName());
            ingredientDto.setQuantity(ingredient.getQuantity());
            ingredientDto.setUnit(ingredient.getUnit());
            ingredientDto.setCostPerUnit(ingredient.getCostPerUnit());
            ingredientDto.setTotalCost(ingredient.getTotalCost());
            ingredientDtos.add(ingredientDto);
        }
        dto.setIngredients(ingredientDtos);

        return dto;
    }

    private void recalculateCosting(RecipeEntity entity) {
        if (entity.getIngredients() != null && !entity.getIngredients().isEmpty()) {
            double sum = 0.0;
            for (RecipeIngredientEntity ingredient : entity.getIngredients()) {
                if (ingredient.getTotalCost() != null) {
                    sum += ingredient.getTotalCost();
                }
            }
            entity.setTotalMaterialCost(sum);
        }

        if (entity.getSellingPrice() != null && entity.getTotalMaterialCost() != null) {
            entity.setEstimatedProfit(entity.getSellingPrice() - entity.getTotalMaterialCost());
        }
    }
}
