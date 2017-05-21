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
    
    public boolean isRegistered(String username) throws SQLException 
    {      
        PreparedStatement pSt = connection.prepareStatement("select * from users where username = ?;");
        pSt.setString(1,username);
        ResultSet rez= pSt.executeQuery();

        return rez.first();      
    }
  
    public boolean checkCredentials(String username, String password) throws SQLException
    {
        PreparedStatement pSt = connection.prepareStatement("select * from users where username = ? and password = ?;");
        pSt.setString(1,username);
        pSt.setString(2,password);
        
        ResultSet rez= pSt.executeQuery();
        
        return rez.first();   
    }
    
    public UserData getUser(String username) throws SQLException
    {
       PreparedStatement pSt = connection.prepareStatement("select * from users where username = ? ;");
       
       pSt.setString(1,username);
       ResultSet rez= pSt.executeQuery();
        
       rez.next();
       
       UserData user = new UserData(rez.getString("firstName"),rez.getString("lastName"),rez.getString("email"),rez.getString("username"),rez.getString("password"),rez.getInt("wins"),rez.getInt("draws"),rez.getInt("played"));
              
       return user;
    }
    
    public boolean isAlreadyRegistered(String username, String email) throws SQLException
    {
        PreparedStatement pSt = connection.prepareStatement("select * from users where username = ? or email = ?;");
       
        pSt.setString(1,username);
        pSt.setString(2,email);
        
        ResultSet rez= pSt.executeQuery();
        return rez.first();
    }
    
    public void insertUser(UserData user) throws SQLException
    {
        statement = connection.createStatement();
        statement.executeUpdate("insert into users values ('" + user.getFirstName() + "','" + user.getLastName()+ "','"+user.getEmail() + "','"+user.getUsername()+"','"+user.getPassword()+"',"+user.getNumberOfWins()+","+user.getNumberOfDraws()+","+user.getNumberOfGamesPlayed()+");");
    }
    
    public void updateUser(UserData user) throws SQLException
    {
       statement = connection.createStatement();
       statement.executeUpdate("update users set wins = "+ user.getNumberOfWins() + ", draws = " + user.getNumberOfDraws() + ",played="+ user.getNumberOfGamesPlayed()+" where username = '"+ user.getUsername()+"';");
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
