package com.prod.scorpion.controller;

import com.prod.scorpion.entities.offices;
import com.prod.scorpion.repository.officeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/offices")
@RequiredArgsConstructor

public class OfficeController {

    private final officeRepository officeRepository;

    @GetMapping
    public ResponseEntity<List<offices>> getOffices(){
        List<offices> offices = officeRepository.findAll();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOffice(@RequestBody offices offices) {

        offices savedOffice= officeRepository.save(offices);

//
        log.info("Saving office with code: {}", savedOffice.getOfficeCode());
        return ResponseEntity.ok("Office saved successfully with code: " + savedOffice.getOfficeCode());
    }






}
