package com.mycompany.server;

import java.io.*;
import java.util.*;

public class UserDataBase 
{
    
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
        path = new String();
    }
    private ArrayList<UserData> userList;
    private String path = "data";
    
    public void load() throws FileNotFoundException
    {
       Scanner sc = new Scanner(new File (path));
       
       while(sc.hasNext())
       {
           String firstName,lastName,email,username,password;
           
           firstName = sc.next();
           lastName = sc.next();
           email = sc.next();
           username = sc.next();
           password = sc.next();
       }
       sc.close();
    }
   
    public void saveUser (UserData user) throws IOException
    {
        try (FileWriter out = new FileWriter(new File(path),true)) 
        {
            out.write("\n");
            out.write(user.getFirstName()+ " " +
                      user.getLastName() + " " +
                      user.getEmail()    + " " +
                      user.getUsername() + " " +
                      user.getPassword() );
            out.write("\n");
            
            out.close();
        }
        userList.add(user);
    }
    
    public boolean isOnline(String username)
    {
      for(int i = 0; i <= userList.size(); i ++ )
      {
          if(userList.get(i).getUsername().equals(username))
          {
              if(userList.get(i).getClientSocket() == null )
              {
                  return false;
              }
              else
              {
                  return true;
              }
          }
      }
        return false;
    }
    
    public boolean isValid(String username)
    {
        for(int i = 0; i <= userList.size(); i ++ )
      {
          if(userList.get(i).getUsername().equals(username))
            {
                return true;
            }
      }
        return false;
    }
    
    public boolean addIfPossible(UserData user) throws IOException
    {
        for(int i = 0; i <= userList.size(); i ++ )
      {
          if(userList.get(i).getUsername().equals(user.getUsername()))
          {
             return false;
          }
          if(userList.get(i).getEmail().equals(user.getEmail()))
          {
             return false;
          }
      }
        saveUser(user);
        return true;
    }

    public boolean isRegistered(String username, String password)
    {
        //modificari pt varianta cu +client
         for(int i = 0; i <= userList.size(); i ++ )
        {
          if(userList.get(i).getUsername().equals(username) && userList.get(i).getPassword().equals(password) )
            {
                return true;
            }
        }
         return false;
    }
}
