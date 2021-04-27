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
        final String QUERY = "INSERT INTO debit_card VALUES(" + getCardNumber() + ", '"
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
    
    public static void main(String[] args) {
        DebitCard myCard = new DebitCard();
        myCard.setCardNumber("4383755000308625");
        myCard.setExpiryDate(LocalDate.now());
        myCard.setPin(1234);
        myCard.setBalance(22200);
        
        if(isValidCard(myCard)) {
            myCard.persist();
        }
    }

}
