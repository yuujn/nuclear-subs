package com.pluralsight.ui;

import com.pluralsight.model.Order;

import java.util.Scanner;

public class AddDrinkScreen implements Screen {
    private Order order;
    public AddDrinkScreen(Order order) {
        this.order = order;
    }
    @Override
    public Screen run(Scanner userInput) {
        System.out.println("TODO: Adding drink...");
        return new OrderScreen(order);
    }
}
