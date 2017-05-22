/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Suzana
 */
public class GamePageController implements Initializable 
{
    private MainController mainController;
    
    private Button[][] buttons;
    
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
    @FXML private Label user;
    
    @FXML private Label usernameLabel;
    @FXML private Label oponentLabel;
    @FXML private Label gamesPlayed;
    @FXML private Label gamesWon;
    @FXML private Label drawGames;
    @FXML private Label feedBackLabel;
    @FXML private Label invitationLabel;
    @FXML private Label firstPlayerLabel;
    @FXML private TextField insertOponent;
    @FXML private Button submitButton;
    @FXML private Button acceptButton;
    @FXML private Button declineButton;

    private boolean isFirstPlayer;
    private boolean shouldMove;
    private boolean inGame;
    
    public void setClient(MainController main)
    {
        this.mainController = main;
    }
    
    @FXML
    void newgame(ActionEvent event) 
    {
        mainController.showGamePage();
    }
    
    @FXML
    void onClick(ActionEvent event) 
    {
        Button clickedButton = (Button) event.getTarget();
        if (!shouldMove || !"".equals(clickedButton.getText()))
            return;
        
        String id = clickedButton.getId();
        String[] coord = id.split(",");
        int row = Integer.parseInt(coord[0]);
        int col = Integer.parseInt(coord[1]);
        
        clickedButton.setText(((isFirstPlayer == true) ? "X" : "0"));
        shouldMove = false;
        
        boolean iWon = itsAMatch(((isFirstPlayer == true) ? "X" : "0"), "-fx-background-color: #FFD700;");
        if (iWon)
        {
            updateWins();
            updateStats();
        }
        else if (noWinner())
        {
            updateDraws();
            updateStats();
        }
       
        mainController.sendMessage(Messages.getMoveMessage(row, col, usernameLabel.getText(), oponentLabel.getText()));
    }
    
    private boolean noWinner()
    {
        /*if( !"".equals(b11.getText()) && !"".equals(b12.getText()) && !"".equals(b13.getText()) && !"".equals(b21.getText()) && !"".equals(b22.getText()) && !"".equals(b23.getText()) && 
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
        }*/
        return false;
    }
    
    private boolean itsAMatch(String value, String style)
    {
        //linia 1
        if (value.equals(b11.getText()) && value.equals(b12.getText()) && value.equals(b13.getText()))
        {
            b21.setDisable(true);
            b22.setDisable(true);
            b23.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);
            winnerCombo(b11, b12, b13, style);
            return true;
        }
        
