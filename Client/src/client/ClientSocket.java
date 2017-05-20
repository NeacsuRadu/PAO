/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class ClientSocket 
{
    private Socket socket;
    private PrintWriter socketWriter;
    private MessageListener messageListener;
    
    public ClientSocket()
    {
        
    }
    
    private boolean init()
    {
        boolean bRet = true;
        try
        {
            socket = new Socket("localhost", 45000);
            socketWriter = new PrintWriter(socket.getOutputStream(), true);
            messageListener = new MessageListener(new Scanner(socket.getInputStream()));
        }
        catch(UnknownHostException ex)
        {
            System.out.println("Client socket init: " + ex.getMessage());
            bRet = false;
        }
        catch(IOException ex)
        {
            System.out.println("Client socket init: " + ex.getMessage());
            bRet = false;
        }
        return bRet;
    }
    
    public synchronized void sendMessage(String message)
    {
        socketWriter.println(message);
    }
    
    
    
}
