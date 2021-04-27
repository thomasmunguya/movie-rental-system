package disc;

import java.time.*;

/**
 * Models a disc tag
 * A disc tag is represented by the date, and the time the disc was rented and by the date and the time it was returned
 */
public class DiscTag {

    private LocalDate dateRented;
    private Instant timeRented;
    private LocalDate dateReturned;
    private Instant timeReturned;

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

    public DiscTag() {

    }

    public DiscTag(LocalDate dateRented, Instant timeRented) {
        this.dateRented = dateRented;
        this.timeRented = timeRented;
    }

    public DiscTag(LocalDate dateRented, Instant timeRented, LocalDate dateReturned, Instant timeReturned) {
        this.dateRented = dateRented;
        this.timeRented = timeRented;
        this.dateReturned = dateReturned;
        this.timeReturned = timeReturned;
    }

}