        //linia2
        if (value.equals(b21.getText()) && value.equals(b22.getText()) && value.equals(b23.getText()))
        {
            b11.setDisable(true);
            b12.setDisable(true);
            b13.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);
            winnerCombo(b21, b22, b23, style);
            return true;
        }
        
         //linia3
        if (value.equals(b31.getText()) && value.equals(b32.getText()) && value.equals(b33.getText()))
        {
            b21.setDisable(true);
            b22.setDisable(true);
            b23.setDisable(true);
            b11.setDisable(true);
            b12.setDisable(true);
            b13.setDisable(true); 
            winnerCombo(b31, b32, b33, style);
            
            return true;
        }
        
         //coloana1
        if (value.equals(b11.getText()) && value.equals(b21.getText()) && value.equals(b31.getText()))
        {
            b12.setDisable(true);
            b13.setDisable(true);
            b23.setDisable(true);
            b22.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);
            winnerCombo(b11, b21, b31, style);
            return true;            
        }
        
          //coloana2
        if (value.equals(b12.getText()) && value.equals(b22.getText()) && value.equals(b32.getText()))
        {
            b11.setDisable(true);
            b13.setDisable(true);
            b21.setDisable(true);
            b23.setDisable(true);
            b31.setDisable(true);
            b33.setDisable(true);
            winnerCombo(b12, b22, b32, style);           
            return true;
        }
        
          //coloana3
        if (value.equals(b13.getText()) && value.equals(b23.getText()) && value.equals(b33.getText()))
        {
            b11.setDisable(true);
            b12.setDisable(true);
            b21.setDisable(true);
            b22.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);           
            winnerCombo(b13, b23, b33, style); 
            return true;
        }
        
        //diagonala principala
        if (value.equals(b11.getText()) && value.equals(b22.getText()) && value.equals(b33.getText()))
        {
            b12.setDisable(true);
            b13.setDisable(true);
            b21.setDisable(true);
            b23.setDisable(true);
            b31.setDisable(true);
            b32.setDisable(true);
            winnerCombo(b11, b22, b33, style);           
            return true;
        }
        
        //diagonala secundara
        if (value.equals(b13.getText()) && value.equals(b22.getText()) && value.equals(b31.getText()))
        {
            b11.setDisable(true);
            b12.setDisable(true);
            b21.setDisable(true);
            b23.setDisable(true);
            b32.setDisable(true);
            b33.setDisable(true);       
            winnerCombo(b13, b22, b31, style);
            return true;
        }
        return false;
    }
    
    public void winnerCombo(Button b1, Button b2, Button b3, String style)
    {
        b1.setStyle(style);
        b2.setStyle(style);
        b3.setStyle(style);
        //"-fx-background-color: #FFD700;"
        /*if(b1.getText().equals("X"))
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
        }*/
    }
    
    @FXML
    void back(ActionEvent event)
    {
        mainController.sendMessage(Messages.getLogoutMessage(usernameLabel.getText()));
        mainController.showFirstPage();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        buttons = new Button[3][3];
        buttons[0][0] = b11;
        buttons[0][1] = b12;
        buttons[0][2] = b13;
        buttons[1][0] = b21;
        buttons[1][1] = b22;
        buttons[1][2] = b23;
        buttons[2][0] = b31;
        buttons[2][1] = b32;
        buttons[2][2] = b33;
    }    
    
    public void setStats(String username, int played, int won, int draws)
    {
        usernameLabel.setText(username);
        oponentLabel.setText("");
        gamesPlayed.setText("" + played);
        gamesWon.setText("" + won);
        drawGames.setText("" + draws);
        user.setText(username);
    }
    
    public void newIntivation(String username)
    {
        invitationLabel.setText(username);
        acceptButton.setDisable(false);
        declineButton.setDisable(false);
    }
    
    @FXML public void onClickSubmitButton(ActionEvent event) 
    {
        mainController.sendMessage(Messages.getRequestGameMessage(usernameLabel.getText(), insertOponent.getText()));
    }
    
    @FXML public void onClickAccept(ActionEvent event)
    {
        acceptButton.setDisable(true);
        declineButton.setDisable(true);
        isFirstPlayer = decideFirstPlayer();
        shouldMove = isFirstPlayer;
        oponentLabel.setText(invitationLabel.getText());
        
        if (isFirstPlayer)
        {
            firstPlayerLabel.setText("First Player");
        }
        else 
        {
            firstPlayerLabel.setText("Second Player");
        }
        
        mainController.sendMessage(Messages.getResponseGameRequestMessage(true, !isFirstPlayer, usernameLabel.getText(), invitationLabel.getText()));
    }
    
    @FXML public void onClickDecline(ActionEvent event)
    {
        acceptButton.setDisable(true);
        declineButton.setDisable(true);
        mainController.sendMessage(Messages.getResponseGameRequestMessage(true, false, usernameLabel.getText(), invitationLabel.getText()));
    }
    
    public void setFeedbackLabelText(String text)
    {
        feedBackLabel.setText(text);        
    }
    
    public void responseFromUser(String username, boolean accept, boolean firstPlayer)
    {
        if (accept)
        {
            // adica aici. ii curatam butoanele alea 
            oponentLabel.setText(username);
            isFirstPlayer = firstPlayer;
            shouldMove = isFirstPlayer;
            if (isFirstPlayer)
            {
                firstPlayerLabel.setText("First Player");
            }
            else 
            {
                firstPlayerLabel.setText("Second Player");
            }
        }
        else 
        {
            feedBackLabel.setText(username + " declined your invitation");
        }
    }
    
    public void playerMadeAMove(int row, int col)
    {
        buttons[row-1][col-1].setText(((isFirstPlayer == true) ? "0" : "X"));
        boolean heWins = itsAMatch(((isFirstPlayer == true) ? "0" : "X"), "-fx-background-color: #FF0000;");
        if (heWins)
        {
            updatePlayed();
            updateStats();
            shouldMove = false;
            return;
        }
        else if (noWinner())
        {
            updateDraws();
            updateStats();
            shouldMove = false;
            return;
        }
        shouldMove = true;
    }
    
    private boolean decideFirstPlayer()
    {     
        return (new Random().nextInt() == 0);
    }
    
    private void updateWins()
    {
        int wins = Integer.parseInt(gamesWon.getText()) + 1;
        int played = Integer.parseInt(gamesPlayed.getText()) + 1;
        gamesWon.setText("" + wins);
        gamesPlayed.setText("" + played);
        mainController.getUserData().increaseWins();
        mainController.getUserData().increasePlayed();
    }
    
    private void updateDraws()
    {
        int draws = Integer.parseInt(drawGames.getText()) + 1;
        int played = Integer.parseInt(gamesPlayed.getText()) + 1;
        drawGames.setText("" + draws);
        gamesPlayed.setText("" + played);
        mainController.getUserData().increaseDraws();
        mainController.getUserData().increasePlayed();
    }
    
    private void updatePlayed()
    {
        int played = Integer.parseInt(gamesPlayed.getText()) + 1;
        gamesPlayed.setText("" + played);
        mainController.getUserData().increasePlayed();
    }
    
    private void updateStats()
    {
        mainController.sendMessage(Messages.getUpdateUserStatsMessage(mainController.getUserData().getUsername(), 
                                                                          mainController.getUserData().getNumberOfGamesPlayed(),
                                                                          mainController.getUserData().getNumberOfWins(),
                                                                          mainController.getUserData().getNumberOfDraws()));
    }
}
