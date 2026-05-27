package com.pluralsight.ui;

import com.pluralsight.model.Order;

import java.util.Scanner;

public class OrderScreen implements Screen {
    private final Order order;
    // To avoid accidents, constructing an OrderScreen
    // always requires specifying what Order it is working
    // with.
    // It will be a very clear mistake if anything which
    // is not supposed to be creating a new order calls
    // `new Order()`.
    public OrderScreen(Order order) {
        this.order = order;
    }
    @Override
    public Screen run(Scanner scan) {
        while (true) {
            System.out.println(order.generateSummary());
            displayMenu();
            String choice = promptLine(scan, "Choose: ");
            switch (choice) {
                case "1" -> { return new AddSandwichScreen(order); }
                case "2" -> { return new AddSignatureSandwichScreen(order); }
                case "3" -> { return new AddDrinkScreen(order); }
                case "4" -> { return new AddChipsScreen(order); }
                case "5" -> { return new CheckoutScreen(order); }
                case "0" -> { return new HomeScreen(); }
                default -> {}
            }
        }
    }
    void displayMenu() {
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Signature Sandwich");
        System.out.println("3) Add Drink");
        System.out.println("4) Add Chips");
        if (CheckoutScreen.isAvailable(order)) {
            System.out.println("5) Checkout");
        }
        System.out.println("0) Cancel Order");
    }
}
