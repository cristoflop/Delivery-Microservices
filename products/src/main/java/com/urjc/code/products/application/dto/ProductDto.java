package com.urjc.code.products.application.dto;

public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private int stock;

    public ProductDto(Long id, String name, String description, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

}
