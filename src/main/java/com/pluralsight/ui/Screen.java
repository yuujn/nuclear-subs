package com.pluralsight.ui;

import java.util.Scanner;

public interface Screen {
    Screen run(Scanner userInput);


    // Utility methods for the various screens.
    // These could easily be somewhere else, but they're here for now.
    default String promptLine(Scanner userInput, String prompt) {
        System.out.print(prompt);
        return userInput.nextLine();
    }

    default int promptInt(Scanner userInput, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException ignored) {
                System.out.println("Please enter a number.");
            }
        }
    }

    @SuppressWarnings("unused")
    default int promptPositiveInt(Scanner userInput, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int number = Integer.parseInt(userInput.nextLine());
                if (number >= 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Please enter a positive number.");
        }
    }

    default boolean promptYesOrNo(Scanner userInput, String prompt) {
        while (true) {
            System.out.print(prompt);
            String choice = userInput.nextLine();
            switch (choice.toLowerCase()) {
                case "y", "yes" -> { return true; }
                case "n", "no" -> { return false; }
                default -> System.out.println("Please enter a yes or no (y/yes/n/no).");
            }
        }
    }

    default void waitForEnter(Scanner userInput, String notice) {
        System.out.println(notice);
        String ignored = userInput.nextLine();
    }
}
