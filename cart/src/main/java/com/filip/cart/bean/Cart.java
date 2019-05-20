package com.filip.cart.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cart {

    private int totalItems;

    private double totalPrice;

    private List<Product> products;

    public Cart(int totalItems, double totalPrice, List<Product> products) {
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
        this.products = products;
    }

}