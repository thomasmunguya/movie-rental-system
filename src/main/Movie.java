package main;

import disc.*;
import javafx.scene.image.*;
import java.sql.*;
import java.util.*;
import java.time.*;

/**
 * Models a movie
 */
import database.Retrievable;
public class Movie implements Retrievable {

    private String title;
    private String actors;
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
    public Movie(String title, String actors, String director, double rating,
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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
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

    public static List<Movie> retrieveAll() {
        final String QUERY = "SELECT * FROM movie";

        final List<Movie> retrievedMovies = new ArrayList<>();
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet retrievedMoviesRS = statement.executeQuery(QUERY);
            while(retrievedMoviesRS.next()) {
                Movie movie = new Movie();
                movie.setTitle(retrievedMoviesRS.getString("title"));
                movie.setDiscs(retrieveDiscsAssignedToMovie(retrievedMoviesRS.getString("title")));
                movie.setRating(retrievedMoviesRS.getDouble("rating"));
                movie.setActors(retrievedMoviesRS.getString("actors"));
                movie.setDirector(retrievedMoviesRS.getString("director"));
                movie.setLengthInMinutes(retrievedMoviesRS.getInt("length_in_minutes"));
                movie.setGenre(getGenre(retrievedMoviesRS.getString("genre")));
                movie.setReleaseDate(LocalDate.parse(retrievedMoviesRS.getString("release_date")));
                movie.setAvailable(Boolean.getBoolean(retrievedMoviesRS.getString("available")));
                movie.setPathToImage(retrievedMoviesRS.getString("path_to_image"));
                movie.setImage(new Image(retrievedMoviesRS.getString("path_to_image")));
                System.out.println(movie.getImage().getHeight());
                retrievedMovies.add(movie);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return retrievedMovies;
    }


    /**
     * Retrieves the discs that have been assigned to a movie
     * @param title the title of movie whose discs to retrieve
     * @return a list of discs
     */
    private static List<Disc> retrieveDiscsAssignedToMovie(String title) {
        final String QUERY = "SELECT * FROM disc LEFT JOIN movie ON " +
                "disc.movie_id = movie.id WHERE movie.title = ' " + title + "';";
        final List<Disc> discsAssignedToMovie = new ArrayList<>();
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet discsAssignedToMovieRS = statement.executeQuery(QUERY);
            while(discsAssignedToMovieRS.next()) {
                Disc disc = new Disc();
                disc.setDiscId(discsAssignedToMovieRS.getInt("id"));
                disc.setDiscTag(retrieveDiscTag(discsAssignedToMovieRS.getInt("disc_tag_id")));
                discsAssignedToMovie.add(disc);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return discsAssignedToMovie;
    }

    /**
     * Retrieves the ID associated with a movie from the database
     * @param title the title of the movie whose ID to retrieve
     * @return the id associated with title
     */
    private int retrieveMovieID(String title) {
        final String QUERY = "SELECT id FROM movie WHERE title = '" + title + "';";
        int id;
        try {
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
     * Retrieve the disc tag associated with an id
     * @param id the id associated with the disc tag
     * @return
     */
    private static DiscTag retrieveDiscTag(int id) {
        final String QUERY = "SELECT * FROM disc_tag WHERE id = " + id;
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet discTagsRS = statement.executeQuery(QUERY);
            if(discTagsRS.next()) {
                DiscTag discTag = new DiscTag();
                discTag.setDateRented(LocalDate.parse(discTagsRS.getString("date_rented")));
                discTag.setTimeRented(Instant.parse(discTagsRS.getString("time_rented")));
                discTag.setDateReturned(LocalDate.parse(discTagsRS.getString("date_returned")));
                discTag.setTimeReturned(Instant.parse(discTagsRS.getString("time_returned")));
                return discTag;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Retrievable retrieve(String query) {
        return null;
    }

    /**
     * Maps a genre name in text form to the MovieGenre enum
     * @param genre the name of the genre to map
     * @return the movie genre
     */
    private static MovieGenre getGenre(String genre) {
        switch(genre) {
            case "Action": return MovieGenre.ACTION;
            case "Adventure": return MovieGenre.ADVENTURE;
            case "Comedy": return MovieGenre.COMEDY;
            case "Crime and Mystery": return MovieGenre.CRIME_AND_MYSTERY;
            case "Fantasy": return MovieGenre.FANTASY;
            case "Historical": return MovieGenre.HISTORICAL;
            case "Horror": return MovieGenre.HORROR;
            case "Romance": return MovieGenre.ROMANCE;
            case "Satire": return MovieGenre.SATIRE;
            case "Science Fiction": return MovieGenre.SCIENCE_FICTION;
            case "Speculative": return MovieGenre.SPECULATIVE;
            case "Thriller": return MovieGenre.THRILLER;
            case "Western": return MovieGenre.WESTERN;
            case "Other": return MovieGenre.OTHER;
        }
        return null;
    }

    public static void main(String[] args) {
        for (Movie movie : Movie.retrieveAll()) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Actors: " + movie.getActors());
            System.out.println("Director: " + movie.getDirector());
            System.out.println("Rating: " + movie.getRating());
            System.out.println("Length (Minutes): " + movie.getLengthInMinutes());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Available: " + movie.isAvailable());
            System.out.println("Release date: " + movie.getReleaseDate());
            System.out.println("pathToImage: " + movie.getPathToImage());
        }

    }
}



































































































































































































































































































































































































































































































































































































































































































































