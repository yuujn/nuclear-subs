package com.pluralsight.ui;

public class StringUtil {
    public static String titleCase(String phrase) {
        StringBuilder buf = new StringBuilder();
        boolean isFirstLetterOfWord = true;
        for (char c : phrase.toCharArray()) {
            // Capitalization is... complicated. But this'll work for us,
            // because we're working with English.
            if (isFirstLetterOfWord) {
                buf.append(Character.toTitleCase(c));
            } else {
                buf.append(c);
            }

            // Setup the next codepoint.
            isFirstLetterOfWord = Character.isWhitespace(c);
        }
        return buf.toString();
    }
}
