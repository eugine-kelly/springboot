package com.prod.scorpion.controller;


import com.prod.scorpion.entities.products;
import com.prod.scorpion.repository.productRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final productRepository productRepository;

    @GetMapping
    public ResponseEntity<List<products>> getProducts() {
        List<products> products = productRepository.findAll();
        log.info("Retrieved {} products", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<products> getProductById(@PathVariable String productCode) {
        products product = productRepository.findById(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found with code: " + productCode));
        log.info("Retrieved product with code: {}", product.getProductCode());
        return ResponseEntity.ok(product);

    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody products product) {
        products savedProduct = productRepository.save(product);
        log.info("Saving product with code: {}", savedProduct.getProductCode());
        return ResponseEntity.ok("Product saved successfully with code: " + savedProduct.getProductCode());
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<String> updateProduct(@PathVariable String productCode, @RequestBody products product) {
        if (productRepository.existsById(productCode)) {
            product.setProductCode(productCode);
            productRepository.save(product);
            log.info("Updated product: {}", productCode);
            return ResponseEntity.ok("Product updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productCode) {
        if (productRepository.existsById(productCode)) {
            productRepository.deleteById(productCode);
            log.info("Deleted product: {}", productCode);
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
}
