package com.prod.scorpion.repository;

import com.prod.scorpion.entities.productLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productLineRepository extends JpaRepository<productLine, String> {
    // This interface will inherit all CRUD operations from JpaRepository
    // No additional methods are needed unless specific queries are required
}
