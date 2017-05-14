/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
            msgList = new MessageListener(new Scanner(this.clientSocket.getInputStream()));
            socketOutput = new PrintWriter(this.clientSocket.getOutputStream(), true);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
