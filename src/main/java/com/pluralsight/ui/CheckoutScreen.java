package com.pluralsight.ui;

import com.pluralsight.App;
import com.pluralsight.model.Order;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CheckoutScreen implements Screen {
    private final Order order;
    public CheckoutScreen(Order order) {
        this.order = order;
    }
    @Override
    public Screen run(Scanner userInput) {
        if (order.calculateTotal() == 0) {
            System.out.println("Cannot checkout without purchasing anything.");
            return new OrderScreen(order);
        }
        displayReceipt();
        waitForEnter(userInput, "Press Enter to continue...");
        displayMenu();
        while (true) {
            String choice = promptLine(userInput, "Choose: ");
            switch (choice) {
                case "1" -> {
                    try {
                        App.receiptWriter.writeOrder(LocalDateTime.now(), order);
                        return new HomeScreen();
                    } catch (IOException e) {
                        System.out.println("Failed to write receipt.");
                        System.out.println("Error: " + e);
                        System.out.println("Returning to prompt. You may attempt to resolve the issue before trying again.");
                    }
                }
                case "0" -> { return new OrderScreen(order); }
            }
        }
    }

    void displayReceipt() {
        System.out.println("# Receipt");
        System.out.println(order.generateReceipt());
    }

    void displayMenu() {
        System.out.println("1) Confirm");
        System.out.println("0) Cancel");
    }

    static boolean isAvailable(Order order) {
        return order.calculateTotal() != 0;
    }
}
