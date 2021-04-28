package main;

import database.*;
import static database.DatabaseAccessor.CONNECTION;
import disc.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import payment.Receipt;

public class Rental implements Persistable {
    private double rentalFee;
    private User user;
    private Disc disc;
    private DiscHandler discHandler;
    private LocalDate dateRented;
    private String id;
    private Receipt receipt;
    
    public Rental() {
    }

    public Rental(double rentalFee, User user, Disc disc,
            DiscHandler discHandler, LocalDate dateRented, String id,
            Receipt receipt) {
        this.rentalFee = rentalFee;
        this.user = user;
        this.disc = disc;
        this.discHandler = discHandler;
        this.dateRented = dateRented;
        this.id = id;
        this.receipt = receipt;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Disc getDisc() {
        return disc;
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
    }

    public DiscHandler getDiscHandler() {
        return discHandler;
    }

    public void setDiscHandler(DiscHandler discHandler) {
        this.discHandler = discHandler;
    }

    public LocalDate getDateRented() {
        return dateRented;
    }

    public void setDateRented(LocalDate dateRented) {
        this.dateRented = dateRented;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Receipt getReceipt() {
        return this.receipt;
    }
    
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

   
    @Override
    public boolean persist() {
        final String QUERY = "INSERT INTO rental VALUES('"  + getId() + "'," +
                getRentalFee() + ", '" + getDateRented() + "', " + getDisc().getDiscId()
                + ", '" + getReceipt().getReceiptNumber() + "');";
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
    public boolean equals(Object other) {
           if(this == other) {
            return true;
        }

        if(other == null) {
            return false;
        }

        if(!(other instanceof Rental)) {
            return false;
        }

        if(!this.getId().equals(((Rental) other).getId())) {
            return false;
        }

        return true;
    }
}
