package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.PaymentUpdateRequestDto;
import com.POS.dto.requestDto.SaleItemRequestDto;
import com.POS.dto.requestDto.SalePatchRequestDto;
import com.POS.dto.requestDto.SaleRequestDto;
import com.POS.dto.responseDto.SaleItemResponseDto;
import com.POS.dto.responseDto.SaleResponseDto;
import com.POS.entity.CustomerEntity;
import com.POS.entity.SaleEntity;
import com.POS.entity.SaleItemEntity;
import com.POS.repository.CustomerRepository;
import com.POS.repository.SaleRepository;
import com.POS.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public SaleResponseDto createSale(SaleRequestDto requestDto) {

        // resolve or create customer
        CustomerEntity customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseGet(() -> {
                    CustomerEntity newCustomer = new CustomerEntity();
                    newCustomer.setCustomerId(requestDto.getCustomerId());
                    newCustomer.setName(requestDto.getCustomerName());
                    newCustomer.setPhone(requestDto.getCustomerPhone());
                    newCustomer.setCreatedAt(LocalDateTime.now());
                    return customerRepository.save(newCustomer);
                });

        SaleEntity sale = new SaleEntity();
        sale.setSaleId("SALE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        sale.setSaleNumber(requestDto.getSaleNumber());
        sale.setTenantId(requestDto.getTenantId());
        sale.setBranchId(requestDto.getBranchId());
        sale.setCustomer(customer);

        sale.setOrderId(requestDto.getOrderId());
        sale.setOrderType(requestDto.getOrderType());
        sale.setOrderSource(requestDto.getOrderSource());

        sale.setSubtotal(requestDto.getSubtotal());
        sale.setDiscountAmount(requestDto.getDiscountAmount());
        sale.setTaxAmount(requestDto.getTaxAmount());
        sale.setRoundOff(requestDto.getRoundOff());
        sale.setGrandTotal(requestDto.getGrandTotal());

        sale.setPaymentStatus(requestDto.getPaymentStatus());
        sale.setPaidAmount(requestDto.getPaidAmount());
        sale.setDueAmount(requestDto.getDueAmount());
        sale.setPaymentMethod(requestDto.getPaymentMethod());
        sale.setTransactionReference(requestDto.getTransactionReference());

        sale.setStatus(requestDto.getStatus());
        sale.setNotes(requestDto.getNotes());
        sale.setCreatedAt(LocalDateTime.now());
        sale.setUpdatedAt(LocalDateTime.now());

        List<SaleItemEntity> itemEntities = new ArrayList<>();
        if (requestDto.getItems() != null) {
            for (SaleItemRequestDto itemDto : requestDto.getItems()) {
                SaleItemEntity item = new SaleItemEntity();
                item.setSale(sale);
                item.setProductId(itemDto.getProductId());
                item.setProductName(itemDto.getProductName());
                item.setVariantId(itemDto.getVariantId());
                item.setQuantity(itemDto.getQuantity());
                item.setUnit(itemDto.getUnit());
                item.setUnitPrice(itemDto.getUnitPrice());
                item.setDiscountType(itemDto.getDiscountType());
                item.setDiscountValue(itemDto.getDiscountValue());
                item.setDiscountAmount(itemDto.getDiscountAmount());
                item.setTaxId(itemDto.getTaxId());
                item.setTaxRate(itemDto.getTaxRate());
                item.setTaxAmount(itemDto.getTaxAmount());
                item.setLineTotal(itemDto.getLineTotal());
                itemEntities.add(item);
            }
        }
        sale.setItems(itemEntities);

        SaleEntity saved = saleRepository.save(sale);
        return mapToResponseDto(saved);
    }

    @Override
    public SaleResponseDto getSaleById(String saleId) {
        SaleEntity sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + saleId));
        return mapToResponseDto(sale);
    }

    @Override
    public SaleResponseDto getSaleByNumber(String saleNumber) {
        SaleEntity sale = saleRepository.findBySaleNumber(saleNumber)
                .orElseThrow(() -> new RuntimeException("Sale not found with number: " + saleNumber));
        return mapToResponseDto(sale);
    }

    @Override
    public List<SaleResponseDto> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleResponseDto> getSalesByCustomer(String customerId) {
        return saleRepository.findByCustomer_CustomerId(customerId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public SaleResponseDto updateSaleStatus(String saleId, String status) {
        SaleEntity sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + saleId));
        sale.setStatus(status);
        sale.setUpdatedAt(LocalDateTime.now());
        SaleEntity updated = saleRepository.save(sale);
        return mapToResponseDto(updated);
    }

    @Override
    public SaleResponseDto updatePayment(String saleId, PaymentUpdateRequestDto paymentDto) {
        SaleEntity sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + saleId));

        sale.setPaymentStatus(paymentDto.getPaymentStatus());
        sale.setPaidAmount(paymentDto.getPaidAmount());
        sale.setDueAmount(paymentDto.getDueAmount());
        sale.setPaymentMethod(paymentDto.getPaymentMethod());
        sale.setTransactionReference(paymentDto.getTransactionReference());
        sale.setUpdatedAt(LocalDateTime.now());

        SaleEntity updated = saleRepository.save(sale);
        return mapToResponseDto(updated);
    }



    @Override
    public SaleResponseDto patchSale(String saleId, SalePatchRequestDto patchDto) {

        SaleEntity sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + saleId));

        // ---- only update fields that are actually sent (non-null) ----
        if (patchDto.getSaleNumber() != null) {
            sale.setSaleNumber(patchDto.getSaleNumber());
        }
        if (patchDto.getBranchId() != null) {
            sale.setBranchId(patchDto.getBranchId());
        }

        if (patchDto.getCustomerName() != null && sale.getCustomer() != null) {
            sale.getCustomer().setName(patchDto.getCustomerName());
        }
        if (patchDto.getCustomerPhone() != null && sale.getCustomer() != null) {
            sale.getCustomer().setPhone(patchDto.getCustomerPhone());
        }

        if (patchDto.getOrderType() != null) {
            sale.setOrderType(patchDto.getOrderType());
        }
        if (patchDto.getOrderSource() != null) {
            sale.setOrderSource(patchDto.getOrderSource());
        }

        if (patchDto.getItems() != null) {
            sale.getItems().clear();
            for (SaleItemRequestDto itemDto : patchDto.getItems()) {
                SaleItemEntity item = new SaleItemEntity();
                item.setSale(sale);
                item.setProductId(itemDto.getProductId());
                item.setProductName(itemDto.getProductName());
                item.setVariantId(itemDto.getVariantId());
                item.setQuantity(itemDto.getQuantity());
                item.setUnit(itemDto.getUnit());
                item.setUnitPrice(itemDto.getUnitPrice());
                item.setDiscountType(itemDto.getDiscountType());
                item.setDiscountValue(itemDto.getDiscountValue());
                item.setDiscountAmount(itemDto.getDiscountAmount());
                item.setTaxId(itemDto.getTaxId());
                item.setTaxRate(itemDto.getTaxRate());
                item.setTaxAmount(itemDto.getTaxAmount());
                item.setLineTotal(itemDto.getLineTotal());
                sale.getItems().add(item);
            }
        }

        if (patchDto.getSubtotal() != null) {
            sale.setSubtotal(patchDto.getSubtotal());
        }
        if (patchDto.getDiscountAmount() != null) {
            sale.setDiscountAmount(patchDto.getDiscountAmount());
        }
        if (patchDto.getTaxAmount() != null) {
            sale.setTaxAmount(patchDto.getTaxAmount());
        }
        if (patchDto.getRoundOff() != null) {
            sale.setRoundOff(patchDto.getRoundOff());
        }
        if (patchDto.getGrandTotal() != null) {
            sale.setGrandTotal(patchDto.getGrandTotal());
        }

        if (patchDto.getPaymentStatus() != null) {
            sale.setPaymentStatus(patchDto.getPaymentStatus());
        }
        if (patchDto.getPaidAmount() != null) {
            sale.setPaidAmount(patchDto.getPaidAmount());
        }
        if (patchDto.getDueAmount() != null) {
            sale.setDueAmount(patchDto.getDueAmount());
        }
        if (patchDto.getPaymentMethod() != null) {
            sale.setPaymentMethod(patchDto.getPaymentMethod());
        }
        if (patchDto.getTransactionReference() != null) {
            sale.setTransactionReference(patchDto.getTransactionReference());
        }

        if (patchDto.getStatus() != null) {
            sale.setStatus(patchDto.getStatus());
        }
        if (patchDto.getNotes() != null) {
            sale.setNotes(patchDto.getNotes());
        }

        sale.setUpdatedAt(LocalDateTime.now());

        SaleEntity updated = saleRepository.save(sale);
        return mapToResponseDto(updated);
    }
    @Override
    public void deleteSale(String saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new RuntimeException("Sale not found with id: " + saleId);
        }
        saleRepository.deleteById(saleId);
    }

    // ---------- Mapper ----------
    private SaleResponseDto mapToResponseDto(SaleEntity sale) {
        SaleResponseDto dto = new SaleResponseDto();
        dto.setSaleId(sale.getSaleId());
        dto.setSaleNumber(sale.getSaleNumber());
        dto.setTenantId(sale.getTenantId());
        dto.setBranchId(sale.getBranchId());

        if (sale.getCustomer() != null) {
            dto.setCustomerId(sale.getCustomer().getCustomerId());
            dto.setCustomerName(sale.getCustomer().getName());
            dto.setCustomerPhone(sale.getCustomer().getPhone());
        }

        dto.setOrderId(sale.getOrderId());
        dto.setOrderType(sale.getOrderType());
        dto.setOrderSource(sale.getOrderSource());

        List<SaleItemResponseDto> itemDtos = new ArrayList<>();
        for (SaleItemEntity item : sale.getItems()) {
            SaleItemResponseDto itemDto = new SaleItemResponseDto();
            itemDto.setItemId(item.getItemId());
            itemDto.setProductId(item.getProductId());
            itemDto.setProductName(item.getProductName());
            itemDto.setVariantId(item.getVariantId());
            itemDto.setQuantity(item.getQuantity());
            itemDto.setUnit(item.getUnit());
            itemDto.setUnitPrice(item.getUnitPrice());
            itemDto.setDiscountType(item.getDiscountType());
            itemDto.setDiscountValue(item.getDiscountValue());
            itemDto.setDiscountAmount(item.getDiscountAmount());
            itemDto.setTaxId(item.getTaxId());
            itemDto.setTaxRate(item.getTaxRate());
            itemDto.setTaxAmount(item.getTaxAmount());
            itemDto.setLineTotal(item.getLineTotal());
            itemDtos.add(itemDto);
        }
        dto.setItems(itemDtos);

        dto.setSubtotal(sale.getSubtotal());
        dto.setDiscountAmount(sale.getDiscountAmount());
        dto.setTaxAmount(sale.getTaxAmount());
        dto.setRoundOff(sale.getRoundOff());
        dto.setGrandTotal(sale.getGrandTotal());

        dto.setPaymentStatus(sale.getPaymentStatus());
        dto.setPaidAmount(sale.getPaidAmount());
        dto.setDueAmount(sale.getDueAmount());
        dto.setPaymentMethod(sale.getPaymentMethod());
        dto.setTransactionReference(sale.getTransactionReference());

        dto.setStatus(sale.getStatus());
        dto.setNotes(sale.getNotes());
        dto.setCreatedAt(sale.getCreatedAt());
        dto.setUpdatedAt(sale.getUpdatedAt());

        return dto;
    }
}
