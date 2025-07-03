package com.prod.scorpion.repository;

import com.prod.scorpion.entities.products;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends JpaRepository<products, String> {
    // This interface will inherit all CRUD operations from JpaRepository
    // No additional methods are needed unless specific queries are required
}
