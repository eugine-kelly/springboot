package com.prod.scorpion.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class orders {

    @Id
    @Column(name = "ordernumber", nullable = false)
    private Integer orderNumber;

    @Column(name = "orderdate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "requireddate", nullable = false)
    private LocalDate requiredDate;

    @Column(name = "shippeddate")
    private LocalDate shippedDate;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Lob
    @Column(name = "comments", length = 200)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customernumber", nullable = false)
    private customers customersNumber;

}
