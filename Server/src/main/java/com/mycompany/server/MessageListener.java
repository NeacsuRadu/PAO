/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class MessageListener implements Runnable
{
    private BufferedReader socketReader;
    private MessageHandler messageHandler;
    
    public MessageListener(BufferedReader buff)
    {
        socketReader = buff;
        messageHandler = MessageHandler.getInstance();
    }
    
    
    private Thread workerThread;
  
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
                line = socketReader.readLine();
                if(line == null)
                {
                    System.out.println("Connection closed !!!");
                    break;
                }
                messageHandler.addMessageTask(new MessageTask(line));
                messageHandler.releaseSemaphore();
            } 
            catch (IOException ex)
            {
                Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
