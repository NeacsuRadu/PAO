/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Client extends Application 
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
    
}
