package com.POS.service;

import com.POS.dto.requestDto.ProductRequestDto;
import com.POS.dto.responseDto.ProductResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto requestDto, MultipartFile image) throws IOException;

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductByProductId(String productId);

    List<ProductResponseDto> getProductsByTenantAndBranch(String tenantId, String branchId);

    ProductResponseDto updateProduct(String productId, ProductRequestDto requestDto, MultipartFile image) throws IOException;

    ProductResponseDto patchProduct(String productId, ProductRequestDto requestDto, MultipartFile image) throws IOException;

    void deleteProduct(String productId);
}