package com.pluralsight.ui;

import com.pluralsight.model.Order;

import java.util.Scanner;

public class HomeScreen implements Screen {
    @Override
    public Screen run(Scanner scan) {
        while (true) {
            displayMenu();
            String choice = promptLine(scan, "Choose: ");

            switch (choice) {
                case "1" -> { return new OrderScreen(new Order()); }
                case "0" -> { return null; }
                default -> {}
            }
        }
    }

    void displayMenu() {
        System.out.println("1) New Order");
        System.out.println("0) Exit");
    }
}
