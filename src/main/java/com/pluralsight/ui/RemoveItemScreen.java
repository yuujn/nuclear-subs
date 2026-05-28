package com.pluralsight.ui;

import com.pluralsight.model.LineItem;
import com.pluralsight.model.Order;

import java.util.List;
import java.util.Scanner;

public class RemoveItemScreen implements Screen {
    private Order order;

    public RemoveItemScreen(Order order) {
        this.order = order;
    }

    @Override
    public Screen run(Scanner userInput) {
        List<LineItem> items = order.getItems();
        if (items.isEmpty()) {
            System.out.println("Cannot remove an item when there are no items to remove.");
            return new OrderScreen(order);
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, items.get(i).generateOneLiner());
        }
        System.out.println("0) Cancel Remove Item");

        while (true) {
            int choice = promptPositiveInt(userInput, "Choose: ");
            if (choice == 0) {
                return new OrderScreen(order);
            } else if (choice <= items.size()) {
                System.out.println("Removing item.");
                items.remove(choice - 1);
                return new OrderScreen(order);
            }
        }
    }

    static boolean isAvailable(Order order) {
        return !order.getItems().isEmpty();
    }
}
