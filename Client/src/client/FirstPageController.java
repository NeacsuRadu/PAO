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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Suzana
 */
public class FirstPageController implements Initializable {
    
    private MainController mainController;
    
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label warning;   
    @FXML private Label emptyField; 
    @FXML private Label notConnectedLabel;
    @FXML private Button signIn;
    @FXML private Button signUp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
    void setClient(MainController main) 
    {
        this.mainController = main;
    }
    
    public void signup(ActionEvent event)
    {
        this.mainController.showRegisterPage();
    }
    
 
    public void signin(ActionEvent event)
    {
        if(!username.getText().equals("") && !password.getText().equals(""))
        {
            mainController.sendMessage(Messages.getLoginMessage(username.getText(), password.getText()));
        }
        else
        {
           emptyField.setVisible(true);
           warning.setVisible(false);
        }
    }
    
    public void showInvalideUsernamePasswordCombination()
    {
        warning.setVisible(true);
        emptyField.setVisible(false);
    }
    
    public void cleanUp()
    {
        username.setText("");
        password.setText("");
        
        warning.setVisible(false);
        emptyField.setVisible(false);
        if (mainController.isClientConnected())
        {
            notConnectedLabel.setVisible(false);
        }
        else
        {
            notConnectedLabel.setVisible(true);
        }
    }
    
    public void setNotConnectedText()
    {
        notConnectedLabel.setVisible(true);
    }
}
