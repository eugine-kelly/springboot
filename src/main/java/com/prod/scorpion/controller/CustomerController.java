package com.prod.scorpion.controller;


import com.prod.scorpion.entities.customers;
import com.prod.scorpion.repository.customerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final customerRepository customerRepository;

    @GetMapping
    public ResponseEntity<List<customers>> getCustomers() {
        List<customers> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<customers> getCustomerById(@PathVariable Integer customerNumber) {
        customers customer = customerRepository.findById(customerNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerNumber));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@RequestBody customers customer) {
        customers savedCustomer = customerRepository.save(customer);
        log.info("Saving customer with number: {}", savedCustomer.getCustomerNumber());
        return ResponseEntity.ok("Customer saved successfully with number: " + savedCustomer.getCustomerNumber());
    }

    @DeleteMapping("/{customerNumber}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerNumber) {
        customers customer = customerRepository.findById(customerNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerNumber));
        customerRepository.delete(customer);
        log.info("Deleted customer with number: {}", customerNumber);
        return ResponseEntity.ok("Customer deleted successfully with number: " + customerNumber);
    }

}
