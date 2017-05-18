/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client ;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Suzana
 */
public class FirstPageController implements Initializable {

   @FXML
    private TextField usernameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
    public void signup(ActionEvent event) throws IOException
    {
        Stage stage = null; 
        Parent root = null;
            
        stage=(Stage) signUp.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("registerPage.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    public void signin(ActionEvent event) throws IOException
    {
        // verificare date (username + parola)
        Stage stage = null; 
        Parent root = null;
        
        stage=(Stage) signIn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("gamePage.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
