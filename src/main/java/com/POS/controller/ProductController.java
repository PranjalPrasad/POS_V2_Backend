package com.POS.controller;

import com.POS.dto.requestDto.ProductRequestDto;
import com.POS.dto.responseDto.ProductResponseDto;
import com.POS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/create-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> createProduct(
            @ModelAttribute ProductRequestDto requestDto,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        ProductResponseDto response = productService.createProduct(requestDto, image);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> response = productService.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-product-by-product-id/{productId}")
    public ResponseEntity<ProductResponseDto> getProductByProductId(@PathVariable String productId) {
        ProductResponseDto response = productService.getProductByProductId(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-product-by-tenant-and-branch/search")
    public ResponseEntity<List<ProductResponseDto>> getProductsByTenantAndBranch(
            @RequestParam String tenantId,
            @RequestParam String branchId) {
        List<ProductResponseDto> response = productService.getProductsByTenantAndBranch(tenantId, branchId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update-product/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable String productId,
            @ModelAttribute ProductRequestDto requestDto,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        ProductResponseDto response = productService.updateProduct(productId, requestDto, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "/patch-product/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> patchProduct(
            @PathVariable String productId,
            @ModelAttribute ProductRequestDto requestDto,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        ProductResponseDto response = productService.patchProduct(productId, requestDto, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}