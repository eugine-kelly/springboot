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
@Table(name = "productlines")

public class productLine {


    @Id
    @Column(name = "productline", nullable = false, length = 50)
    private String productLine;

    @Column(name = "textdescription" ,nullable = false, length = 4000)
    private String textDescription;

    @Column(name = "htmldescription", nullable = false, length = 4000)
    private String htmlDescription;

    @Column(name = "image")
    private byte[] image;
}
