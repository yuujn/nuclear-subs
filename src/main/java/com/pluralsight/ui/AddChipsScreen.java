package com.pluralsight.ui;

import com.pluralsight.model.Order;

import java.util.Scanner;

public class AddChipsScreen implements Screen {
    private Order order;
    public AddChipsScreen(Order order) {
        this.order = order;
    }
    @Override
    public Screen run(Scanner userInput) {
        System.out.println("TODO: Adding chips...");
        return new OrderScreen(order);
    }
}
