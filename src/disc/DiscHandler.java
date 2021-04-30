package disc;

import java.time.*;
import java.util.*;

/**
 * Handle business related to discs including dispensing and accepting returned discs
 */
public class DiscHandler  {

    /**
     * Dispenses a disc
     * <p A disc is dispensed by setting the disc tag's date and time rented properties to the current date and time respectively
     * and erasing the old disc tag's date and time returned properties </p>
     * @param disc the disc to dispense
     * @return true if the disc was successfully dispense, otherwise false
     */
    public static boolean dispense(Disc disc) {
        LocalDate oldDateRented = disc.getDiscTag().getDateRented();
        Instant oldTimeRented = disc.getDiscTag().getTimeRented();
        DiscTag discTag = new DiscTag(LocalDate.now(), Instant.now());
        disc.setDiscTag(discTag);
        if(disc.persist()) {
            return true;
        }
        disc.getDiscTag().setDateRented(oldDateRented);
        disc.getDiscTag().setTimeRented(oldTimeRented);
        return false;
    }

    /**
     * Accepts a returned disc
     * @param disc the disc being returned
     * @return true if the disc is accepted, otherwise false
     */
    public static boolean acceptReturnedDisc(Disc disc) {
        return true;
    }
    
//    public 
}
