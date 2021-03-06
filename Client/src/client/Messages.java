/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import org.json.JSONObject;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class Messages 
{
    static final public int LOGIN = 0;
    static final public int REGISTER = 1;
    static final public int LOGOUT = 2;
    static final public int GAME_REQUEST = 3;
    static final public int GAME_RESPONSE = 4;
    static final public int GAME_MOVE = 5;
    static final public int WAIT_FOR_RESPONSE = 6;
    static final public int UPDATE_USER_DATA = 7;
    static final public int START_GAME = 8;
    
    static final public int DOES_NOT_EXISTS = 69;
    static final public int IS_NOT_ONLINE = 70;
    static final public int IS_ALREADY_PLAYING = 71;
    static final public int PENDING_RESPONSE = 72;
    static final public int REQUEST = 73;
    static final public int ALL_GOOD_MAN = 74;
  
    
    static public String getLoginMessage(String username, String password)
    {
        JSONObject messageData = new JSONObject();
        messageData.put("username", username);
        messageData.put("password", password);
        
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("type", LOGIN);
        messageJSON.put("data", messageData);
     
        return messageJSON.toString();
    }
    
    static public String getRegisterMessage(String firstName, String lastName, String email, String username, String password)
    {
        JSONObject messageData = new JSONObject();
        messageData.put("firstname", firstName);
        messageData.put("lastname", lastName);
        messageData.put("email", email);
        messageData.put("username", username);
        messageData.put("password", password);
        
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("type", REGISTER);
        messageJSON.put("data", messageData);
        
        return messageJSON.toString();
    }
    
    static public String getLogoutMessage(String username)
    {
        JSONObject messageData = new JSONObject();
        messageData.put("username", username);
        
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("type", LOGOUT);
        messageJSON.put("data", messageData);
        
        return messageJSON.toString();
    }
    
    static public String getRequestGameMessage(String username_from, String username_to)
    {
        JSONObject messageData = new JSONObject();
        messageData.put("to", username_to);
        messageData.put("from", username_from);
        
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("type", GAME_REQUEST);
        messageJSON.put("data", messageData);
        
        return messageJSON.toString();
    }
    
    static public String getResponseGameRequestMessage(boolean accept, String username_from, String username_to)
    {
        JSONObject messageData = new JSONObject();
        messageData.put("accept", accept);
        messageData.put("to", username_to);
        messageData.put("from", username_from);
        
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("type", GAME_RESPONSE);
        messageJSON.put("data", messageData);
        
        return messageJSON.toString();
    }
    
    static public String getMoveMessage(int row, int col, String username_from, String username_to)
    {
        JSONObject messageData = new JSONObject();
        messageData.put("row", row);
        messageData.put("col", col);
        messageData.put("to", username_to);
        messageData.put("from", username_from);
        
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("type", GAME_MOVE);
        messageJSON.put("data", messageData);
        
        return messageJSON.toString();
    }
    
    static public String getUpdateUserStatsMessage(String username, int gamesPlayed, int gamesWon, int drawGames)
    {
        JSONObject messageData = new JSONObject();
        messageData.put("username", username);
        messageData.put("played", gamesPlayed);
        messageData.put("wins", gamesWon);
        messageData.put("draws", drawGames);
        
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("type", UPDATE_USER_DATA);
        messageJSON.put("data", messageData);
        return messageJSON.toString();
    }
}
