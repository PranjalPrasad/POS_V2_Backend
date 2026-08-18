package com.POS.controller;

import com.POS.dto.requestDto.RecipeRequestDto;
import com.POS.dto.resposneDto.RecipeResponseDto;
import com.POS.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // CREATE
    @PostMapping("/create-recipe")
    public ResponseEntity<RecipeResponseDto> createRecipe(@RequestBody RecipeRequestDto requestDto) {
        RecipeResponseDto response = recipeService.createRecipe(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // READ - all
    @GetMapping("/get-all-recipes")
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    // READ - by primary key
    @GetMapping("/get-recipe-by-id/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    // READ - by business key (recipeId, e.g. REC-001)
    @GetMapping("/get-recipe-by-recipe-id/recipe-code/{recipeId}")
    public ResponseEntity<RecipeResponseDto> getRecipeByRecipeId(@PathVariable String recipeId) {
        return ResponseEntity.ok(recipeService.getRecipeByRecipeId(recipeId));
    }

    // READ - by tenant + branch
    @GetMapping("/get-recipes-by-tenant-and-branch/tenant/{tenantId}/branch/{branchId}")
    public ResponseEntity<List<RecipeResponseDto>> getRecipesByTenantAndBranch(
            @PathVariable String tenantId,
            @PathVariable String branchId) {
        return ResponseEntity.ok(recipeService.getRecipesByTenantAndBranch(tenantId, branchId));
    }

    // READ - active only
    @GetMapping("/get-active-recipes/active")
    public ResponseEntity<List<RecipeResponseDto>> getActiveRecipes() {
        return ResponseEntity.ok(recipeService.getActiveRecipes());
    }

    // UPDATE - full replace (ingredients list fully replaced too)
    @PutMapping("/update-recipe/{id}")
    public ResponseEntity<RecipeResponseDto> updateRecipe(
            @PathVariable Long id,
            @RequestBody RecipeRequestDto requestDto) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, requestDto));
    }

    // UPDATE - partial patch
    @PatchMapping("/patch-recipe/{id}")
    public ResponseEntity<RecipeResponseDto> patchRecipe(
            @PathVariable Long id,
            @RequestBody RecipeRequestDto requestDto) {
        return ResponseEntity.ok(recipeService.patchRecipe(id, requestDto));
    }

    // DELETE
    @DeleteMapping("/delete-recipe/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
