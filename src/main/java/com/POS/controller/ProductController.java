package com.POS.controller;

import com.POS.dto.requestDto.ProductRequestDto;
import com.POS.dto.resposneDto.ProductResponseDto;
import com.POS.service.ProductService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Note: No @Valid / security annotations used here as requested.
// JWT auth filter is assumed to already be configured globally in the project.

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1) CREATE PRODUCT
    // POST /api/v1/products
    @PostMapping("/create-product")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto response = productService.createProduct(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2) GET ALL PRODUCTS
    // GET /api/v1/products
    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> response = productService.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 3) GET PRODUCT BY PRODUCT ID
    // GET /api/v1/products/{productId}
    @GetMapping("/get-product-by-product-id/{productId}")
    public ResponseEntity<ProductResponseDto> getProductByProductId(@PathVariable String productId) {
        ProductResponseDto response = productService.getProductByProductId(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 4) GET PRODUCTS BY TENANT AND BRANCH (query params)
    // GET /api/v1/products/search?tenantId=TENANT-001&branchId=BRANCH-001
    @GetMapping("/get-product-by-tenant-and-branch/search")
    public ResponseEntity<List<ProductResponseDto>> getProductsByTenantAndBranch(
            @RequestParam String tenantId,
            @RequestParam String branchId) {
        List<ProductResponseDto> response = productService.getProductsByTenantAndBranch(tenantId, branchId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 5) FULL UPDATE (PUT) - replaces the whole product
    // PUT /api/v1/products/{productId}
    @PutMapping("/update-product/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable String productId,
            @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto response = productService.updateProduct(productId, requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 6) PARTIAL UPDATE (PATCH) - only updates fields sent in request body
    // PATCH /api/v1/products/{productId}
    @PatchMapping("/patch-product/{productId}")
    public ResponseEntity<ProductResponseDto> patchProduct(
            @PathVariable String productId,
            @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto response = productService.patchProduct(productId, requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 7) DELETE PRODUCT
    // DELETE /api/v1/products/{productId}
    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}
