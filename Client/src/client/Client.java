/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Client extends Application implements MainController 
{
    private Stage primaryStage;
    
    private FXMLLoader firstPageLoader;
    private FXMLLoader registerPageLoader;
    private FXMLLoader gamePageLoader;
    
    private FirstPageController firstPageController;
    private RegisterPageController registerPageController;
    private GamePageController gamePageController;
    
    private Scene firstPageScene;
    private Scene registerPageScene;
    private Scene gamePageScene;
    
    private ClientSocket clientSocket;
    
    private UserData userData;
    
    public boolean initialize()
    {
        boolean bRet = true;
        try
        {            
            firstPageLoader = new FXMLLoader();
            registerPageLoader = new FXMLLoader();
            gamePageLoader = new FXMLLoader();

            firstPageLoader.setLocation(Client.class.getResource("firstPage.fxml"));
            registerPageLoader.setLocation(Client.class.getResource("registerPage.fxml"));
            gamePageLoader.setLocation(Client.class.getResource("gamePage.fxml")); 

            Parent rootFirst = firstPageLoader.load();
            Parent rootRegister = registerPageLoader.load();
            Parent rootGame = gamePageLoader.load();

            firstPageScene = new Scene(rootFirst);
            registerPageScene = new Scene(rootRegister);
            gamePageScene = new Scene(rootGame);

            firstPageController = firstPageLoader.getController();
            registerPageController = registerPageLoader.getController();
            gamePageController = gamePageLoader.getController();

            firstPageController.setClient(this);
            registerPageController.setClient(this);
            gamePageController.setClient(this);
            
            clientSocket = new ClientSocket(this);
            if (!clientSocket.init())
            {
                // add code to tell the user that he needs to pay his bills 
                System.out.println("Failed to init the connection with the server !!");
                clientSocket = null;
            }
            System.out.println("All good");
        }
        catch(IOException ex)
        {
            bRet = false;
            System.out.println("Failed to inialize: " + ex.getMessage());
        }
        
        return bRet;
    }
    
    @Override
    public void start(Stage primaryStage)
    {    
        this.primaryStage = primaryStage;
        if (!initialize())
            return;
        
        this.primaryStage.setTitle("Fucking tic tac toe");
        this.primaryStage.setResizable(false);
        primaryStage.setScene(firstPageScene);
        firstPageController.cleanUp();
        primaryStage.show();
    }
    
    @Override
    public boolean isClientConnected()
    {
        return (clientSocket != null);
    }
    
    @Override
    public void showRegisterPage()
    {
        primaryStage.setScene(registerPageScene);
        registerPageController.cleanUp(); 
        primaryStage.show();
    }
    
    @Override
    public void showFirstPage()
    {
        primaryStage.setScene(firstPageScene);
        firstPageController.cleanUp();
        primaryStage.show();
    }
    
    @Override
    public void showGamePage()
    {
        primaryStage.setScene(gamePageScene);
        //cleanUp si aici :) 
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    // ------------------------- MAIN CONTROOLER IMPLEMENTATION -------------------------
    
    @Override
    public UserData getUserData()
    {
        return userData;
    }
    
    @Override
    public void setUserData(UserData userData)
    {
        this.userData = userData;
    }
    
    @Override 
    public void sendMessage(String message)
    {
        if (clientSocket == null)
            return;
        System.out.println("Sending message: " + message);
        clientSocket.sendMessage(message);
    }
    
    @Override
    public void invaliCredentials()
    {
        Platform.runLater(
        ()->
        {
            firstPageController.showInvalideUsernamePasswordCombination();
        });
    }
    
    @Override
    public void loginSuccessful(UserData userData)
    {
        Platform.runLater( 
        () -> 
        {
            System.out.println("altceva");
            this.userData = userData;
            this.gamePageController.setStats(userData.getUsername(), userData.getNumberOfGamesPlayed(), userData.getNumberOfWins(), userData.getNumberOfDraws());
            this.showGamePage();
        });
    }
    
    @Override
    public void failderRegistration()
    {
        Platform.runLater(
        ()->      
        {
            registerPageController.showRegistrationFailedLabel();
        });
    }
    
    @Override
    public void successfulRegistration()
    {
        Platform.runLater(
        ()->
        {
            System.out.println("ceva");
            this.showFirstPage();
        });
    }
    
    @Override
    public void setUserDoesNotExistsText(String username)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.setFeedbackLabelText(username + " is not a valid username");
        }); 
    }
    
    @Override
    public void setUserInNotOnlineText(String username)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.setFeedbackLabelText(username + " is not online right now");
        });
    }
    
    @Override
    public void setUserIsAlreadyPlayingText(String username)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.setFeedbackLabelText(username + " is already in a game");
        });
    }
    
    @Override
    public void setWaitingResponseFromUserText(String username)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.setFeedbackLabelText(username + " has been notified");
        });
    }
    
    @Override
    public void showRequestDialog(String username)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.newIntivation(username);
        });
    }
    
    @Override
    public void oponentDisconnected()
    {
        
    }
    
    @Override
    public void responseFromUser(String username, boolean accept)
    {
        Platform.runLater(
        ()->
        {    
            gamePageController.responseFromUser(username, accept);
        });
    }
    
    @Override
    public void playerMadeAMove(int row, int col)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.playerMadeAMove(row, col);
        });
    }
    
    @Override
    public void playerIsNotOnlineAnyMore(String username)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.setResponseErrorText(username + " is not online anymore.");
        });     
    }
    
    @Override
    public void playerEnteredAGame(String username)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.setResponseErrorText(username + " entered a game.");
        });     
    }
    
    @Override
    public void startTheGame(String username, boolean firstPlayer)
    {
        Platform.runLater(
        ()->
        {
            gamePageController.startTheGame(username, firstPlayer);
        }); 
    }
}
