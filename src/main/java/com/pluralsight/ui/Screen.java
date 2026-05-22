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
}
