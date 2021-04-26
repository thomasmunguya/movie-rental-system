package payment;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DebitCard extends PaymentCard {

    public DebitCard() {
        super();
    }

    public DebitCard(int cardNumber, Date expiryDate, int pin, double balance) {
        super(cardNumber, expiryDate, pin, balance);
    }

    @Override
    public boolean persist() {
        final String QUERY = "INSERT INTO credit_card VALUES(" + getCardNumber() + ", '"
                + DateConvertor.convert(getExpiryDate()) + "', " + getPin() + ", "
                + getBalance() + ");";
        try(CONNECTION) {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate(QUERY);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
