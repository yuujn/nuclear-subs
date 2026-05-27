package com.pluralsight.ui;

import com.pluralsight.model.Chips;
import com.pluralsight.model.Order;

import java.util.Scanner;

public class AddChipsScreen implements Screen {
    private Order order;
    public AddChipsScreen(Order order) {
        this.order = order;
    }
    @Override
    public Screen run(Scanner userInput) {
        displayTypeMenu();
        String choice = promptLine(userInput, "Choose: ");
        if (choice.equalsIgnoreCase("0")) {
            return new OrderScreen(order);
        }
        
        String type = switch (choice) {
            case "1" -> "Cool Ranch Doritos";
            case "2" -> "Hot Fries";
            case "3" -> "Baked BBQ Lays";
            case "4" -> "Donkey Tortilla Chips";
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };

        order.addItem(new Chips(type));

        return new OrderScreen(order);
    }

    void displayTypeMenu() {
        System.out.println("# Chip Types");
        System.out.println("--------------");
        System.out.println("1) Cool Ranch Doritos");
        System.out.println("2) Hot Fries");
        System.out.println("3) Baked BBQ Lays");
        System.out.println("4) Donkey Tortilla Chips");
        System.out.println("0) Cancel chips");
    }
}
