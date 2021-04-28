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
import java.util.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import main.Movie;

/**
 *
 * @author MTH
 */
public class MovieCartController implements Initializable {
    
    @FXML
    private Button btnRent;
    
    @FXML
    private Button btnRedeem;
    
    @FXML
    private Button btnGoBack;
    
    public static final List<Movie> MOVIE_CART = new ArrayList<>();
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
          
    }
    
    @FXML
    /**
     * Navigates to the movie cart ui
     */
    public void navigateToMovieCart() throws IOException {
        AnchorPane rootPane = FXMLLoader.<AnchorPane>load(getClass().getResource("/ui/MovieCart.fxml"));
        Scene scene = new Scene(rootPane, 720, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Movie Cart");
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * Loads the movies in the cart
     */
    private void loadMovies() {
        for(Movie movie: MOVIE_CART) {
            ImageView imgView = new ImageView(movie.getImage());
            imgView.setFitWidth(51);
            imgView.setFitHeight(68);
            
            Label lblMovieTitle = new Label(movie.getTitle());
            lblMovieTitle.setFont(new Font("Berlin Sans FB", 14));
            
            AnchorPane anchorPane1 = new AnchorPane();
            anchorPane1.getChildren().addAll(imgView, lblMovieTitle);
            
            Button btnRemoveFromCart = new Button("Remove From Cart");
//            btnRemoveFromCart.setFont(new Font());
            
        }
    }
    
}
