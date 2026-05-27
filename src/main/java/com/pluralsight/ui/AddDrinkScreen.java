package com.pluralsight.ui;

import com.pluralsight.model.Drink;
import com.pluralsight.model.Order;

import java.util.Scanner;

public class AddDrinkScreen implements Screen {
    private Order order;
    public AddDrinkScreen(Order order) {
        this.order = order;
    }
    @Override
    public Screen run(Scanner userInput) {
        displaySizeMenu();
        String choice = promptLine(userInput, "Choose: ");
        int choiceNumber;
        try {
            choiceNumber = Integer.parseUnsignedInt(choice);
            if (choiceNumber > 3) {
                System.out.println("Please choose one of the displayed options.");
                return this;
            }
            if (choiceNumber == 0) {
                return new OrderScreen(order);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter one of the following: 0, 1, 2, or 3.");
            return this;
        }

        String size = switch (choiceNumber) {
            case 1 -> "Small";
            case 2 -> "medium";
            case 3 -> "large";
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };

        displayFlavorMenu();
        do {
            choice = promptLine(userInput, "Choose: ");
        } while (!choice.toLowerCase().matches("[01234b]"));
        if (choice.equalsIgnoreCase("b")) {
            return this;
        }
        if (choice.equalsIgnoreCase("0")) {
            return new OrderScreen(order);
        }

        String flavor = switch (choice) {
            case "1" -> "coke";
            case "2" -> "pepsi";
            case "3" -> "lemonade";
            case "4" -> "apple juice";
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };

        order.addItem(new Drink(size, flavor));

        return new OrderScreen(order);
    }

    void displaySizeMenu() {
        System.out.println("# Sizes");
        System.out.println("---------");
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");
        System.out.println("0) Cancel drink");
    }

    void displayFlavorMenu() {
        System.out.println("# Drink Flavors");
        System.out.println("1) Coke");
        System.out.println("2) Pepsi");
        System.out.println("3) Lemonade");
        System.out.println("4) Apple Juice");
        System.out.println("b) Go back");
        System.out.println("0) Cancel drink");
    }
}
