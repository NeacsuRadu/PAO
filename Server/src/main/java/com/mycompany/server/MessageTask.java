/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class MessageTask 
{
    private String message;
    private Client senderClient;
    
    public MessageTask(String message, Client senderClient)
    {
        this.message = message;
        this.senderClient = senderClient;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public Client getSender()
    {
        return senderClient;
    }
    
    
}
