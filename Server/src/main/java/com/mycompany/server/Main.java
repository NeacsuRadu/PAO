/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.net.Socket;

/**
 *
 * @author Suzana
 */
public class Main
{
   
    public static void main(String args[])
    {
        MessageHandler msgInstance = MessageHandler.getInstance();
        msgInstance.start();
        
        
        Server serverInstance = Server.getInstance();
        ClientListener clInstance = ClientListener.getInstance();
        clInstance.addEventHandler(serverInstance);
        clInstance.Init();
        
        clInstance.start();
    }
    
    
    
    
   
    
    
}
