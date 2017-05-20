/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Radu-Stefan Neacsu
 */


public class UserData
{
    
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private int numberOfWins;
    private int numberOfGamesPlayed;
    private int numberOfDraws;

    public UserData(String firstName, String lastName, String email, String username, String password, int numberOfWins, int numberOfDraws, int numberOfGamesPlayed) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.numberOfWins = numberOfWins;
        this.numberOfDraws = numberOfDraws;
        this.numberOfGamesPlayed = numberOfGamesPlayed;
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
    
    public int getNumberOfDraws()
    {
        return numberOfDraws;
    }
    
    public int getNumberOfGamesPlayed()
    {
        return numberOfGamesPlayed;
    }
    
    
}
