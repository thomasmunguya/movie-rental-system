package payment;

import static database.DatabaseAccessor.CONNECTION;
import database.Retrievable;
import java.sql.*;
import java.util.*;

public class DebitCard extends PaymentCard {

    public DebitCard() {
        super();
    }

    public DebitCard(String cardNumber, String cardName, String expiryDate, String pin, double balance) {
        super(cardNumber, cardName, expiryDate, pin, balance);
    }

    @Override
    public boolean persist() {
        final String QUERY = "INSERT INTO debit_card VALUES('" + getCardNumber() + "', '"
                + getCardName() + "', " + getBalance() + ", '" + getExpiryDate()+ "', "
                + getPin() + ");";
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate(QUERY);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static List<DebitCard> retrieveAll() {
        final String QUERY = "SELECT * FROM debit_card;";

        final List<DebitCard> retrievedDebitCards = new ArrayList<>();
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet retrievedCCardRS = statement.executeQuery(QUERY);
            while(retrievedCCardRS.next()) {
                DebitCard debitCard = new DebitCard();
                debitCard.setCardNumber(retrievedCCardRS.getString("card_number"));
                debitCard.setCardName(retrievedCCardRS.getString("card_name"));
                debitCard.setBalance(retrievedCCardRS.getDouble("balance"));
                debitCard.setExpiryDate(retrievedCCardRS.getString("expiry_date"));
                debitCard.setPin(retrievedCCardRS.getString("pin"));
                retrievedDebitCards.add(debitCard);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return retrievedDebitCards;
    }
    
    @Override
    public Retrievable retrieveOne(String columnName, String columnValue) {
        final String QUERY = "SELECT * FROM debit_card WHERE " + columnName + 
                " = '" + columnValue + "';";
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet retrievedCCardRS = statement.executeQuery(QUERY);
            if(retrievedCCardRS.next()) {
                CreditCard debitCard = new CreditCard();
                debitCard.setCardNumber(retrievedCCardRS.getString("card_number"));
                debitCard.setCardName(retrievedCCardRS.getString("card_name"));
                debitCard.setBalance(retrievedCCardRS.getDouble("balance"));
                debitCard.setCreditLimit(retrievedCCardRS.getDouble("credit_limit"));
                debitCard.setExpiryDate(retrievedCCardRS.getString("expiry_date"));
                debitCard.setPin(retrievedCCardRS.getString("pin"));
                return debitCard;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean releaseFunds(double amount) {
        if(amount <= getBalance()) {
            setBalance(getBalance() - amount);
            if(persist()) {
                return true;
            }
        }
        return false;
    }

}
