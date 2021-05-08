package com.urjc.code.products.application.dto;

public class PurchaseDto {

    private int quantity;

    public PurchaseDto(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
