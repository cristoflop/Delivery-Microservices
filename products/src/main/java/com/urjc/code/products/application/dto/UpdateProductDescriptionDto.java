package com.urjc.code.products.application.dto;

public class UpdateProductDescriptionDto {

    private String description;

    public UpdateProductDescriptionDto(String desription) {
        this.description = desription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
