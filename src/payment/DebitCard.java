package payment;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;

public class DebitCard extends PaymentCard {

    public DebitCard() {
        super();
    }

    public DebitCard(String cardNumber, String cardName, LocalDate expiryDate, int pin, double balance) {
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

}
