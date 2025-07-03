package com.prod.scorpion.repository;

import com.prod.scorpion.entities.orderdetails;
import com.prod.scorpion.entities.orderdetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderDetailsRepository extends JpaRepository<orderdetails, orderdetailsId> {
}
