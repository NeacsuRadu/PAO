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
        primaryStage.show();
    }
    
    public void showRegisterPage()
    {
        primaryStage.setScene(registerPageScene);
        primaryStage.show();
    }
    
    public void showFirstPage()
    {
        primaryStage.setScene(firstPageScene);
        primaryStage.show();
    }
    
    public void showGamePage()
    {
        primaryStage.setScene(gamePageScene);
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
    public void sendMessage(String message)
    {
        System.out.println("Sending message: " + message);
        clientSocket.sendMessage(message);
    }
    
    @Override
    public void invaliCredentials()
    {
        firstPageController.showInvalideUsernamePasswordCombination();
    }
    
    @Override
    public void loginSuccessful(UserData userData)
    {
        Platform.runLater( 
        () -> 
        {
            System.out.println("altceva");
            this.showGamePage();
        });
    }
    
    @Override
    public void failderRegistration()
    {
        registerPageController.showRegistrationFailedLabel();
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
        // tell him the user he entered does not exists 
    }
    
    @Override
    public void setUserInNotOnlineText(String username)
    {
        // tell him the user he entered does not existts
    }
    
    @Override
    public void setUserIsAlreadyPlayingText(String username)
    {
        // tell him the user he entered is already playing 
    }
    
    @Override
    public void setWaitingResponseFromUserText(String username)
    {
        // the user he entered was notified, need to wait confirmation 
    }
    
    @Override
    public void responseFromUser(String username, boolean accept)
    {
        // some response for the request made earlier 
    }
    
    @Override
    public void playerMadeAMove(int row, int col)
    {
        // a move was made, need to update :) s
    }
    
}
