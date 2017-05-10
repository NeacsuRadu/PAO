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
    
    public MessageTask(String message)
    {
        this.message = message;
    }
    
    public void processMessage()
    {
        System.out.println("The message is: " + message);
    }
}
