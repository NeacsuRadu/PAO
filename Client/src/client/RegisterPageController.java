/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
 *
 * @author Suzana
 */
public class RegisterPageController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button backBt;
    @FXML
    private Button signIn;
    @FXML
    private Label passLength;
    @FXML
    private Label passConfirm;
    @FXML
    private Label emptyField;
    @FXML
    private Label emailCf;
    @FXML
    private Label usernameTaken;
     @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField cfPassword;

    
    @FXML
    void back(ActionEvent event) throws IOException 
    {
        Stage stage = null; 
        Parent root = null;
        
        stage=(Stage) backBt.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void signin(ActionEvent event) throws IOException
    {
        String mail = email.getText();
        
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(mail);
        
        boolean matchFound = m.matches();
        

       if ( !firstName.getText().equals("") && !lastName.getText().equals("") &&  !mail.equals("") && !username.getText().equals("") && !password.getText().equals(""))
        {    
            if(matchFound)
            {
                if(password.getLength() >= 8)
                {       
                    if(password.getText().equals(cfPassword.getText()))
                    {
                        
                        Stage stage = null; 
                        Parent root = null;
        
                        stage=(Stage) signIn.getScene().getWindow();
                        root = FXMLLoader.load(getClass().getResource("gamePage.fxml"));
        
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        
                    //daca exista in baza de date
                    //-> usernameTaken.setVisibility(true);
                    }
                    else
                    {   
                        emptyField.setVisible(false);
                        passLength.setVisible(false);
                        passConfirm.setVisible(true);
                        emailCf.setVisible(false);
                    }
                }
                else
                {
                    emptyField.setVisible(false);
                    passLength.setVisible(true);
                    passConfirm.setVisible(false);
                    emailCf.setVisible(false);
                }
            }
            else
            {
                    emptyField.setVisible(false);
                    passLength.setVisible(false);
                    passConfirm.setVisible(false);
                    emailCf.setVisible(true);
            }  
        }
        else
        {
            emptyField.setVisible(true);
            passLength.setVisible(false);
            passConfirm.setVisible(false);
            emailCf.setVisible(false);
        }                      
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
