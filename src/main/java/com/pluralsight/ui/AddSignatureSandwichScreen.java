package com.pluralsight.ui;

import com.pluralsight.App;
import com.pluralsight.data.SignatureSandwich;
import com.pluralsight.model.Order;

import java.util.List;
import java.util.Scanner;

public class AddSignatureSandwichScreen implements Screen {
    private final Order order;

    public AddSignatureSandwichScreen(Order order) {
        this.order = order;
    }

    @Override
    public Screen run(Scanner userInput) {
        List<SignatureSandwich> signatures = App.data.getSignatureSandwiches();
        for (int i = 0; i < signatures.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, signatures.get(i).getName());
        }
        System.out.println("0) Cancel Sandwich");

        while (true) {
            try {
                int choice = promptPositiveInt(userInput, "Choose: ");
                if (choice == 0) {
                    return new OrderScreen(order);
                } else if (choice <= signatures.size()) {
                    return new AddSandwichScreen(order, signatures.get(choice - 1).makeSandwich());
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter one of the listed options.");
            }
        }
    }
}
