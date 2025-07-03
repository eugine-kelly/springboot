package com.prod.scorpion.repository;

import com.prod.scorpion.entities.customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepository extends JpaRepository<customers, Integer> {
}
