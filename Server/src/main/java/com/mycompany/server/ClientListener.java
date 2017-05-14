/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 *
 * @author Suzana
 */
public class ClientListener implements Runnable 
{
    
    static private ClientListener instance;
    static public ClientListener getInstance()
    {
        if(instance == null)
        {
            instance = new ClientListener();
        }
        return instance;
    }
    
    private ClientListener()
    {
        handlers = new ArrayList<>();
        workerThread = new Thread(this);
    }
    
    
    
    private ServerSocket srvSocket;
    public void Init() 
    {
        try
        {
            srvSocket = new ServerSocket(45000);
        }
        catch(IOException ex)
        {
            System.out.println("Client listener init ex: " + ex.getMessage());
        }
    }
    
    
    
   
    
    private ArrayList<ClientEvents> handlers;
    
    public void addEventHandler(ClientEvents handler)
    {
        handlers.add(handler);
    }
    
    public void removeEventHandler(ClientEvents handler)
    {
        handlers.remove(handler);
    }
    
    
    
    private Thread workerThread;
    
    public void start()
    {
        workerThread.start();
    }
    
    @Override 
    public void run()
    {
        System.out.println("Client listener, run method started.");
       while (true)
       {
            try 
            {
                System.out.println("Client listener, waiting for new client connection.");
                Socket clientSocket = srvSocket.accept();
                System.out.println("Client listener, new client connection.");
                for (ClientEvents handler : handlers)
                {   
                    handler.NewClientConnection(clientSocket);
                }
            } 
            catch (IOException ex) 
            {
                ex.getMessage();
            }
           
       }
    }
    
}
