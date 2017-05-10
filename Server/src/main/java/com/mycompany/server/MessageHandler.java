/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class MessageHandler implements Runnable
{
    
    
    static private MessageHandler instance;
    static public MessageHandler getInstance()
    {
        if (instance == null)
        {
            instance = new MessageHandler();
        }
        return instance;
    }
    
    private MessageHandler()
    {
        semaphore = new Semaphore(0);
        workerThread = new Thread(this);
        taskQueue = new ArrayDeque();
    }
    
    
    private ArrayDeque<MessageTask> taskQueue;
    
    public synchronized void addMessageTask(MessageTask task)
    {
        taskQueue.addLast(task);
    }
    
    public synchronized MessageTask getTask()
    {
        return taskQueue.poll();
    }
    
    
    private Semaphore semaphore;
    
    public synchronized void releaseSemaphore()
    {
        semaphore.release();
    }
    
    
    
    private Thread workerThread;
    
    public void start()
    {
        workerThread.start();
    }
    
    @Override 
    public void run()
    {
        System.out.println("Message handler, run method started");
        while (true)
        {
            try 
            {
                semaphore.acquire();
                MessageTask task = getTask();
                if (task != null)
                {
                    task.processMessage();
                }
            } 
            catch (InterruptedException ex) 
            {
                System.out.println("Message handler exception: " + ex.getMessage());
            }
        }
    }
    
    
}
