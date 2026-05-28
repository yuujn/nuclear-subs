package com.pluralsight.ui;

import com.pluralsight.model.Order;
import com.pluralsight.model.Sandwich;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EditSandwichScreen implements Screen {
    private Order order;

    public EditSandwichScreen(Order order) {
        this.order = order;
    }

    @Override
    public Screen run(Scanner userInput) {
        List<Sandwich> sandwiches = listSandwiches(order);
        if (sandwiches.isEmpty()) {
            System.out.println("Cannot edit a sandwich when there are no sandwiches to edit.");
            return new OrderScreen(order);
        }
        for (int i = 0; i < sandwiches.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, sandwiches.get(i).generateOneLiner());
        }
        System.out.println("0) Cancel Edit Sandwich");

        while (true) {
            int choice = promptPositiveInt(userInput, "Choose: ");
            if (choice == 0) {
                return new OrderScreen(order);
            } else if (choice <= sandwiches.size()) {
                Sandwich selection = sandwiches.get(choice - 1);
                return AddSandwichScreen.editSandwich(order, selection);
            }
        }
    }

    private static List<Sandwich> listSandwiches(Order order) {
        return order.getItems().stream().flatMap(x -> {
            if (x instanceof Sandwich sandwich) {
                return Optional.of(sandwich).stream();
            } else {
                Optional<Sandwich> none = Optional.empty();
                return none.stream();
            }
        }).toList();
    }

    static boolean isAvailable(Order order) {
        return !listSandwiches(order).isEmpty();
    }
}
