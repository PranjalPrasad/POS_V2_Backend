package com.POS.service;


import com.POS.dto.requestDto.ProductRequestDto;
import com.POS.dto.resposneDto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto requestDto);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductByProductId(String productId);

    List<ProductResponseDto> getProductsByTenantAndBranch(String tenantId, String branchId);

    ProductResponseDto updateProduct(String productId, ProductRequestDto requestDto);

    ProductResponseDto patchProduct(String productId, ProductRequestDto requestDto);

    void deleteProduct(String productId);
}
