package com.prod.scorpion.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "orderdetails")
public class orderdetails {

    @EmbeddedId
    private orderdetailsId orderdetailsId;


    @MapsId("orderNumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ordernumber", nullable = false)
    private orders orders;

    @MapsId("productCode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productcode", nullable = false)
    private products products;

    @Column(name = "quantityordered", nullable = false)
    private Integer quantityOrdered;

    @Column(name = "priceeach", nullable = false)
    private Double priceEach;

    @Column(name = "orderlinenumber", nullable = false)
    private Integer orderLineNumber;

    public Integer getOrderNumber() {
        return orderdetailsId.getOrderNumber();
    }
    public String getProductCode() {
        return orderdetailsId.getProductCode();
    }
    public void setOrderNumber(Integer orderNumber) {
        orderdetailsId.setOrderNumber(orderNumber);
    }
    public void setProductCode(String productCode) {
        orderdetailsId.setProductCode(productCode);
    }



}
