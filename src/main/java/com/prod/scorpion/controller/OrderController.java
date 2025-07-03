package com.prod.scorpion.controller;


import com.prod.scorpion.entities.orders;
import com.prod.scorpion.repository.orderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final orderRepository orderRepository;

    @GetMapping
    public ResponseEntity<List<orders>> getOrders() {
        List<orders> ordersList = orderRepository.findAll();
        log.info("Retrieved {} orders", ordersList.size());
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<orders> getOrderById(@PathVariable Integer orderNumber) {
        orders order = orderRepository.findById(orderNumber)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderNumber));
        log.info("Retrieved order with number: {}", order.getOrderNumber());
        return ResponseEntity.ok(order);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOrder(@RequestBody orders order) {
        orders savedOrder = orderRepository.save(order);
        log.info("Saving order with number: {}", savedOrder.getOrderNumber());
        return ResponseEntity.ok("Order saved successfully with number: " + savedOrder.getOrderNumber());
    }

    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer orderNumber) {
        orders existingOrder = orderRepository.findById(orderNumber)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderNumber));

        orderRepository.delete(existingOrder);
        log.info("Deleted order with number: {}", existingOrder.getOrderNumber());
        return ResponseEntity.ok("Order deleted successfully with number: " + existingOrder.getOrderNumber());
    }
}
