/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class Client 
{
    private Socket clientSocket; 
    private MessageListener msgList;
    private PrintWriter socketOutput;   
    private ArrayList<ClientEvents> handlers;
    private boolean isPlaying;
    private String oponent;
    
    public void startReceivingMessages()
    {
        System.out.println("Client, start receiving messaged");
        msgList.start();
    }
    
    public Client(Socket clientSocket)
    {
        try 
        {
            this.clientSocket = clientSocket;
            msgList = new MessageListener(new Scanner(this.clientSocket.getInputStream()), this);
            socketOutput = new PrintWriter(this.clientSocket.getOutputStream(), true);
            handlers = new ArrayList();
            isPlaying = false;
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SendMessage(String message)
    {
        synchronized(socketOutput)
        {
            socketOutput.println(message);
        }
    }
    
    public void AddHandler(ClientEvents handler)
    {
        handlers.add(handler);
    }
    
    public void ClientDisconnected()
    {
        for(ClientEvents handler : handlers)
        {
            handler.ClientDisconnected(this);
        }
    }
    
    public boolean isPlaying()
    {
        return this.isPlaying;
    }
    
    public String getOponent()
    {
        return this.oponent;
    }
    
    public void setOponent(String oponent)
    {
        this.oponent = oponent;
    }
    
    public void setPlayState(boolean isPlaying)
    {
        this.isPlaying = isPlaying;
    }
}
