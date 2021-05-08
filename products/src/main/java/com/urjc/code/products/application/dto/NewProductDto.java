package com.urjc.code.products.application.dto;

public class NewProductDto {

    private String name;

    private String description;

    private int stock;

    public NewProductDto(String name, String description, int stock) {
        this.name = name;
        this.description = description;
        this.stock = stock;
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
