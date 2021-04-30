/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import main.Movie;

/**
 *
 * Controller class for the movie cart view
 */
public class MovieCartController implements Initializable {
    @FXML
    AnchorPane middlePane;
    
    @FXML
    private Button btnRent;
    
    @FXML
    private Button btnRedeem;
    
    @FXML
    private Button btnGoBack;
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnCheckout;

    @FXML
    private Button btnAddMovie;

    @FXML
    private Label anchorPaneCartDetails;

    @FXML
    private Label lblCartItemsNumber;
    
    @FXML
    private TableView<Movie> tvMovieCart;

    @FXML
    private TableColumn<Movie, String> tcMovieCover;

    @FXML
    private TableColumn<Movie, String> tcMovieTitle;

    @FXML
    private TableColumn<Movie, String> tcTotal;
    
    public static final ObservableList<Movie> MOVIE_CART = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
          showMoviesInCart();
          
           MOVIE_CART.addListener(((ListChangeListener) c-> {
              if(!MOVIE_CART.isEmpty())
                lblCartItemsNumber.setText(MOVIE_CART.size() + " Item(s) in cart.");
              else
                  lblCartItemsNumber.setText("0 Item(s) in cart.");
          }));
    }
    
    @FXML
    /**
     * Navigates to the movie list UI
     */
    public void navigateToMovieList() throws IOException {
        AnchorPane movieListRootPane = FXMLLoader.<AnchorPane>load(getClass().getResource("/ui/MovieList.fxml"));
        Scene scene = new Scene(movieListRootPane, 720, 600);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Movie List");
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * Loads the movies in the cart
     */
    private void showMoviesInCart() {
        
        System.out.println(MOVIE_CART.size());
        
        tvMovieCart.setItems(MOVIE_CART);
        
        
        tcMovieCover.setPrefWidth(220);
        tcMovieCover.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        tcMovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        tcTotal.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));
        
////        int layoutX = 
//        for(Movie movie: MOVIE_CART) {
//            
//            ImageView imgView = new ImageView(movie.getImage());
//            imgView.setFitWidth(51);
//            imgView.setFitHeight(68);
//            
//            Label lblMovieTitle = new Label(movie.getTitle());
//            lblMovieTitle.setFont(new Font("Berlin Sans FB", 14));
//            lblMovieTitle.setTextFill(Paint.valueOf("WHITE"));
//            lblMovieTitle.setLayoutX(59);
//            lblMovieTitle.setLayoutY(24);
//            
//            AnchorPane anchorPane1 = new AnchorPane();
//            anchorPane1.setLayoutX(0);
//            anchorPane1.setLayoutY(0);
//            anchorPane1.getChildren().addAll(imgView, lblMovieTitle);
//            
//            
//            Button btnRemoveFromCart = new Button("Remove From Cart");
//            btnRemoveFromCart.setFont(new Font("System Bold", 11));
//            btnRemoveFromCart.setTextFill(Paint.valueOf("WHITE"));
//            btnRemoveFromCart.setLayoutX(404);
//            btnRemoveFromCart.setLayoutY(17);
//            btnRemoveFromCart.setPrefHeight(35);
//            btnRemoveFromCart.setPrefWidth(120);
//            btnRemoveFromCart.setStyle("-fx-background-color:  #ff3700;");
//            
//            AnchorPane anchorPane2 = new AnchorPane();
//            anchorPane2.setLayoutX(0);
//            anchorPane2.setLayoutY(0);
//            anchorPane2.getChildren().addAll(anchorPane1, btnRemoveFromCart);
//            
//            AnchorPane anchorPane3 = new AnchorPane();
//            anchorPane3.setLayoutX(14);
//            anchorPane3.setLayoutY(105);
//            anchorPane3.getChildren().add(anchorPane2);
//            anchorPane3.setStyle("-fx-background-color:    #0080ff;");
//            
//            middlePane.getChildren().add(anchorPane3);
            
//        }
    }
    
    @FXML
    /**
     * Navigates to the payment UI
     * 
     */
    public void navigateToPayment() throws IOException {
        AnchorPane mainMenuRootPane = FXMLLoader.<AnchorPane>load(getClass().getResource("/ui/CardPayment.fxml"));
        Scene scene = new Scene(mainMenuRootPane, 720, 600);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Card Payment");
        stage.setResizable(false);
        stage.show();
    }
    
}
