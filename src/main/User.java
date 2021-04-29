package main;

import database.*;
import static database.DatabaseAccessor.CONNECTION;
import java.sql.*;
import java.util.*;
import payment.*;

/**
 * Models a user
 * A user has a payment card and an optional email address
 */
public class User implements Persistable, Retrievable {

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
        this.emailAddress = "";
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

    @Override
    public boolean persist() {
        final String QUERY = "INSERT INTO user VALUES('" + getEmailAddress() + "','" + getPaymentCard().getCardNumber() + "');";
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate(QUERY);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Retrievable retrieveOne(String columnName, String columnValue) {
       final String QUERY = "SELECT * FROM user WHERE " + columnName + 
                " = '" + columnValue + "';";
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet retrievedUserRS = statement.executeQuery(QUERY);
            if(retrievedUserRS.next()) {
                User user = new User();
                user.setEmailAddress(retrievedUserRS.getString("email_address"));
                if(PaymentCard.retrievePaymentCardType(retrievedUserRS.
                        getString("payment_card_number")).equals(PaymentCardType
                                .CREDIT_CARD.toString())) {
                    CreditCard cCard = new CreditCard();
                    cCard.setCardNumber(retrievedUserRS.getString("payment_card_number"));
                    cCard = (CreditCard) cCard.retrieveOne("card_number",
                            retrievedUserRS.getString("payment_card_number"));
                    user.setPaymentCard(cCard);
                    return user;
                } else if(PaymentCard.retrievePaymentCardType(retrievedUserRS.
                        getString("payment_card_number")).equals(PaymentCardType
                                .DEBIT_CARD.toString())) {
                    DebitCard dCard = new DebitCard();
                    dCard.setCardNumber(retrievedUserRS.getString("payment_card_number"));
                    dCard = (DebitCard) dCard.retrieveOne("card_number",
                            retrievedUserRS.getString("payment_card_number"));
                    user.setPaymentCard(dCard);
                    return user;
                }
               
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
   
}
