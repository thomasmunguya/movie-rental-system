package controllers;

import java.io.IOException;
import static java.lang.Integer.min;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import main.Movie;
import javafx.scene.text.*;
import java.util.*;
import static java.util.stream.Collectors.toMap;
import java.util.stream.IntStream;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * Controller class for the movie list view
 */
public class MovieListController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private AnchorPane navigationButtonsPane;
    
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
    
    @FXML
    private ImageView imgViewNextPage;

    @FXML
    private ImageView imgViewPreviousPage;
    
    @FXML
    private Pagination paginationMovieList;
    
    @FXML
    private AnchorPane movieListPane;
    
    private static List<Movie> movieList;
    private static final int MOVIES_PER_PAGE = 12;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
          movieList = Movie.retrieveAll();
          loadMovies();
    }
    
    /**
     * Loads the movies starting at a particular index
     * @param startIndex the index at which to start loading the movies
     * @param endIndex the index (exclusive) at which to end loading the movies
     */
    private void loadMovies() {
//       paginationMovieList.setLayoutX(100);
//       paginationMovieList.setLayoutY(40);
       paginationMovieList.setPageCount(movieList.size());
        
       paginationMovieList.setPageFactory(new Callback<Integer, Node>() {
        public Node call(Integer pageIndex) {
           return createMovieCoverPane(pageIndex);
       }
      });
       
            
    }
    
    public Map<Integer, List<Movie>> partition(List<Movie> list, int pageSize) {
    return IntStream.iterate(0, i -> i + pageSize)
          .limit((list.size() + pageSize - 1) / pageSize)
          .boxed()
          .collect(toMap(i -> i / pageSize,
                         i -> list.subList(i, min(i + pageSize, list.size()))));
}
    
    @FXML
    /**
     *Navigates of the previous page of the movie list
     */
    public void navigateToPreviousPage() {
//        loadMovies(currentIndex - MOVIES_PER_PAGE, currentIndex);
    }
    
    /**
     * Navigates to movie details UI
     * @param movie the movie whose details to display
     * @throws IOException 
     */
    private void navigateToMovieDetails(Movie movie) throws IOException{
        MovieDetailsController.setMovie(movie);
        AnchorPane movieDetailsRootPane = FXMLLoader.<AnchorPane>load(getClass().getResource("/ui/MovieDetails.fxml"));
        Scene scene = new Scene(movieDetailsRootPane, 720, 600);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Movie Details");
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML
    /**
     * Navigates to the main menu
     * @throws IOException if the view file cannot be loaded
     */
    public void navigateToMainMenu() throws IOException {
        AnchorPane mainMenuRootPane = FXMLLoader.<AnchorPane>load(getClass().getResource("/ui/MainMenu.fxml"));
        Scene scene = new Scene(mainMenuRootPane, 720, 600);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.setResizable(false);
        stage.show();
    }
    
    private HBox createMovieCoverPane(int index) {
        
           HBox movieCoversPane = new HBox(20);
           movieCoversPane.setPrefWidth(720);
           movieCoversPane.setPrefHeight(500);
           
           for (int i = 0; i <= index; i++) {
               
               ImageView imgViewMovieCover = movieList.get(index).getImage();
               imgViewMovieCover.setFitWidth(200);
               imgViewMovieCover.setFitHeight(300);
               imgViewMovieCover.setSmooth(true);
               imgViewMovieCover.setLayoutX(100);
               imgViewMovieCover.setOnMouseReleased((event) -> {
                   try {
                       navigateToMovieDetails(movieList.get(index));
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
               });
               
      
              Text txtMovieTitle = new Text(movieList.get(index).getTitle());
              txtMovieTitle.setFont(Font.font("Berlin Sans FB", 14));
              txtMovieTitle.setTextAlignment(TextAlignment.RIGHT);
              txtMovieTitle.setFill(Paint.valueOf("WHITE"));
              txtMovieTitle.setWrappingWidth(100);

              VBox vBoxCoverAndTitle = new VBox();
              vBoxCoverAndTitle.getChildren().addAll(imgViewMovieCover, txtMovieTitle);
              
              movieCoversPane.getChildren().add(vBoxCoverAndTitle);
           }
           
           return movieCoversPane;
    }
    
    
}
