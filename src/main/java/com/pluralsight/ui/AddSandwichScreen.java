package com.pluralsight.ui;

import com.pluralsight.model.Order;

import java.util.Scanner;

public class AddSandwichScreen implements Screen {
    private Order order;
    public AddSandwichScreen(Order order) {
        this.order = order;
    }
    @Override
    public Screen run(Scanner userInput) {
        System.out.println("Adding a sandwich...");
        return new OrderScreen(order);
    }
}
