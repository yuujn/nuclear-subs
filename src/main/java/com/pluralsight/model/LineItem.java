package com.pluralsight.model;

import java.util.stream.Stream;

public interface LineItem {
    String getName();
    String getReceiptEntry();
    double getPrice();
}
