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
    
    public void initialize() throws IOException {
        
        firstPageLoader = new FXMLLoader();
        registerPageLoader = new FXMLLoader();
        gamePageLoader = new FXMLLoader();
        
        firstPageLoader.setLocation(Client.class.getResource("firstPage.fxml"));
        registerPageLoader.setLocation(Client.class.getResource("registerPage.fxml"));
        gamePageLoader.setLocation(Client.class.getResource("gamePage.fxml")); 
        
        //registerPageController = registerPageLoader.getController();
        //gamePageController = gamePageLoader.getController();
       
        
    }
    
     @Override
    public void start(Stage primaryStage) throws Exception {
        
        this.primaryStage = primaryStage;
        initialize();
       
        Parent root = firstPageLoader.load();
        firstPageController = firstPageLoader.getController();
        firstPageController.setClient(this);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void showRegisterPage() throws IOException {
        
        Parent root = registerPageLoader.load();
        firstPageController = firstPageLoader.getController();
        firstPageController.setClient(this);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
