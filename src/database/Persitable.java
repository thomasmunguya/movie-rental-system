package database;

/**
 * Models and entity that can be persisted to a database
 */
public interface Persitable extends DatabaseAccessor {

    /**
     * Persists and object to the database
     * @return true if successfully persisted or false if an attempt to persist failed for some reason
     */
    public abstract boolean persist();
}
