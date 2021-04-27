package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import main.Movie;
import javafx.scene.text.*;
import java.util.*;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MovieListController implements Initializable {
    @FXML
    private VBox vBoxMovieImages;

    @FXML
    private HBox hBoxMovieRow1;

    @FXML
    private VBox vBoxCoverAndTitle;

    @FXML
    private ImageView imgViewMovieCover;

    @FXML
    private HBox hBoxMovieRow2;

    @FXML
    private VBox vBoxCoverAndTitle1;
    
    @FXML
    private Label lblPageNumber;
    
    private static int currentIndex = 0;
    private static int pageNumber = 0;
    private static List<Movie> movieList;
    private static final int MOVIES_PER_PAGE = 12;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
          movieList = Movie.retrieveAll();
        navigateToNextPage();
//          loadMovies(0, 6);
    }
    
    /**
     * Loads the movies starting at a particular index
     * @param startIndex the index at which to start loading the movies
     * @param endIndex the index (exclusive) at which to end loading the movies
     */
    private void loadMovies(int startIndex, int endIndex) {
        int moviesAddedOnRow = 0;
        
        for(int i = startIndex; i < endIndex; i++) {
            
            Movie movie = movieList.get(i);
            ImageView imageView = new ImageView();
            imageView.setFitWidth(95.0);
            imageView.setFitHeight(132.0);
            imageView.setImage(movie.getImage());

            Label lblMovieTitle = new Label(movie.getTitle());
            lblMovieTitle.setFont(Font.font("arial", FontWeight.BOLD, 12));
            lblMovieTitle.setTextAlignment(TextAlignment.CENTER);

            VBox vBoxCoverAndTitle = new VBox();
            vBoxCoverAndTitle.setPrefWidth(95);
            vBoxCoverAndTitle.setPrefHeight(147);
            vBoxCoverAndTitle.getChildren().addAll(imageView, lblMovieTitle);
            
            if(moviesAddedOnRow < 6) {
                hBoxMovieRow1.getChildren().add(vBoxCoverAndTitle);
                moviesAddedOnRow++;
            }
            else if(moviesAddedOnRow > 5) {
                hBoxMovieRow2.getChildren().add(vBoxCoverAndTitle);
                moviesAddedOnRow++;
            }
            
            if(movieList.size() == endIndex) {
                currentIndex = currentIndex;
            }

        }
    }
    
    @FXML
    /**
     * Navigates to next page of the movie list
     */
    public void navigateToNextPage() {
        if(currentIndex + MOVIES_PER_PAGE < movieList.size()) {
            loadMovies(currentIndex, currentIndex + MOVIES_PER_PAGE);
            pageNumber++;
            if(movieList.size() % MOVIES_PER_PAGE != 0 && (movieList.size() / MOVIES_PER_PAGE) < 1) {
                lblPageNumber.setText("PAGE " + pageNumber  + " OF 1");
            }
            else {
                lblPageNumber.setText("PAGE " + pageNumber  + " OF "  + (int)((movieList.size() / MOVIES_PER_PAGE) + 1));
            }
            
        }
        else {
            loadMovies(currentIndex, movieList.size());
            pageNumber++;
            if(movieList.size() % MOVIES_PER_PAGE != 0 && (movieList.size() / MOVIES_PER_PAGE) < 1) {
                lblPageNumber.setText("PAGE " + pageNumber  + " OF 1");
            }
            else {
                lblPageNumber.setText("PAGE " + pageNumber  + " OF "  + (int)((movieList.size() / MOVIES_PER_PAGE) + 1));
            }
        }
            
    }
    
    @FXML
    /**
     *Navigates of the previous page of the movie list
     */
    public void navigateToPreviousPage() {
        loadMovies(currentIndex - MOVIES_PER_PAGE, currentIndex);
    }
    
    private void navigateToMovieDetails(Movie movie) throws IOException{
            
    }
    

}
