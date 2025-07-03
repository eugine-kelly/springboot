package com.prod.scorpion.repository;

import com.prod.scorpion.entities.employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository <employees, Integer>{
}
