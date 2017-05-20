/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Suzana
 */
public class GamePageController implements Initializable 
{
    private Client main;
    
    @FXML private Button b11;
    @FXML private Button b12;
    @FXML private Button b13;
    @FXML private Button b21;
    @FXML private Button b22;
    @FXML private Button b23;
    @FXML private Button b31;
    @FXML private Button b32;
    @FXML private Button b33;   
    @FXML private Label playerTurn;
    @FXML private Button backBt;
    @FXML private Button newGame;
    @FXML private Label lb1;
    @FXML private Label lb3;
    @FXML private ProgressBar progressBar;

    private boolean isFirstPlayer = true;
    
    public void setClient(Client main)
    {
        this.main = main;
    }
    
    @FXML
    void newgame(ActionEvent event) 
    {
        main.showGamePage();
    }
    @FXML
    void onClick(ActionEvent event) 
    {
        
        playerTurn.setText("PLAYER 1");
        Button clickedButton = (Button) event.getTarget();
        
        System.out.print("Butoane apasate: ");
        
        String id = clickedButton.getId();
        String[] coord = id.split(",");
        
        System.out.print("Linia: " + Integer.parseInt(coord[0]));
        System.out.println(" Coloana : " + Integer.parseInt(coord[1]));
        
        String buttonLabel = clickedButton.getText();

        if ("".equals(buttonLabel) && isFirstPlayer)
        {
            
            clickedButton.setText("X");
            playerTurn.setText("PLAYER 2");
            itsAMatch();
            noWinner();
            isFirstPlayer = false;

        } 
        else
        {
            if("".equals(buttonLabel) && !isFirstPlayer)
            {
                 
                clickedButton.setText("0");
                itsAMatch();
                isFirstPlayer = true;
            }
        }
    }
    
    private boolean noWinner()
    {
        if( !"".equals(b11.getText()) && !"".equals(b12.getText()) && !"".equals(b13.getText()) && !"".equals(b21.getText()) && !"".equals(b22.getText()) && !"".equals(b23.getText()) && 
               !"".equals(b31.getText()) && !"".equals(b32.getText()) && !"".equals(b33.getText()) && itsAMatch() == false )
        {
            b11.setDisable(true);
            b12.setDisable(true);
            b13.setDisable(true);
            b21.setDisable(true);
            b22.setDisable(true);
            b23.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);
            
            lb1.setText("");
            playerTurn.setText("NOBODY");
            lb3.setText(" WON ! :(");
           
            newGame.setVisible(true);
            
            return true;
        }
        return false;
    }
    
    private boolean itsAMatch()
    {
        //linia 1
        if(!"".equals(b11.getText()) && b11.getText().equals(b12.getText()) && b12.getText().equals(b13.getText()))
        {
            b21.setDisable(true);
            b22.setDisable(true);
            b23.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);
            
            winnerCombo(b11,b12,b13);
            
            return true;
        }
        
        //linia2
        if(!"".equals(b21.getText()) && b21.getText().equals(b22.getText()) && b22.getText().equals(b23.getText()))
        {
            b11.setDisable(true);
            b12.setDisable(true);
            b13.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);
           
            winnerCombo(b21,b22,b23);
            
            return true;
        }
        
         //linia3
        if(!"".equals(b31.getText()) && b31.getText().equals(b32.getText()) && b32.getText().equals(b33.getText()))
        {
            b21.setDisable(true);
            b22.setDisable(true);
            b23.setDisable(true);
            b11.setDisable(true);
            b12.setDisable(true);
            b13.setDisable(true);
            
            winnerCombo(b31,b32,b33);
            
            return true;
        }
        
         //coloana1
        if(!"".equals(b11.getText()) && b11.getText().equals(b21.getText()) && b21.getText().equals(b31.getText()))
        {
            b12.setDisable(true);
            b13.setDisable(true);
            b23.setDisable(true);
            b22.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);
            
            winnerCombo(b11,b21,b31);
            
            return true;            
        }
        
          //coloana2
        if(!"".equals(b12.getText()) && b12.getText().equals(b22.getText()) && b22.getText().equals(b32.getText()))
        {
            b11.setDisable(true);
            b13.setDisable(true);
            b21.setDisable(true);
            b23.setDisable(true);
            b31.setDisable(true);
            b33.setDisable(true);
            
            winnerCombo(b12,b22,b32);
            
            return true;
        }
        
          //coloana3
        if(!"".equals(b13.getText()) && b13.getText().equals(b23.getText()) && b23.getText().equals(b33.getText()))
        {
            b11.setDisable(true);
            b12.setDisable(true);
            b21.setDisable(true);
            b22.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);
            
            winnerCombo(b13,b23,b33);
            
            return true;
        }
        
        //diagonala principala
        if(!"".equals(b11.getText()) && b11.getText().equals(b22.getText()) && b22.getText().equals(b33.getText()))
        {
            b12.setDisable(true);
            b13.setDisable(true);
            b21.setDisable(true);
            b23.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);
            
            winnerCombo(b11,b22,b33);
            
            return true;
        }
        
        //diagonala secundara
        if(!"".equals(b13.getText()) && b13.getText().equals(b22.getText()) && b22.getText().equals(b31.getText()))
        {
            b11.setDisable(true);
            b12.setDisable(true);
            b21.setDisable(true);
            b23.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);
            
            winnerCombo(b13,b22,b31);
            
            return true;
        }
        return false;
    }
    
    public void winnerCombo(Button b1, Button b2, Button b3)
    {
        newGame.setVisible(true);
        b1.setStyle("-fx-background-color: #FFD700;");
        b2.setStyle("-fx-background-color: #FFD700;");
        b3.setStyle("-fx-background-color: #FFD700;");
        
        if(b1.getText().equals("X"))
        {
            lb1.setText("");
            playerTurn.setText("PLAYER 1");
            lb3.setText(" WON !");
        }
        else
        {
            lb1.setText("");
            playerTurn.setText("PLAYER 2");
            lb3.setText(" WON !");
        }
    }
    
    @FXML
    void back(ActionEvent event)
    {
        main.showFirstPage();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
}
