package com.pluralsight;

import com.pluralsight.ui.UserInterface;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(new Scanner(System.in));
        ui.run();
    }
}
