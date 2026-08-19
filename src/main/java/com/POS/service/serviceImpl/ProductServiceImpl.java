package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.ProductRequestDto;
import com.POS.dto.responseDto.ProductResponseDto;
import com.POS.entity.Product;
import com.POS.exception.ProductNotFoundException;
import com.POS.repository.ProductRepository;
import com.POS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {

        if (requestDto.getProductId() != null
                && productRepository.existsByProductId(requestDto.getProductId())) {
            throw new RuntimeException("Product already exists with productId: " + requestDto.getProductId());
        }

        Product product = new Product();
        mapRequestToEntity(requestDto, product);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);
        return mapEntityToResponse(saved);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> response = new ArrayList<>();
        for (Product product : products) {
            response.add(mapEntityToResponse(product));
        }
        return response;
    }

    @Override
    public ProductResponseDto getProductByProductId(String productId) {
        Product product = findProductOrThrow(productId);
        return mapEntityToResponse(product);
    }

    @Override
    public List<ProductResponseDto> getProductsByTenantAndBranch(String tenantId, String branchId) {
        List<Product> products = productRepository.findByTenantIdAndBranchId(tenantId, branchId);
        List<ProductResponseDto> response = new ArrayList<>();
        for (Product product : products) {
            response.add(mapEntityToResponse(product));
        }
        return response;
    }

    @Override
    public ProductResponseDto updateProduct(String productId, ProductRequestDto requestDto) {
        Product product = findProductOrThrow(productId);
        mapRequestToEntity(requestDto, product);
        product.setUpdatedAt(LocalDateTime.now());
        Product updated = productRepository.save(product);
        return mapEntityToResponse(updated);
    }

    @Override
    public ProductResponseDto patchProduct(String productId, ProductRequestDto requestDto) {
        Product product = findProductOrThrow(productId);

        // Only update fields that are actually sent (non-null) in the request
        if (requestDto.getName() != null) product.setName(requestDto.getName());
        if (requestDto.getCode() != null) product.setCode(requestDto.getCode());
        if (requestDto.getSku() != null) product.setSku(requestDto.getSku());
        if (requestDto.getBarcode() != null) product.setBarcode(requestDto.getBarcode());
        if (requestDto.getDescription() != null) product.setDescription(requestDto.getDescription());
        if (requestDto.getProductType() != null) product.setProductType(requestDto.getProductType());

        if (requestDto.getCategoryId() != null) product.setCategoryId(requestDto.getCategoryId());
        if (requestDto.getCategoryName() != null) product.setCategoryName(requestDto.getCategoryName());
        if (requestDto.getBrandId() != null) product.setBrandId(requestDto.getBrandId());
        if (requestDto.getBrandName() != null) product.setBrandName(requestDto.getBrandName());

        if (requestDto.getCostPrice() != null) product.setCostPrice(requestDto.getCostPrice());
        if (requestDto.getSellingPrice() != null) product.setSellingPrice(requestDto.getSellingPrice());
        if (requestDto.getMrp() != null) product.setMrp(requestDto.getMrp());
        if (requestDto.getCurrency() != null) product.setCurrency(requestDto.getCurrency());

        if (requestDto.getUnitId() != null) product.setUnitId(requestDto.getUnitId());
        if (requestDto.getUnitName() != null) product.setUnitName(requestDto.getUnitName());
        if (requestDto.getUnitCode() != null) product.setUnitCode(requestDto.getUnitCode());

        if (requestDto.getTaxId() != null) product.setTaxId(requestDto.getTaxId());
        if (requestDto.getTaxName() != null) product.setTaxName(requestDto.getTaxName());
        if (requestDto.getTaxRate() != null) product.setTaxRate(requestDto.getTaxRate());

        if (requestDto.getTrackInventory() != null) product.setTrackInventory(requestDto.getTrackInventory());
        if (requestDto.getTrackBatch() != null) product.setTrackBatch(requestDto.getTrackBatch());
        if (requestDto.getTrackExpiry() != null) product.setTrackExpiry(requestDto.getTrackExpiry());
        if (requestDto.getMinimumStock() != null) product.setMinimumStock(requestDto.getMinimumStock());
        if (requestDto.getMaximumStock() != null) product.setMaximumStock(requestDto.getMaximumStock());

        if (requestDto.getVariants() != null) product.setVariants(requestDto.getVariants());
        if (requestDto.getIsActive() != null) product.setIsActive(requestDto.getIsActive());

        product.setUpdatedAt(LocalDateTime.now());

        Product patched = productRepository.save(product);
        return mapEntityToResponse(patched);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = findProductOrThrow(productId);
        productRepository.delete(product);
    }

    // ---------------- Helper methods ----------------

    private Product findProductOrThrow(String productId) {
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found with productId: " + productId);
        }
        return optionalProduct.get();
    }

    private void mapRequestToEntity(ProductRequestDto dto, Product product) {
        product.setProductId(dto.getProductId());
        product.setTenantId(dto.getTenantId());
        product.setBranchId(dto.getBranchId());

        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setSku(dto.getSku());
        product.setBarcode(dto.getBarcode());
        product.setDescription(dto.getDescription());
        product.setProductType(dto.getProductType());

        product.setCategoryId(dto.getCategoryId());
        product.setCategoryName(dto.getCategoryName());
        product.setBrandId(dto.getBrandId());
        product.setBrandName(dto.getBrandName());

        product.setCostPrice(dto.getCostPrice());
        product.setSellingPrice(dto.getSellingPrice());
        product.setMrp(dto.getMrp());
        product.setCurrency(dto.getCurrency());

        product.setUnitId(dto.getUnitId());
        product.setUnitName(dto.getUnitName());
        product.setUnitCode(dto.getUnitCode());

        product.setTaxId(dto.getTaxId());
        product.setTaxName(dto.getTaxName());
        product.setTaxRate(dto.getTaxRate());

        product.setTrackInventory(dto.getTrackInventory());
        product.setTrackBatch(dto.getTrackBatch());
        product.setTrackExpiry(dto.getTrackExpiry());
        product.setMinimumStock(dto.getMinimumStock());
        product.setMaximumStock(dto.getMaximumStock());

        product.setVariants(dto.getVariants());
        product.setIsActive(dto.getIsActive());
    }

    private ProductResponseDto mapEntityToResponse(Product product) {
        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(product.getId());
        dto.setProductId(product.getProductId());
        dto.setTenantId(product.getTenantId());
        dto.setBranchId(product.getBranchId());

        dto.setName(product.getName());
        dto.setCode(product.getCode());
        dto.setSku(product.getSku());
        dto.setBarcode(product.getBarcode());
        dto.setDescription(product.getDescription());
        dto.setProductType(product.getProductType());

        dto.setCategoryId(product.getCategoryId());
        dto.setCategoryName(product.getCategoryName());
        dto.setBrandId(product.getBrandId());
        dto.setBrandName(product.getBrandName());

        dto.setCostPrice(product.getCostPrice());
        dto.setSellingPrice(product.getSellingPrice());
        dto.setMrp(product.getMrp());
        dto.setCurrency(product.getCurrency());

        dto.setUnitId(product.getUnitId());
        dto.setUnitName(product.getUnitName());
        dto.setUnitCode(product.getUnitCode());

        dto.setTaxId(product.getTaxId());
        dto.setTaxName(product.getTaxName());
        dto.setTaxRate(product.getTaxRate());

        dto.setTrackInventory(product.getTrackInventory());
        dto.setTrackBatch(product.getTrackBatch());
        dto.setTrackExpiry(product.getTrackExpiry());
        dto.setMinimumStock(product.getMinimumStock());
        dto.setMaximumStock(product.getMaximumStock());

        dto.setVariants(product.getVariants());
        dto.setIsActive(product.getIsActive());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());

        return dto;
    }
}
