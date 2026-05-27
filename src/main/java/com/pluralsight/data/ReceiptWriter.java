package com.pluralsight.data;

import com.pluralsight.model.Addition;
import com.pluralsight.model.LineItem;
import com.pluralsight.model.Order;
import com.pluralsight.model.Sandwich;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    private String receiptsDirectory;

    public ReceiptWriter(String receiptsDirectory) {
        this.receiptsDirectory = receiptsDirectory;
    }

    public void writeOrder(LocalDateTime moment, Order order) throws IOException {
        String receiptFileName = moment.format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss'.txt'"));
        String receiptPath = receiptsDirectory + "/" + receiptFileName;

        FileWriter fileWriter = new FileWriter(receiptPath);
        BufferedWriter bufWriter = new BufferedWriter(fileWriter);

        bufWriter.write(order.generateReceipt());

        bufWriter.close();
    }
}
