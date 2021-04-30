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
    
    private static int currentIndex = 0;
    private static int pageNumber = 0;
    private static List<Movie> movieList;
    private static final int MOVIES_PER_PAGE = 12;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
          movieList = Movie.retrieveAll();
////          loadMovies();
////          navigateToNextPage();
////          setUpPageNavigationButtons();
          partition(movieList, 2).forEach((t, movies) -> {
                ImageView imgViewMovieCover = movies.get(0).getImage();
               imgViewMovieCover.setFitWidth(200);
               imgViewMovieCover.setFitHeight(300);
               imgViewMovieCover.setSmooth(true);
               
               imgViewMovieCover.setOnMouseReleased((event) -> {
                   try {
                       navigateToMovieDetails(movieList.get(0));
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
               });
               Text txtMovieTitle = new Text(movieList.get(0).getTitle());
              txtMovieTitle.setFont(Font.font("Berlin Sans FB", 12));
              txtMovieTitle.setTextAlignment(TextAlignment.CENTER);
              txtMovieTitle.setFill(Paint.valueOf("WHITE"));
              txtMovieTitle.setWrappingWidth(100);
              
              HBox movieCoversPane = new HBox(20);
            movieCoversPane.setPrefWidth(720);
            movieCoversPane.setPrefHeight(391);

              VBox vBoxCoverAndTitle = new VBox();
              vBoxCoverAndTitle.getChildren().addAll(imgViewMovieCover, txtMovieTitle);
              movieCoversPane.getChildren().add(vBoxCoverAndTitle);
              rootPane.getChildren().add(movieCoversPane);
          });
    }
    
    /**
     * Loads the movies starting at a particular index
     * @param startIndex the index at which to start loading the movies
     * @param endIndex the index (exclusive) at which to end loading the movies
     */
    private void loadMovies() {
        
//        List<String> movieCovers = new ArrayList<>();
       paginationMovieList.setOnDragEntered((event) -> {
//           if(paginationMovieList.getOnDragEntered().equals(MouseDragEvent.))
           paginationMovieList.setCurrentPageIndex(paginationMovieList.getCurrentPageIndex() + 1);
       });
       paginationMovieList.setPageFactory(new Callback<Integer, Node>() {
        public Node call(Integer pageIndex) {
           HBox movieCoversPane = new HBox(20);
           movieCoversPane.setPrefWidth(720);
           movieCoversPane.setPrefHeight(391);
           
           for (int i = 0; i < pageIndex + 10; i++) {
               ImageView imgViewMovieCover = movieList.get(i).getImage();
               imgViewMovieCover.setFitWidth(200);
               imgViewMovieCover.setFitHeight(300);
               imgViewMovieCover.setSmooth(true);
               imgViewMovieCover.setOnMouseReleased((event) -> {
                   try {
                       navigateToMovieDetails(movieList.get(0));
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
               });
               
              Text txtMovieTitle = new Text(movieList.get(i).getTitle());
              txtMovieTitle.setFont(Font.font("Berlin Sans FB", 12));
              txtMovieTitle.setTextAlignment(TextAlignment.CENTER);
              txtMovieTitle.setFill(Paint.valueOf("WHITE"));
              txtMovieTitle.setWrappingWidth(100);

              VBox vBoxCoverAndTitle = new VBox();
              vBoxCoverAndTitle.getChildren().addAll(imgViewMovieCover, txtMovieTitle);
              movieCoversPane.getChildren().add(vBoxCoverAndTitle);
           }
           
//           navigationButtonsPane.getChildren().add(movieCoversPane);
           return movieCoversPane;
       }
      });
//        
//        if(!hBoxMovieRow1.getChildren().isEmpty()) {
//            hBoxMovieRow1.getChildren().clear();
//        }
//        
//        if(!hBoxMovieRow2.getChildren().isEmpty()) {
//            hBoxMovieRow2.getChildren().clear();
//        }
//        
//        for(int i = startIndex; i < endIndex; i++) {
//            
//            Movie movie = movieList.get(i);
//            ImageView imageView = new ImageView();
//            imageView.setFitWidth(95.0);
//            imageView.setFitHeight(132.0);
//            imageView.setImage(movie.getImage());
//
//            Text txtMovieTitle = new Text(movie.getTitle());
//            txtMovieTitle.setFont(Font.font("Berlin Sans FB", 12));
//            txtMovieTitle.setTextAlignment(TextAlignment.CENTER);
//            txtMovieTitle.setFill(Paint.valueOf("WHITE"));
//            txtMovieTitle.setWrappingWidth(100);
//
//            VBox vBoxCoverAndTitle = new VBox();
//            vBoxCoverAndTitle.setPrefWidth(95);
//            vBoxCoverAndTitle.setPrefHeight(147);
//            vBoxCoverAndTitle.getChildren().addAll(imageView, txtMovieTitle);
//            
//            if(moviesAddedOnRow < 6) {
//                hBoxMovieRow1.getChildren().add(vBoxCoverAndTitle);
//                moviesAddedOnRow++;
//            }
//            else if(moviesAddedOnRow > 5) {
//                hBoxMovieRow2.getChildren().add(vBoxCoverAndTitle);
//                moviesAddedOnRow++;
//            }
//            
//            if(movieList.size() == endIndex) {
//                currentIndex = currentIndex;
//            }
//            
//            currentIndex = (pageNumber * 12) % 12;
//
//        }
//    }
//    
//    private void setUpPageNavigationButtons() {
//        ImageView nextIcon = new ImageView("img/next.png");
//        nextIcon.setFitHeight(34);
//        nextIcon.setFitWidth(34);
//        
//        Circle nextIconClip = new Circle();
//        nextIconClip.setRadius(34);
//        nextIconClip.setLayoutX(655);
//        nextIconClip.setLayoutY(405);
//        
//        nextIcon.setClip(nextIconClip);
//        
//        navigationButtonsPane.getChildren().add(nextIcon);
//    }
//    
//    
//    @FXML
//    /**
//     * Navigates to next page of the movie list
//     */
//    public void navigateToNextPage() {
//        if(currentIndex + MOVIES_PER_PAGE < movieList.size()) {
//            loadMovies(currentIndex, currentIndex + MOVIES_PER_PAGE);
//            pageNumber++;
//            if(movieList.size() % MOVIES_PER_PAGE != 0 && (movieList.size() / MOVIES_PER_PAGE) < 1) {
//                lblPageNumber.setText("PAGE " + pageNumber  + " OF 1");
//            }
//            else {
//                lblPageNumber.setText("PAGE " + pageNumber  + " OF "  + (int)((movieList.size() / MOVIES_PER_PAGE) + 1));
//            }
//            
//        }
//        else {
//            loadMovies(currentIndex, movieList.size());
//            pageNumber++;
//            if(movieList.size() % MOVIES_PER_PAGE != 0 && (movieList.size() / MOVIES_PER_PAGE) < 1) {
//                lblPageNumber.setText("PAGE " + pageNumber  + " OF 1");
//            }
//            else {
//                lblPageNumber.setText("PAGE " + pageNumber  + " OF "  + (int)((movieList.size() / MOVIES_PER_PAGE) + 1));
//            }
//        }
            
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
    
    
    
}
