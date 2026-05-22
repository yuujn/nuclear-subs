package com.pluralsight.ui;

import com.pluralsight.model.Order;

import java.util.Scanner;

public class CheckoutScreen implements Screen {
    private Order order;
    public CheckoutScreen(Order order) {
        this.order = order;
    }
    @Override
    public Screen run(Scanner userInput) {
        System.out.println("TODO: Checking out...");
        return new OrderScreen(order);
    }
}
