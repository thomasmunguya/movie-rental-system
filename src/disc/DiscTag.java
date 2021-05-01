package disc;

import static database.DatabaseAccessor.CONNECTION;
import database.Persistable;
import database.Retrievable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;

/**
 * Models a disc tag
 * A disc tag is represented by the date, and the time the disc was rented and by the date and the time it was returned
 */
public class DiscTag implements Retrievable, Persistable{

    private LocalDate dateRented;
    private Instant timeRented;
    private LocalDate dateReturned;
    private Instant timeReturned;
    private String id;

    public DiscTag() {
    }

    
    public DiscTag(LocalDate dateRented, Instant timeRented, LocalDate dateReturned,
            Instant timeReturned, String id) {
        this.dateRented = dateRented;
        this.timeRented = timeRented;
        this.dateReturned = dateReturned;
        this.timeReturned = timeReturned;
        this.id = id;
    }

    public LocalDate getDateRented() {
        return dateRented;
    }

    public void setDateRented(LocalDate dateRented) {
        this.dateRented = dateRented;
    }

    public Instant getTimeRented() {
        return timeRented;
    }

    public void setTimeRented(Instant timeRented) {
        this.timeRented = timeRented;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Instant getTimeReturned() {
        return timeReturned;
    }

    public void setTimeReturned(Instant timeReturned) {
        this.timeReturned = timeReturned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    @Override
    public Retrievable retrieveOne(String columnName, String columnValue) {
       final String QUERY = "SELECT * FROM disc_tag WHERE " + columnName
               + " = '" + columnValue + "';";
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet discTagsRS = statement.executeQuery(QUERY);
            if(discTagsRS.next()) {
                DiscTag discTag = new DiscTag();
                discTag.setDateRented(LocalDate.parse(discTagsRS.getString("date_rented")));
                discTag.setTimeRented(Instant.parse(discTagsRS.getString("time_rented")));
                discTag.setDateReturned(LocalDate.parse(discTagsRS.getString("date_returned")));
                discTag.setTimeReturned(Instant.parse(discTagsRS.getString("time_returned")));
                discTag.setId(discTagsRS.getString("id"));
                return discTag;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean persist() {
         final String QUERY = "INSERT INTO disc_tag VALUES('"
                + getId() + "',"
                + " '"  + getDateRented() + "',"
                + " '" + getTimeRented() + "',"
                + " '" + getDateReturned() + "',"
                + " '" + getTimeReturned() + "')"
                + " ON DUPLICATE KEY UPDATE "
                + "date_returned = '" + getDateReturned() + "',"
                + " time_returned = '" + getTimeReturned()+ "';";
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate(QUERY);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
