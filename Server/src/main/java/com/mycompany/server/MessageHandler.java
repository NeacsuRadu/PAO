/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
/**
 *
 * @author Radu-Stefan Neacsu
 */
public class MessageHandler implements Runnable
{
    static final private int LOGIN = 0;
    static final private int REGISTER = 1;
    static final private int LOGOUT = 2;
    static final private int GAME_REQUEST = 3;
    static final private int GAME_RESPONSE = 4;
    static final private int GAME_MOVE = 5;
    static final private int WAIT_FOR_RESPONSE = 6;
    
    static private MessageHandler instance;
    static public MessageHandler getInstance()
    {
        if (instance == null)
        {
            instance = new MessageHandler();
        }
        return instance;
    }
    
    private MessageHandler()
    {
        semaphore = new Semaphore(0);
        workerThread = new Thread(this);
        taskQueue = new ArrayDeque();
    }
    
    
    private ArrayDeque<MessageTask> taskQueue;
    
    public synchronized void addMessageTask(MessageTask task)
    {
        taskQueue.addLast(task);
    }
    
    public synchronized MessageTask getTask()
    {
        return taskQueue.poll();
    }
    
    
    private Semaphore semaphore;
    
    public synchronized void releaseSemaphore()
    {
        semaphore.release();
    }
    
    
    
    private Thread workerThread;
    
    public void start()
    {
        workerThread.start();
    }
    
    @Override 
    public void run()
    {
        System.out.println("Message handler, run method started");
        while (true)
        {
            try 
            {
                System.out.println("Message handler, waiting to process messages");
                semaphore.acquire();
                System.out.println("Message handler, new message.");
                MessageTask task = getTask();
                if (task != null)
                {
                    processTask(task);
                }
            } 
            catch (InterruptedException ex) 
            {
                System.out.println("Message handler exception: " + ex.getMessage());
            }
        }
    }
    
    private String getUserData(String username)
    {
        UserData user = UserDataBase.getInstance().getUser(username);
        JSONObject userData = new JSONObject();
        userData.put("result", true);
        userData.put("firstname", user.getFirstName());
        userData.put("lastname", user.getLastName());
        userData.put("email", user.getEmail());
        userData.put("username", user.getUsername());
        userData.put("played", user.getNumberOfGamesPlayed());
        userData.put("wins", user.getNumberOfWins());
        userData.put("draws", user.getNumberOfDraws());
        
        JSONObject message = new JSONObject();
        message.put("type", LOGIN);
        message.put("data", userData);
        
        return message.toString();
    }
    
    private String invalidLoginMessage()
    {
        JSONObject data = new JSONObject();
        data.put("result", false);
        
        JSONObject message = new JSONObject();
        message.put("type", LOGIN);
        message.put("data", data);
        
        return message.toString();
    }
    
    private String registeredMessage(boolean result)
    {
        JSONObject data = new JSONObject();
        data.put("result", result);
        
        JSONObject message = new JSONObject();
        message.put("type", REGISTER);
        message.put("data", data);
        
        return message.toString();
    }
    
    private String doesNotExistMessage()
    {
        return null;
    }
    
    private String isNotOnlineMessage()
    {
        return null;
    }
    
    private String isPlayingMessage()
    {
        return null;
    }
    
    private String waitForResponseMessage()
    {
        return null;
    }
    
    
    
    private void processTask(MessageTask task)
    {
        JSONObject messageJSON = new JSONObject(task.getMessage());
        
        int messageType = messageJSON.getInt("type");
        
        switch(messageType)
        {
            case LOGIN:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                String username = messageData.getString("username");
                String password = messageData.getString("password");
                
                boolean isValidCombination = UserDataBase.getInstance().checkCredentials(username, password);
                if (isValidCombination)
                {
                    task.getSender().SendMessage(getUserData(username));
                }
                else
                {
                    task.getSender().SendMessage(invalidLoginMessage());
                }
                break;
            }
            case REGISTER:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                String username = messageData.getString("username");
                String email = messageData.getString("email");
                
                boolean alreadyRegistered = UserDataBase.getInstance().isAlreadyRegistered(username, email);
                if (!alreadyRegistered)
                {
                    // insert user into the data base :D 
                    UserData user = new UserData(
                            messageData.getString("firstname"),
                            messageData.getString("lastname"),
                            messageData.getString("email"),
                            messageData.getString("username"),
                            messageData.getString("password"),
                            0, 0, 0);
                    UserDataBase.getInstance().insertUser(user);
                }
                task.getSender().SendMessage(registeredMessage(alreadyRegistered));
                break;
            }
            case LOGOUT:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                Server.getInstance().userDisconnected(messageData.getString("username"));
                break;
            }
            case GAME_REQUEST:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                String username_from = messageData.getString("from");
                String username_to   = messageData.getString("to");
                
                if (!UserDataBase.getInstance().isRegistered(username_to))
                {
                    task.getSender().SendMessage(doesNotExistMessage());
                }
                if (!Server.getInstance().checkOnlineUser(username_to))
                {
                    task.getSender().SendMessage(isNotOnlineMessage());
                }
                if (Server.getInstance().isUserPlaying(username_to))
                {
                    task.getSender().SendMessage(isPlayingMessage());
                }
                task.getSender().SendMessage(waitForResponseMessage());
                Server.getInstance().setPlayState(username_to, true);
                Server.getInstance().sendMessage(username_to, task.getMessage());
                break;
            }
            case GAME_RESPONSE:
            {
                JSONObject messageData = messageJSON.getJSONObject("data");
                String username_from = messageData.getString("from");
                String username_to = messageData.getString("to");
                boolean accept = messageData.getBoolean("accept");
                
                if (!Server.getInstance().checkOnlineUser(username_to) && accept)
                {
                    task.getSender().SendMessage(isNotOnlineMessage());
                    task.getSender().setPlayState(false);
                }
                if (Server.getInstance().isUserPlaying(username_to) && accept)
                {
                    task.getSender().SendMessage(isPlayingMessage());
                    task.getSender().setPlayState(false);
                }
                if (!Server.getInstance().isUserPlaying(username_to))
                {
                    Server.getInstance().sendMessage(username_to, task.getMessage());
                }
                
                break;
            }
            case GAME_MOVE:
            {
                
                break;
            }
            default:
                break;
        }
    }
    
}
