package com.prod.scorpion.controller;


import com.prod.scorpion.entities.employees;
import com.prod.scorpion.repository.employeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final employeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<employees>> getEmployees(){
        List<employees> employeesList = employeeRepository.findAll();
        log.info("Retrieved {} employees", employeesList.size());
        return ResponseEntity.ok(employeesList);
    }

    @GetMapping("/{employeeNumber}")
    public ResponseEntity<employees> getEmployeeById(@PathVariable Integer employeeNumber) {
        employees employee = employeeRepository.findById(employeeNumber)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeNumber));
        log.info("Retrieved employee with number: {}", employee.getEmployeeNumber());
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(@RequestBody employees employee) {
        employees savedEmployee = employeeRepository.save(employee);
        log.info("Saving employee with number: {}", savedEmployee.getEmployeeNumber());
        return ResponseEntity.ok("Employee saved successfully with number: " + savedEmployee.getEmployeeNumber());
    }

    @PutMapping("/{employeeNumber}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer employeeNumber, @RequestBody employees employeeDetails) {
        employees existingEmployee = employeeRepository.findById(employeeNumber)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeNumber));

        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setExtension(employeeDetails.getExtension());
        existingEmployee.setJobTitle(employeeDetails.getJobTitle());
        existingEmployee.setOfficeCode(employeeDetails.getOfficeCode());
        existingEmployee.setReportsTo(employeeDetails.getReportsTo());


        employees updatedEmployee = employeeRepository.save(existingEmployee);
        log.info("Updated employee with number: {}", updatedEmployee.getEmployeeNumber());
        return ResponseEntity.ok("Employee updated successfully with number: " + updatedEmployee.getEmployeeNumber());
    }

    @DeleteMapping("/{employeeNumber}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeNumber) {
        employees employee = employeeRepository.findById(employeeNumber)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeNumber));
        employeeRepository.delete(employee);
        log.info("Deleted employee with number: {}", employeeNumber);
        return ResponseEntity.ok("Employee deleted successfully with number: " + employeeNumber);
    }
}
