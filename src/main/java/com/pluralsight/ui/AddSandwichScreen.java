package com.pluralsight.ui;

import com.pluralsight.App;
import com.pluralsight.model.*;

import java.util.List;
import java.util.Scanner;

import static com.pluralsight.ui.StringUtil.titleCase;

public class AddSandwichScreen implements Screen {
    private Order order;

    public AddSandwichScreen(Order order) {
        this.order = order;
    }

    @Override
    public Screen run(Scanner userInput) {
        Sandwich sandwich = new Sandwich();
        // Loop over every category of sandwich component
        //   Each component category is one of:
        //    - Exactly 1 (no extra) | Bread
        //    - Exactly 1 (optional extra) | Meat, Cheese
        //    - Any number 0 or greater (only unique choices) | Everything else

        // This is not a for loop because the control flow is way
        // less confusing this way, bearing in mind that this cursor
        // can walk backward when the user selects "Go back".
        //
        // This cursor determines which category we're looking at adding
        // components from.
        int cursor = 0;
        List<MenuAdditionCategory> categories = App.data.getCategories();
        while (cursor < categories.size()) {
            MenuAdditionCategory category = categories.get(cursor);
            displayCategory(category);

            if (cursor > 0) {
                System.out.println("b) Go back");
            }
            if (category.isCanMany()) {
                System.out.printf("d) Done adding from %s%n", titleCase(category.getName()));
            }
            System.out.println("0) Cancel Sandwich");


            String choice = promptLine(userInput, "Choose: ");
            // Cases:
            // It's a number...
            //    0: Cancel sandwich
            //    N (in the list of options): Select option N, add it.
            //                                If !category.isCanMany(): Go to next category
            //    N (outside the list of options): Retry prompt
            // It's not a number...
            //    b && cursor > 0: Go back
            //    d && category.isCanMany(): Go to next category
            //    Anything else: Retry prompt

            try {
                int n = Integer.parseInt(choice);
                if (n == 0) {
                    // Cancel the current sandwich, without adding it to the order.
                    return new OrderScreen(order);
                } else if (n > 0 && n < category.getAdditions().size()) {
                    MenuAddition selection = category.getAdditions().get(n);
                    // TODO: prompt about wanting extra
                    sandwich.addComponent(new Addition(selection, false));
                    cursor += 1;
                } else {
                    // TODO: retry prompt
                }
            } catch (NumberFormatException e) {
                if (choice.equalsIgnoreCase("b")) {
                    cursor -= 1;
                    // TODO: remove elements of the additions list which have already been added?
                } else {
                    System.out.println("Please enter one of the listed options.");
                    // TODO: retry prompt
                }
            }
        }

        int bread = promptInt(userInput, "Choose: ");
        switch (bread) {
            case 0 -> {
                return new OrderScreen(order);
            }
            case 1, 2, 3, 4 -> {
            }
            default -> {
                throw new RuntimeException("TODO");
            }
        }
        ;
        String size = promptLine(userInput, "Sandwich Size: ");
        return new OrderScreen(order);
    }

    void horizontalRule(StringBuilder buf, int length) {
        for (int i = 0; i < length; i++) {
            buf.append("-");
        }
    }

    void displayCategory(MenuAdditionCategory category) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("# ");
        prompt.append(titleCase(category.getName()));
        prompt.append("\n");
        horizontalRule(prompt, category.getName().length() + 2);

        List<MenuAddition> menuAdditions = category.getAdditions();
        for (int i = 0; i < menuAdditions.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, titleCase(menuAdditions.get(i).getName()));
        }
    }

    void displayBreads() {
        System.out.println("# Bread");
        System.out.println("-------");
        System.out.println("1) White");
        System.out.println("2) Wheat");
        System.out.println("3) Rye");
        System.out.println("4) Wrap");
//        System.out.println("0) Cancel Sandwich");
    }

    void displaySizes() {
        System.out.println("# Sandwich Size");
        System.out.println("1) 4\"");
        System.out.println("2) 8\"");
        System.out.println("3) 12\"");
//        System.out.println("b) Go Back");
//        System.out.println("0) Cancel Sandwich");
    }

    void displayMeats() {
        System.out.println("1) Steak");
        System.out.println("2) Ham");
        System.out.println("3) Salami");
        System.out.println("4) Roast Beef");
        System.out.println("5) Chicken");
        System.out.println("6) Bacon");
    }

    // TODO: Do you want extra meat?
    void displayCheeses() {
        System.out.println("1) American");
        System.out.println("2) Provolone");
        System.out.println("3) Cheddar");
        System.out.println("4) Swiss");
    }

    // TODO: Do you want extra cheese?
    void displayRegularToppings() {
        System.out.println("1) Lettuce");
        System.out.println("2) Peppers");
        System.out.println("3) ");
    }
}
