package payment;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;

public class CreditCard extends PaymentCard {
    private double creditLimit;

    public CreditCard() {
        super();
    }

    public CreditCard(String cardNumber, String cardName, LocalDate expiryDate, int pin, double balance, double creditLimit) {
        super(cardNumber, cardName, expiryDate, pin, balance);
        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean persist() {
        final String QUERY = "INSERT INTO credit_card VALUES('" + getCardNumber() + "', '"
                + getCardName() + "', " + getBalance() + ", '" + getExpiryDate()+ "', "
                + getPin() + ", " + getCreditLimit() + ");";
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate(QUERY);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
