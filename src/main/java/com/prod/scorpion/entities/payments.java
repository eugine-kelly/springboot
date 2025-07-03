package com.prod.scorpion.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class payments {

    @EmbeddedId
    private paymentsId paymentsId;

    @MapsId("customerNumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customernumber", nullable = false)
    private customers customersNumber;

    @Column(name = "paymentDate", nullable = false, length = 50)
    private LocalDate paymentDate;

    @Column(name = "amount", nullable = false)
    private Double amount;
}
