package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.CustomerRequestDto;
import com.POS.dto.responseDto.CustomerResponseDto;
import com.POS.entity.CustomerEntity;
import com.POS.exception.CustomerNotFoundException;
import com.POS.repository.CustomerRepository;
import com.POS.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto requestDto) {
        if (requestDto.getCustomerId() != null && customerRepository.existsByCustomerId(requestDto.getCustomerId())) {
            throw new RuntimeException("Customer already exists with customerId: " + requestDto.getCustomerId());
        }

        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(requestDto.getCustomerId());
        entity.setTenantId(requestDto.getTenantId());
        entity.setBranchId(requestDto.getBranchId());
        entity.setName(requestDto.getName());
        entity.setEmail(requestDto.getEmail());
        entity.setPhone(requestDto.getPhone());
        entity.setAddress(requestDto.getAddress());
        entity.setCustomerGroup(requestDto.getCustomerGroup());
        entity.setStatus(requestDto.getStatus());
        entity.setIsActive(requestDto.getIsActive());
        entity.setTotalOrders(0);
        entity.setTotalSpent(0.0);

        CustomerEntity saved = customerRepository.save(entity);
        return mapToResponseDto(saved);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto getCustomerById(String customerId) {
        CustomerEntity entity = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with customerId: " + customerId));
        return mapToResponseDto(entity);
    }

    @Override
    public List<CustomerResponseDto> getCustomersByTenantAndBranch(String tenantId, String branchId) {
        return customerRepository.findByTenantIdAndBranchId(tenantId, branchId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponseDto> getCustomersByStatus(String status) {
        return customerRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto updateCustomer(String customerId, CustomerRequestDto requestDto) {
        CustomerEntity entity = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with customerId: " + customerId));

        // Full update — every field is overwritten
        entity.setTenantId(requestDto.getTenantId());
        entity.setBranchId(requestDto.getBranchId());
        entity.setName(requestDto.getName());
        entity.setEmail(requestDto.getEmail());
        entity.setPhone(requestDto.getPhone());
        entity.setAddress(requestDto.getAddress());
        entity.setCustomerGroup(requestDto.getCustomerGroup());
        entity.setStatus(requestDto.getStatus());
        entity.setIsActive(requestDto.getIsActive());

        CustomerEntity updated = customerRepository.save(entity);
        return mapToResponseDto(updated);
    }

    @Override
    public CustomerResponseDto patchCustomer(String customerId, CustomerRequestDto requestDto) {
        CustomerEntity entity = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with customerId: " + customerId));

        // Partial update — only overwrite fields that were actually sent
        if (requestDto.getTenantId() != null) {
            entity.setTenantId(requestDto.getTenantId());
        }
        if (requestDto.getBranchId() != null) {
            entity.setBranchId(requestDto.getBranchId());
        }
        if (requestDto.getName() != null) {
            entity.setName(requestDto.getName());
        }
        if (requestDto.getEmail() != null) {
            entity.setEmail(requestDto.getEmail());
        }
        if (requestDto.getPhone() != null) {
            entity.setPhone(requestDto.getPhone());
        }
        if (requestDto.getAddress() != null) {
            entity.setAddress(requestDto.getAddress());
        }
        if (requestDto.getCustomerGroup() != null) {
            entity.setCustomerGroup(requestDto.getCustomerGroup());
        }
        if (requestDto.getStatus() != null) {
            entity.setStatus(requestDto.getStatus());
        }
        if (requestDto.getIsActive() != null) {
            entity.setIsActive(requestDto.getIsActive());
        }

        CustomerEntity patched = customerRepository.save(entity);
        return mapToResponseDto(patched);
    }

    @Override
    public void deleteCustomer(String customerId) {
        CustomerEntity entity = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with customerId: " + customerId));
        customerRepository.delete(entity);
    }

    private CustomerResponseDto mapToResponseDto(CustomerEntity entity) {
        return new CustomerResponseDto(
                entity.getCustomerId(),
                entity.getTenantId(),
                entity.getBranchId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getCustomerGroup(),
                entity.getTotalOrders(),
                entity.getTotalSpent(),
                entity.getStatus(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
