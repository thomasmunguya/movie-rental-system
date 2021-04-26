package main;

import disc.*;
import javafx.scene.image.Image;
import java.sql.*;
import java.util.*;
import java.time.*;

/**
 * Models a movie
 */
import database.Retrievable;
public class Movie implements Retrievable {

    private String title;
    private List<String> actors;
    private String director;
    private double rating;
    private int lengthInMinutes;
    private List<Disc> discs;
    private MovieGenre genre;
    private boolean available;
    private LocalDate releaseDate;
    private Image image;
    private String pathToImage;

    /**
     * Constructs a movie
     */
    public Movie() {

    }

    /**
     * Constructs a movie with a given title, actors, director, rating, length, discs, genre, availability,
     * release date, image and path to image
     * @param title
     * @param actors
     * @param director
     * @param rating
     * @param lengthInMinutes
     * @param discs
     * @param genre
     * @param available
     * @param releaseDate
     * @param image
     * @param pathToImage
     */
    public Movie(String title, List<String> actors, String director, double rating,
                 int lengthInMinutes, List<Disc> discs, MovieGenre genre, boolean available,
                 LocalDate releaseDate, Image image, String pathToImage) {
        this.title = title;
        this.actors = actors;
        this.director = director;
        this.rating = rating;
        this.lengthInMinutes = lengthInMinutes;
        this.discs = discs;
        this.genre = genre;
        this.available = available;
        this.releaseDate = releaseDate;
        this.image = image;
        this.pathToImage = pathToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public List<Disc> getDiscs() {
        return discs;
    }

    public void setDiscs(List<Disc> discs) {
        this.discs = discs;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    @Override
    public List<Retrievable> retrieveAll() {
        final String ALL_MOVIES_QUERY = "SELECT * FROM movies";

        final List<Movie> retrievedMovies = new ArrayList<>();
        try(CONNECTION) {
            Statement statement = CONNECTION.createStatement();
            ResultSet retrievedMoviesRS = statement.executeUpdate();
            while(retrievedMoviesRS.next()) {
                Movie movie = new Movie();
                movie.setTitle(retrievedMoviesRS.getString("title"));
                movie.set
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }

    }


    /**
     * Retrieves the discs that have been assigned to a movie
     * @param title the title of movie whose discs to retrieve
     * @return a list of discs
     */
    private List<Disc> retrieveDiscsAssignedToMovie(String title) {
        final String QUERY = "SELECT * FROM disc LEFT JOIN discs_assigned_to_movie ON " +
                "disc.id = discs_assigned_to_movie.disc_id;";
        final List<Disc> discsAssignedToMovie = new ArrayList<>();
        try(CONNECTION) {
            Statement statement = CONNECTION.createStatement();
            ResultSet discsAssignedToMovieRS = statement.executeQuery(QUERY);
            while(discsAssignedToMovieRS.next()) {
                Disc disc = new Disc();
                disc.
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves the ID associated with a movie from the database
     * @param title the title of the movie whose ID to retrieve
     * @return the id associated with title
     */
    private int retrieveMovieID(String title) {
        final String QUERY = "SELECT id FROM movies WHERE title = '" + title + "';";
        int id;
        try(CONNECTION) {
            Statement statement = CONNECTION.createStatement();
            ResultSet retrievedMovieRS = statement.executeQuery(QUERY);
            while(retrievedMovieRS.next()) {
                return retrievedMovieRS.getInt("id");
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     * Retrieve the disc tag associated with a disc
     * @param id the id of the disc whose tag to retrieve
     * @return
     */
    private DiscTag retrieveDiscTag(int id) {
        final String QUERY = "SELECT * FROM disc_tag LEFT JOIN disc_tag_assigned_to_disc" +
                "ON disc_tag.id = disc_tag_assigned_to_disc WHERE disc_tag_assigned_to_disc.disc_id = + " + id + ";";
        try(CONNECTION) {
            Statement statement = CONNECTION.createStatement();
            ResultSet discTagsRS = statement.executeQuery(QUERY);
            while(discTagsRS.next()) {
                DiscTag discTag = new DiscTag();
                discTag.setDateRented(LocalDate.parse(discTagsRS.getString("date_rented")));
                discTag.setTimeRented(Instant.parse());
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
