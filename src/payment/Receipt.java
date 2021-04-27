package payment;

import database.Persistable;

import java.time.*;
import java.util.Random;

/**
 * Models a receipt
 */
public class Receipt implements Persistable {
    private LocalDate issueDate;
    private Instant issueTime;
    private String details;
    private int receiptNumber;

    public Receipt() {

    }

    public Receipt(LocalDate issueDate, Instant issueTime, String details, int receiptNumber) {
        this.issueDate = issueDate;
        this.issueTime = issueTime;
        this.details = details;
        this.receiptNumber = receiptNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Instant getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Instant issueTime) {
        this.issueTime = issueTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @Override
    public boolean persist() {
//        Timestamp timestamp = Timestamp.from(getIssueTime());
//        String QUERY = "INSERT INTO receipt VALUES('" + getIssueDate() + timestamp.
        return true;
    }

    /**
     * Generates a random 10 digit receipt number
     * @return the receipt number
     */
    public int generateReceiptNumber() {
        final int RECEIPT_NUMBER_LENGTH = 10;
        Random rand = new Random();
        String receiptNumberString = "1234567890";
        StringBuilder receiptNumberSb = new StringBuilder();
        for(int i = 0; i < RECEIPT_NUMBER_LENGTH; i++) {
            receiptNumberSb.append(receiptNumberString.charAt(rand.nextInt(RECEIPT_NUMBER_LENGTH)));
        }
        return Integer.parseInt(receiptNumberSb.toString());
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }

        if(other == null) {
            return false;
        }

        if(!(other instanceof Receipt)) {
            return false;
        }

        if(this.getReceiptNumber() != ((Receipt) other).getReceiptNumber()) {
            return false;
        }

        return true;
    }
}
