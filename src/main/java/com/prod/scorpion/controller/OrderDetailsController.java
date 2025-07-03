package com.prod.scorpion.controller;

import com.prod.scorpion.entities.orderdetails;
import com.prod.scorpion.entities.orderdetailsId;
import com.prod.scorpion.repository.orderDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/orderdetails")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final orderDetailsRepository orderDetailsRepository;

    @GetMapping
    public ResponseEntity<List<orderdetails>> getOrderDetails() {
        List<orderdetails> orderDetailsList = orderDetailsRepository.findAll();
        log.info("Retrieved {} order details", orderDetailsList.size());
        return ResponseEntity.ok(orderDetailsList);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<orderdetails> getOrderDetailsById(@PathVariable Integer orderNumber) {
        List<orderdetails> orderDetailsList = orderDetailsRepository.findByOrderdetailsIdOrderNumber(orderNumber);

        if (orderDetailsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        orderdetails orderDetails = orderDetailsList.get(0);
        log.info("Retrieved order details with order number: {}", orderDetails.getOrderNumber());
        return ResponseEntity.ok(orderDetails);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOrderDetails(@RequestBody orderdetails orderDetails) {
        try {
            // Ensure the composite key is properly set
            if (orderDetails.getOrderdetailsId() == null) {
                orderdetailsId id = new orderdetailsId();
                orderDetails.setOrderdetailsId(id);
            }

            orderdetails savedOrderDetails = orderDetailsRepository.save(orderDetails);
            log.info("Saving order details with order number: {} and product code: {}",
                    savedOrderDetails.getOrderNumber(), savedOrderDetails.getProductCode());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Order details saved successfully with order number: " + savedOrderDetails.getOrderNumber()
                            + " and product code: " + savedOrderDetails.getProductCode());
        } catch (Exception e) {
            log.error("Error saving order details: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to save order details: " + e.getMessage());
        }
    }

    @PutMapping("/{orderNumber}/{productCode}")
    public ResponseEntity<String> updateOrderDetails(
            @PathVariable Integer orderNumber,
            @PathVariable String productCode,
            @RequestBody orderdetails orderDetails) {

        orderdetailsId id = new orderdetailsId();
        id.setOrderNumber(orderNumber);
        id.setProductCode(productCode);

        Optional<orderdetails> existingOrderDetails = orderDetailsRepository.findById(id);

        if (existingOrderDetails.isEmpty()) {
            log.warn("Order details not found for order: {} and product: {}", orderNumber, productCode);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order details not found for order: " + orderNumber + " and product: " + productCode);
        }

        orderdetails existing = existingOrderDetails.get();

        // Updating only the fields that can be changed (not the composite key fields)
        existing.setQuantityOrdered(orderDetails.getQuantityOrdered());
        existing.setPriceEach(orderDetails.getPriceEach());
        existing.setOrderLineNumber(orderDetails.getOrderLineNumber());

        try {
            orderdetails updatedOrderDetails = orderDetailsRepository.save(existing);
            log.info("Updated order details for order: {} and product: {}", orderNumber, productCode);
            return ResponseEntity.ok("Order details updated successfully");
        } catch (Exception e) {
            log.error("Error updating order details: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to update order details: " + e.getMessage());
        }
    }


    @DeleteMapping("/{orderNumber}/{productCode}")
    public ResponseEntity<String> deleteOrderDetails(
            @PathVariable Integer orderNumber,
            @PathVariable String productCode) {

        orderdetailsId id = new orderdetailsId();
        id.setOrderNumber(orderNumber);
        id.setProductCode(productCode);

        Optional<orderdetails> existingOrderDetails = orderDetailsRepository.findById(id);

        if (existingOrderDetails.isEmpty()) {
            log.warn("Order details not found for order: {} and product: {}", orderNumber, productCode);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order details not found for order: " + orderNumber + " and product: " + productCode);
        }

        try {
            orderDetailsRepository.delete(existingOrderDetails.get());
            log.info("Deleted order details for order: {} and product: {}", orderNumber, productCode);
            return ResponseEntity.ok("Order details deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting order details: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to delete order details: " + e.getMessage());
        }
    }
    }
