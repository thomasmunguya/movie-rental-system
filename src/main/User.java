package main;

import payment.PaymentCard;

/**
 * Models a user
 * A user has a payment card and an optional email address
 */
public class User {
    private String emailAddress;
    private PaymentCard paymentCard;

    /**
     * Constructs a User
     */
    public User() {

    }

    /**
     * Constructs a User with a payment card
     * @param paymentCard
     */
    public User(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    /**
     * Constructs a User with an email address and a payment card
     * @param emailAddress
     * @param paymentCard
     */
    public User(String emailAddress, PaymentCard paymentCard) {
        this.emailAddress = emailAddress;
        this.paymentCard = paymentCard;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }

        if(other == null) {
            return false;
        }

        if(!(other instanceof User)) {
            return false;
        }

        if(!this.getEmailAddress().equals(((User) other).getEmailAddress())) {
           return false;
        }

        return true;
    }
}
