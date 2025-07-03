package com.prod.scorpion.repository;

import com.prod.scorpion.entities.offices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface officeRepository extends JpaRepository<offices, String> {
}
