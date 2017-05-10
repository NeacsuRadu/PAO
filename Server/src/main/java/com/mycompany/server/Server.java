/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.net.Socket;

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
        
    }
    
    
    @Override
    public void NewClientConnection(Socket clientSocket)
    {
        MessageHandler.getInstance().releaseSemaphore();
    }
    
}
