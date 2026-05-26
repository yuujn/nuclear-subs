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
        displayReceipt();
        waitForEnter(userInput, "Press Enter to continue...");
        displayMenu();
        while (true) {
            String choice = promptLine(userInput, "Choose: ");
            switch (choice) {
                case "1" -> {
                    // TODO: save receipt file
                    return new HomeScreen();
                }
                case "0" -> { return new OrderScreen(order); }
            }
        }
    }

    void displayReceipt() {
        System.out.println("# Receipt");
        // TODO: display order details
    }

    void displayMenu() {
        System.out.println("1) Confirm");
        System.out.println("0) Cancel");
    }
}
