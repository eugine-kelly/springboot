package com.prod.scorpion.repository;

import com.prod.scorpion.entities.orderdetails;
import com.prod.scorpion.entities.orderdetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderDetailsRepository extends JpaRepository<orderdetails, orderdetailsId> {
    List<orderdetails> findByOrderdetailsIdOrderNumber(Integer orderNumber);
}
