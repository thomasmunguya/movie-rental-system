/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import disc.Disc;
import disc.DiscTag;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import main.Movie;
import main.Rental;
import main.User;
import payment.CreditCard;
import payment.DebitCard;
import payment.PaymentCard;
import payment.Receipt;

/**
 *
 * @author MTH
 */
public class PaymentController implements Initializable {
    
     @FXML
    private Button btnGoBack;

    @FXML
    private AnchorPane numberPadPane;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn6;

    @FXML
    private Button btn5;

    @FXML
    private Button btn7;

    @FXML
    private Button btn0;

    @FXML
    private Button btn9;

    @FXML
    private Button btn8;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnBackSpace;

    @FXML
    private Label lblCardNumber;

    @FXML
    private Button btnPay;

    private static final Alert ALERT = new Alert(Alert.AlertType.ERROR);
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpNumberPadEventHandlers();
    }
    
    /**
     * Setups event handlers for number pad buttons
     */
    public void setUpNumberPadEventHandlers() {
        btn0.setOnAction((event) -> {
            registerKeyPress("0");
        });
        
        btn1.setOnAction((event) -> {
            registerKeyPress("1");
        });
        
        btn2.setOnAction((event) -> {
            registerKeyPress("2");
        });
        
        btn3.setOnAction((event) -> {
            registerKeyPress("3");
        });
        
        btn4.setOnAction((event) -> {
            registerKeyPress("4");
        });
        
        btn5.setOnAction((event) -> {
            registerKeyPress("5");
        });
        
        btn6.setOnAction((event) -> {
            registerKeyPress("6");
        });
        
        btn7.setOnAction((event) -> {
            registerKeyPress("7");
        });
        
        btn8.setOnAction((event) -> {
            registerKeyPress("8");
        });
        
        btn9.setOnAction((event) -> {
            registerKeyPress("9");
        });
        
        
        btnBackSpace.setOnAction((event) -> {
            registerKeyPress("DEL");
        });
        
        btnClear.setOnAction((event) -> {
            registerKeyPress("CLR");
        });
        
    }
    
    /**
     * Registers a key press on the number pad
     */
    private void registerKeyPress(String key) {
       switch(key) {
           case "0": lblCardNumber.setText(lblCardNumber.getText() + "0"); break;
           case "1": lblCardNumber.setText(lblCardNumber.getText() + "1"); break;
           case "2": lblCardNumber.setText(lblCardNumber.getText() + "2"); break;
           case "3": lblCardNumber.setText(lblCardNumber.getText() + "3"); break;
           case "4": lblCardNumber.setText(lblCardNumber.getText() + "4"); break;
           case "5": lblCardNumber.setText(lblCardNumber.getText() + "5"); break;
           case "6": lblCardNumber.setText(lblCardNumber.getText() + "6"); break;
           case "7": lblCardNumber.setText(lblCardNumber.getText() + "7"); break;
           case "8": lblCardNumber.setText(lblCardNumber.getText() + "8"); break;
           case "9": lblCardNumber.setText(lblCardNumber.getText() + "9"); break;
           case "DEL": {
            if(lblCardNumber.getText().equals("")) {
                lblCardNumber.setText("0");
            }
            String newText = lblCardNumber.getText().substring(0, lblCardNumber.getText().length() - 1);
            lblCardNumber.setText(newText);
           } break;
           case "CLR": lblCardNumber.setText("0"); break;
                     
       }
    }
    
    @FXML
    /**
     *Processes a payment
     * @return true if the payment was success, otherwise false
     */
    public boolean processPayment() {
        String cardNumber = lblCardNumber.getText();
        CreditCard cCard = new CreditCard();
        cCard.setCardNumber(cardNumber);
        CreditCard retrievedCCard = (CreditCard) cCard.retrieveOne("card_number", cardNumber);
        
        if(!PaymentCard.isValidCard(cCard) || retrievedCCard == null) {
          ALERT.setHeaderText("Invalid Payment Card.");
          ALERT.setContentText("Your payment card is either not valid or is not registered with Movie Rental"
                  + " Ensure that your card number is correct and that it is registered with"
                  + "with Movie Rental");
          ALERT.show();
          return false;
        }
       
        cCard = retrievedCCard;
        retrievedCCard = null;
        
        
        if(setUpRentalInfo()) {
            ALERT.setAlertType(Alert.AlertType.INFORMATION);
            ALERT.setHeaderText("Payment Successful");
            ALERT.setContentText("Your payment was successful. Please collect your disc(s) from the disc dispenser.");
            ALERT.show();
            return true;
        }
            
        return false;
    }
    
    
    private boolean setUpRentalInfo() {
        
        DiscTag discTag = new DiscTag();
        discTag.setDateRented(LocalDate.now());
        
        Receipt receipt = new Receipt();
        receipt.setIssueDate(LocalDate.now());
        receipt.setIssueTime(Instant.now());
        
        Rental rental = new Rental();
        
        
        Map<String, Double> cartItems = new HashMap<>();
        
        MovieCartController.MOVIE_CART.forEach((movie) -> {
            
            cartItems.put(movie.getTitle(), movie.getRentalPrice());
            Disc disc = new Disc(movie.getDiscs().get(0).getDiscId(), discTag);
            movie.getDiscs().remove(0);
            
            rental.setDateRented(LocalDate.now());
            rental.setDisc(disc);
            rental.setRentalFee(movie.getRentalPrice());
            
            User user = new User();
            
            rental.setUser((User) user.retrieveOne("payment_card_number", lblCardNumber.getText()));
            
        });
        
        receipt.setItems(cartItems);
        rental.setReceipt(receipt);
        
        return receipt.persist() && rental.persist();
    }
 
}
