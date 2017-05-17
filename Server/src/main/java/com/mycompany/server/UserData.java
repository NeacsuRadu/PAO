package com.mycompany.server;

public class UserData
{
    
    private String firstName,lastName,email,username,password;
    private int numberOfWins;
    private Client clientSocket = null;

    public UserData(String firstName, String lastName, String email, String username, String password, int numberOfWins) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.numberOfWins = numberOfWins;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public int getNumberOfWins()
    {
        return numberOfWins;
    }
    
    public Client getClientSocket()
    {
        return clientSocket;
    }

    public void setClientSocket(Client clientSocket)
    {
        this.clientSocket = clientSocket;
    }
    
    public void Winner()
    {
        ++ this.numberOfWins;
    }
    
    
}
