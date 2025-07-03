package com.prod.scorpion.controller;


import com.prod.scorpion.entities.productLine;
import com.prod.scorpion.repository.productLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/productLine")
@RequiredArgsConstructor
public class ProductLineController {

    private final productLineRepository productLineRepository;

    @GetMapping
    public ResponseEntity<List<productLine>> getProductLines() {
        List<productLine> productLines = productLineRepository.findAll();
        log.info("Retrieved {} product lines", productLines.size());
        return ResponseEntity.ok(productLines);
    }

    @GetMapping("/{productLine}")
    public ResponseEntity<productLine> getProductLineById(@PathVariable String productLine) {
        productLine productLineEntity = productLineRepository.findById(productLine)
                .orElseThrow(() -> new RuntimeException("Product line not found with id: " + productLine));
        log.info("Retrieved product line with id: {}", productLineEntity.getProductLine());
        return ResponseEntity.ok(productLineEntity);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProductLine(@RequestBody productLine productLine) {
        productLine savedProductLine = productLineRepository.save(productLine);
        log.info("Saving product line with id: {}", savedProductLine.getProductLine());
        return ResponseEntity.ok("Product line saved successfully with id: " + savedProductLine.getProductLine());
    }

    @PutMapping("/{productLineName}")
    public ResponseEntity<String> updateProductLine(@PathVariable String productLineName, @RequestBody productLine productLine) {
        if (productLineRepository.existsById(productLineName)) {
            productLine.setProductLine(productLineName);
            productLineRepository.save(productLine);
            log.info("Updated product line: {}", productLineName);
            return ResponseEntity.ok("Product line updated successfully");
        }
        return new ResponseEntity<>("Product line not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{productLineName}")
    public ResponseEntity<String> deleteProductLine(@PathVariable String productLineName) {
        if (productLineRepository.existsById(productLineName)) {
            productLineRepository.deleteById(productLineName);
            log.info("Deleted product line: {}", productLineName);
            return ResponseEntity.ok("Product line deleted successfully");
        }
        return new ResponseEntity<>("Product line not found", HttpStatus.NOT_FOUND);
    }
}
