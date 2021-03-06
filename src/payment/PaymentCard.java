package payment;

import database.Persitable;

import java.util.*;

/**
 * Models a payment card
 * A payment card has a card number, an expiry date, a pin and a balance
 */
public abstract class PaymentCard implements Persitable {
    private int cardNumber;
    private Date expiryDate;
    private int pin;
    private double balance;

    public PaymentCard() {

    }

    public PaymentCard(int cardNumber, Date expiryDate, int pin, double balance) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.pin = pin;
        this.balance = balance;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Releases funds from the payment card
     * @param amount the amount to release
     * @return true if fund release was successful or false if fund release was denied for some reason
     */
    public boolean releaseFunds(double amount) {
        if(amount <= getBalance()) {
            setBalance(getBalance() - amount);
            if(persist()) {
                return true;
            }
        }
        setBalance(getBalance() + amount);
        return false;

    }

    /**
     * Validates a payment card using the Luhn algorithm
     * @param paymentCard the payment card whose number is to be verified
     * @return true if the payment card number is valid or false if otherwise
     */
    public boolean isValidCardNumber(PaymentCard paymentCard) {
        int[] ints = new int[paymentCard.getCardNumber() + "".length()];
        for (int i = 0; i < paymentCard.getCardNumber() + "".length(); i++) {
            ints[i] = Integer.parseInt(paymentCard.getCardNumber() + "".substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean persist() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }

        if(other == null) {
            return false;
        }

        if(!(other instanceof PaymentCard)) {
            return false;
        }

        if(this.getCardNumber() != ((PaymentCard) other).getCardNumber()) {
            return false;
        }

        return true;
    }
}
