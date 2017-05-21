/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.net.Socket;
import java.sql.SQLException;

/**
 *
 * @author Suzana
 */
public class Main
{
   
    public static void main(String args[]) throws SQLException
    {
        MessageHandler              msgHandler = MessageHandler.getInstance();
        Server                      server = Server.getInstance();
        ClientListener              clientListener = ClientListener.getInstance();
        UserDataBase                dataBaseInstance = UserDataBase.getInstance();
        
        if (!dataBaseInstance.connect())
        {
            System.out.println("DataBase failed to connect!!");
            return;
        }
        
        clientListener.addEventHandler(server);
        clientListener.Init();
        
        msgHandler.start();
        clientListener.start();
       
    }
    
    
    
    
   
    
    
}
