package com.pluralsight;

import com.pluralsight.data.SandwichDataReader;
import com.pluralsight.ui.UserInterface;

import java.io.IOException;
import java.util.Scanner;

public class App {
    // This information is considered part of the application,
    // as specified in the assignment. For the purposes of this
    // assignment, hardcoding this data would have been acceptable.
    //
    // Placing it in a static is no worse.
    public static SandwichDataReader data;
    public static void main(String[] args) {
        try {
            data = SandwichDataReader.readCSVFiles("data/sandwich");
        } catch (IOException e) {
            System.out.println("Failed to load sandwich menu data.");
            throw new RuntimeException(e);
        }
        UserInterface ui = new UserInterface(new Scanner(System.in));
        ui.run();
    }
}
