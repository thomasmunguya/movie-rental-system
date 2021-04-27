/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 *
 * @author MTH
 */
public class MainMenuController implements Initializable {
    
    @FXML
    private Button btnRent;
    
    @FXML
    private Button btnReturn;
    
    @FXML
    private Button btnSignUp;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
          
    }
    
    @FXML
    public void navigateToMovieList() throws IOException {
            AnchorPane rootPane = FXMLLoader.<AnchorPane>load(getClass().getResource("/ui/MovieList.fxml"));
            Scene scene = new Scene(rootPane, 720, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Movie List");
            stage.setResizable(false);
            stage.show();
        
    }
}
