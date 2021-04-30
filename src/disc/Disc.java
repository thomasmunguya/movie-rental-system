package disc;

import database.*;
import static database.DatabaseAccessor.CONNECTION;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import payment.CreditCard;

/**
 * Models a disc
 * A disc has an ID by which it is recognized and a disc tag by which it can be traced
 */
public class Disc implements Persistable, Retrievable {

    private int discId;
    private DiscTag discTag;

    public  Disc() {

    }

    public Disc(int discId, DiscTag discTag) {
        this.discId = discId;
        this.discTag = discTag;
    }

    public int getDiscId() {
        return discId;
    }

    public void setDiscId(int discId) {
        this.discId = discId;
    }

    public DiscTag getDiscTag() {
        return discTag;
    }

    public void setDiscTag(DiscTag discTag) {
        this.discTag = discTag;
    }

    @Override
    public boolean persist() {
//        final String QUERY = "INSERT INTO disc VALUES(" + getCardNumber() + ", '"
//                + DateConvertor.convert(getExpiryDate()) + "', " + getPin() + ", "
//                + getBalance() + ", " + getCreditLimit() + ");";
//        try(CONNECTION) {
//            Statement statement = CONNECTION.createStatement();
//            statement.executeUpdate(QUERY);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return true;
        return true;
    }
    
    @Override
    public Retrievable retrieveOne(String columnName, String columnValue) {
        final String QUERY = "SELECT * FROM disc WHERE " + columnName + 
                " = '" + columnValue + "';";
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet retrievedDiscRS = statement.executeQuery(QUERY);
            if(retrievedDiscRS.next()) {
                
//                return creditCard;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
