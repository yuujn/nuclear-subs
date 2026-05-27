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

        // UI Steps:
        // 1     : Size
        // 2..N  : Category[N - 1]
        // N + 1 : Would you like the sandwich toasted?

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
        final int preCategoryWalkSteps = 1;
        final int postCategoryWalkSteps = 2;
        List<MenuAdditionCategory> categories = App.data.getCategories();
        final int totalSteps = preCategoryWalkSteps + categories.size() + postCategoryWalkSteps;
        final int confirmationStep = totalSteps - 1;
        boolean reachedConfirmation = false;
        while (cursor < totalSteps) {

            if (cursor == 0) {
                List<Size> sizes = App.data.getSizes();
                displaySizes();
                System.out.println("0) Cancel sandwich");
                int sizeChoice = promptInt(userInput, "Choose: ");
                if (sizeChoice < 0 || sizeChoice > sizes.size()) {
                    continue;
                }
                if (sizeChoice == 0) {
                    return new OrderScreen(order);
                }
                sandwich.setSize(sizes.get(sizeChoice - 1));

                cursor += 1;
                continue;
            }

            if (cursor >= preCategoryWalkSteps + categories.size()) {
                int step = cursor - (preCategoryWalkSteps + categories.size());
                if (step == 0) {
                    boolean toasted = promptYesOrNo(userInput, "Want it toasted? ");
                    sandwich.setToasted(toasted);
                } else if (step == 1) {
                    reachedConfirmation = true;
                    System.out.println(sandwich.generateSummary());
                    System.out.println();
                    System.out.println("D) Done");
                    System.out.println("1) Pick Size");
                    for (int i = 0; i < categories.size(); i++) {
                        System.out.printf("%d) %s%n", i + 2, titleCase(categories.get(i).getName()));
                    }
                    System.out.printf("%d) Select Toasting%n", categories.size() + 2);
                    System.out.println("0) Cancel Sandwich");
                    boolean isConfirmed = false;
                    boolean confirmLoop = true;
                    while (confirmLoop) {
                        String choice = promptLine(userInput, "Choose: ");
                        if (choice.equalsIgnoreCase("d")) {
                            System.out.println("Adding sandwich to order.");
                            isConfirmed = true;
                            confirmLoop = false;
                        } else {
                            try {
                                int choiceIdx = Integer.parseInt(choice);
                                switch (choiceIdx) {
                                    case 0 -> {
                                        return new OrderScreen(order);
                                    }
                                    case 1 -> {
                                        cursor = 0;
                                        confirmLoop = false;
                                    }
                                    default -> {
                                        int catIdx = choiceIdx - 2;
                                        if (catIdx <= categories.size()) {
                                            cursor = preCategoryWalkSteps + catIdx;
                                            confirmLoop = false;
                                        } else {
                                            System.out.println("Please enter one of the listed options.");
                                        }
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter one of the listed options.");
                            }
                        }
                    }
                    if (!isConfirmed) { continue; }
                }

                cursor += 1;
                continue;
            }

            int categoryIdx = cursor - preCategoryWalkSteps;
            MenuAdditionCategory category = categories.get(categoryIdx);
            displayCategory(category);

            if (cursor > 0) {
                System.out.println("b) Go back");
            }
            if (category.isCanMany()) {
                // NON-PRIORITY TODO: Add "Remove component" option
//                System.out.printf("r) Remove c");
                System.out.printf("d) Done adding from %s%n", titleCase(category.getName()));
            }
            // If each step has already been visited, we offer the user the option
            // to go straight back to the confirmation step.
            if (reachedConfirmation) {
                System.out.println("f) Finish Sandwich");
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
                    MenuAddition selection = category.getAdditions().get(n - 1);
                    boolean wantsExtra = false;
                    if (selection.isCanExtra()) {
                        wantsExtra = promptYesOrNo(userInput, "Want extra? ");
                    }
                    sandwich.addComponent(new Addition(selection, wantsExtra));
                    if (!category.isCanMany()) {
                        cursor += 1;
                    }
                } else {
                    System.out.println("Please enter one of the listed options.");
                }
            } catch (NumberFormatException e) {
                if (choice.equalsIgnoreCase("b")) {
                    // TODO: remove elements of the additions list which have already been added?
                    cursor -= 1;
                } else if (choice.equalsIgnoreCase("d") && category.isCanMany()) {
                    cursor += 1;
                } else if (choice.equalsIgnoreCase("f") && reachedConfirmation) {
                    cursor = confirmationStep;
                } else {
                    System.out.println("Please enter one of the listed options.");
                }
            }
        }

        order.addItem(sandwich);
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

    void displaySizes() {
        System.out.println("# Size");
        System.out.println("--------");
        List<Size> sizes = App.data.getSizes();
        for (int i = 0; i < sizes.size(); i++) {
            Size size = sizes.get(i);
            System.out.printf("%d) %s (%s)%n", i + 1, titleCase(size.getName()), size.getMeasurement());
        }
    }
}
