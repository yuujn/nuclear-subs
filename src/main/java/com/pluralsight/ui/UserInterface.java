package com.pluralsight.ui;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scan;
    public UserInterface(Scanner scan) {
        this.scan = scan;
    }
    public void run() {
        Screen currentScreen = new HomeScreen();

        while (currentScreen != null) {
            currentScreen = currentScreen.run(scan);
        }
    }
}
