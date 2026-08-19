package com.POS.service;


import com.POS.dto.requestDto.RecipeRequestDto;
import com.POS.dto.responseDto.RecipeResponseDto;

import java.util.List;

public interface RecipeService {

    RecipeResponseDto createRecipe(RecipeRequestDto requestDto);

    List<RecipeResponseDto> getAllRecipes();

    RecipeResponseDto getRecipeById(Long id);

    RecipeResponseDto getRecipeByRecipeId(String recipeId);

    List<RecipeResponseDto> getRecipesByTenantAndBranch(String tenantId, String branchId);

    List<RecipeResponseDto> getActiveRecipes();

    RecipeResponseDto updateRecipe(Long id, RecipeRequestDto requestDto);

    RecipeResponseDto patchRecipe(Long id, RecipeRequestDto requestDto);

    void deleteRecipe(Long id);
}
