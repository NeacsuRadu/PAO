/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class MessageListener implements Runnable 
{
    private Scanner socketScanner;
    private Thread workerThread;
    private MainController controller;
    
    public MessageListener(Scanner sc, MainController mainController)
    {
        this.socketScanner = sc;
        workerThread = new Thread(this);
        this.controller = mainController;
    }
    
    
    
    public void start()
    {
        workerThread.start();
    }
    
    @Override
    public void run()
    {
        String line;
        while (true)
        {
            try
            {
                line = socketScanner.nextLine();
            }
            catch(NoSuchElementException | IllegalStateException ex)
            {
                System.out.println("Problem: " + ex.getMessage());
                line = null;
            }
            if (line == null)
            {
                break;
            }
            processMessage(line);
        }
    }
    
    private void processMessage(String message)
    {
        System.out.println("New message: " + message);
        JSONObject messageJSON = new JSONObject(message);
        int type = messageJSON.getInt("type");
        switch (type)
        {
            case Messages.LOGIN:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                
                boolean bResult = messageData.getBoolean("result");
                if (bResult)
                {
                    UserData userData = new UserData(
                            messageData.getString("firstname"),
                            messageData.getString("lastname"),
                            messageData.getString("email"),
                            messageData.getString("username"),
                            messageData.getString("password"),
                            messageData.getInt("wins"),
                            messageData.getInt("draws"),
                            messageData.getInt("played")
                    );
                    controller.loginSuccessful(userData);
                }
                else 
                {
                   controller.invaliCredentials();
                }
                break;
            }
            case Messages.REGISTER:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                
                boolean bResult = messageData.getBoolean("result");
                System.out.println("REGISTER message result: " + bResult);
                if (bResult)
                {
                    controller.failderRegistration();
                }
                else 
                {
                    controller.successfulRegistration();
                }
                break;
            }
            case Messages.LOGOUT:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                String username = messageData.getString("username");
                controller.oponentDisconnected();
                break;
            }
            case Messages.GAME_REQUEST:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                
                int code = messageData.getInt("code");
                if (code == Messages.DOES_NOT_EXISTS) controller.setUserDoesNotExistsText(messageData.getString("to"));
                else if (code == Messages.IS_NOT_ONLINE) controller.setUserInNotOnlineText(messageData.getString("to"));
                else if (code == Messages.IS_ALREADY_PLAYING) controller.setUserIsAlreadyPlayingText(messageData.getString("to"));
                else if (code == Messages.PENDING_RESPONSE) controller.setWaitingResponseFromUserText(messageData.getString("to"));
                else if (code == Messages.REQUEST) controller.showRequestDialog(messageData.getString("from"));
                break;
            }
            case Messages.GAME_RESPONSE:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                
                controller.responseFromUser(messageData.getString("from"), messageData.getBoolean("accept"), messageData.getBoolean("firstplayer"));
                
                break;
            }
            case Messages.GAME_MOVE:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                
                controller.playerMadeAMove(messageData.getInt("row"), messageData.getInt("col"));
                
                break;
            }
            default:
            {
                break;
            }
           
        }
        
    }
}
