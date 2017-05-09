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
public interface ClientEvents 
{
    public void NewClientConnection(Socket clientSocket);
    public void NewClientMessage(String message);
}
