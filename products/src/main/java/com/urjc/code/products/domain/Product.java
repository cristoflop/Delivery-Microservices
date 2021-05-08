package com.urjc.code.products.domain;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private int stock;

    public Product(String description, int stock) {
        this.description = description;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
