package com.pluralsight.ui;

public class UserInterface {
    public void run() {
        Screen currentScreen = new HomeScreen();

        while (currentScreen != null) {
            currentScreen = currentScreen.run();
        }
    }
}
