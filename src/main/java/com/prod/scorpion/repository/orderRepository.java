package com.prod.scorpion.repository;

import com.prod.scorpion.entities.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRepository extends JpaRepository <orders, Integer>{
}
