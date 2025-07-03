package com.prod.scorpion.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class products {

    @Id
    @Column(name = "productcode", nullable = false, length = 15)
    private String productCode;

    @Column(name = "productname", nullable = false, length = 70)
    private String productName;

    @Column(name = "productline", nullable = false, length = 50)
    private String productLine;

    @Column(name = "productscale", nullable = false, length = 10)
    private String productScale;

    @Column(name = "productvendor", nullable = false, length = 50)
    private String productVendor;

    @Column(name = "productdescription", nullable = false, length = 4000)
    private String productDescription;

    @Column(name = "quantityinstock", nullable = false)
    private Integer quantityInStock;

    @Column(name = "buyprice", nullable = false)
    private Double buyPrice;

    @Column(name = "msrp", nullable = false)
    private Double msrp;
}
