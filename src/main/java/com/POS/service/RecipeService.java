package com.POS.service;


import com.POS.dto.requestDto.RecipeRequestDto;
import com.POS.dto.resposneDto.RecipeResponseDto;

import java.util.List;

public interface RecipeService {

    RecipeResponseDto createRecipe(RecipeRequestDto requestDto);

    List<RecipeResponseDto> getAllRecipes();

    RecipeResponseDto getRecipeById(Long id);

    RecipeResponseDto getRecipeByRecipeId(String recipeId);

    List<RecipeResponseDto> getRecipesByTenantAndBranch(String tenantId, String branchId);

    List<RecipeResponseDto> getActiveRecipes();

    // Full update - all fields + full ingredients list expected
    RecipeResponseDto updateRecipe(Long id, RecipeRequestDto requestDto);

    // Partial update - only non-null top-level fields applied;
    // ingredients list, if sent, fully replaces the existing list
    RecipeResponseDto patchRecipe(Long id, RecipeRequestDto requestDto);

    void deleteRecipe(Long id);
}
