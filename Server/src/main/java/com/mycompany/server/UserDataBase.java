package com.mycompany.server;

import java.io.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDataBase 
{
    static private String hostname = "localhost";
    static private String database = "monkey";
    static private String usr = "luffy";
    static private String pass = "d.luffy69";
    static private int port = 3306;
    
    private Connection connection;
    private Statement statement;
    
    static private UserDataBase instance;
    static public UserDataBase getInstance()
    {
        if(instance == null)
        {
            instance = new UserDataBase();
        }
        return instance;
    }
    
    private UserDataBase()
    {
        userList = new ArrayList<>();
    
    }
    private ArrayList<UserData> userList;
    
    public boolean connect()
    {
        boolean connected = true;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?user=" + usr + "&password=" + pass);
            statement = connection.createStatement();
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            System.out.println("DataBase connect: " + ex.getMessage());
            connected = false;
        }
        return connected;
    }
    
    public boolean isRegistered(String username)
    {
        // checks if username is in the database :) tip: resSet.first returns boolean 
        
        return true;
    }
  
    public boolean checkCredentials(String username, String password)
    {
        return true; // if username password combination is valid. tip: retSet.first :D 
    }
    
    public UserData getUser(String username)
    {
        return null;
    }
    
    public boolean isAlreadyRegistered(String username, String email)
    {
        // when someone tries to register, we need to check if the username or email is already in out data base :D 
        
        return true;
    }
    
    public void insertUser(UserData user)
    {
        // statement.executeUpdate 
    }
    
    public void updateUser(UserData user)
    {
        // statement.executeUpdate 
    }
    
    public ArrayList<String> getUserNames()
    {
        ArrayList<String> usernames = new ArrayList();
        try 
        {
            ResultSet resSet = statement.executeQuery("SELECT username FROM users;");
            while (resSet.next())
            {
                usernames.add(resSet.getString("username"));
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("DataBase getUsernames: " + ex.getMessage());
        }
        return usernames;
    }
}
