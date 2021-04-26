package payment;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class CreditCard extends PaymentCard {
    private double creditLimit;

    public CreditCard() {
        super();
    }

    public CreditCard(int cardNumber, Date expiryDate, int pin, double balance, double creditLimit) {
        super(cardNumber, expiryDate, pin, balance);
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
        final String QUERY = "INSERT INTO credit_card VALUES(" + getCardNumber() + ", '"
                + DateConvertor.convert(getExpiryDate()) + "', " + getPin() + ", "
                + getBalance() + ", " + getCreditLimit() + ");";
        try(CONNECTION) {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate(QUERY);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
