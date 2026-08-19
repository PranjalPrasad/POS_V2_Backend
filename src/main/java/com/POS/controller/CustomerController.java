package com.POS.controller;

import com.POS.dto.requestDto.CustomerRequestDto;
import com.POS.dto.responseDto.CustomerResponseDto;
import com.POS.exception.CustomerNotFoundException;
import com.POS.service.CustomerService;
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
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create-customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDto requestDto) {
        try {
            CustomerResponseDto response = customerService.createCustomer(requestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-customers")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/get-customer-by-id/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable String customerId) {
        try {
            return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
        } catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-customers-by-tenant-and-branch/tenant/{tenantId}/branch/{branchId}")
    public ResponseEntity<List<CustomerResponseDto>> getCustomersByTenantAndBranch(
            @PathVariable String tenantId,
            @PathVariable String branchId) {
        return new ResponseEntity<>(
                customerService.getCustomersByTenantAndBranch(tenantId, branchId), HttpStatus.OK);
    }

    @GetMapping("/get-customers-by-status/status/{status}")
    public ResponseEntity<List<CustomerResponseDto>> getCustomersByStatus(@PathVariable String status) {
        return new ResponseEntity<>(customerService.getCustomersByStatus(status), HttpStatus.OK);
    }

    @PutMapping("/update-customer/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable String customerId,
                                            @RequestBody CustomerRequestDto requestDto) {
        try {
            return new ResponseEntity<>(customerService.updateCustomer(customerId, requestDto), HttpStatus.OK);
        } catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/patch-customer/{customerId}")
    public ResponseEntity<?> patchCustomer(@PathVariable String customerId,
                                           @RequestBody CustomerRequestDto requestDto) {
        try {
            return new ResponseEntity<>(customerService.patchCustomer(customerId, requestDto), HttpStatus.OK);
        } catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
