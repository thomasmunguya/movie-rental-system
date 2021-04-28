package database;

import java.util.List;

/**
 * Models an entity that can be retrieved from a database
 */
public interface Retrievable extends DatabaseAccessor {

    /**
     * Retrieves objects from the database
     * @param query the query to pass to the database
     * @return the object retrieved
     */
    public abstract Retrievable retrieve();
}
