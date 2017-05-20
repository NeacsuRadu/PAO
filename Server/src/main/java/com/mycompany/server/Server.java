/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class Server implements ClientEvents
{
    static private Server instance;
    static public Server getInstance()
    {
        if (instance == null)
        {
            instance = new Server();
        }
        return instance;
    }
    
    private Server()
    {
        clients = new ArrayList();
        users = new HashMap();
    }
    
    private ArrayList<Client> clients;
    
    private HashMap<String, Client> users;
    
    
    public void loadHashMap()
    {
        ArrayList<String> usernames = UserDataBase.getInstance().getUserNames();
        for (String username : usernames)
        {
            users.put(username, null);
        }
    }
    
    public void setPlayState(String username, boolean playState)
    {
        users.get(username).setPlayState(playState);
    }
    
    public boolean checkOnlineUser(String username)
    {
        return (users.get(username) != null);
    }
    
    public boolean isUserPlaying(String username)
    {
        return (users.get(username).isPlaying());
    }
    
    public void userDisconnected(String username)
    {
        users.put(username, null);
    }
    
    public void sendMessage(String username, String message)
    {
        Client client = users.get(username);
        if (client != null)
        {
            client.SendMessage(message);
        }
    }
    
    public void userConnected(String username, Client client)
    {
        Client oldClient = users.get(username);
        
        if (oldClient != null)
        {
            oldClient.SendMessage("username");
        }
        users.put(username, client);
    }
    
    
    @Override
    public void NewClientConnection(Client client)
    {
        System.out.println("Server, new client connection");
        synchronized(clients)
        {
            clients.add(client);
        }
        client.AddHandler(this);
        client.startReceivingMessages();
    }
     
    @Override
    public void ClientDisconnected(Client client)
    {
        boolean retValue;
        System.out.println("Server: client disconnected.");
        synchronized(clients)
        {
            retValue = clients.remove(client);
        }
        System.out.println("retValue is " + retValue);
    }
    
}
