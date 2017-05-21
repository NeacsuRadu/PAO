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
public interface MainController 
{
    public void showRegisterPage();
    public void showFirstPage();
    public void showGamePage();
    
    public void sendMessage(String message);
    
    public UserData getUserData();
    public void setUserData(UserData userData);
    
    public void oponentDisconnected();
    
    public void invaliCredentials();
    public void loginSuccessful(UserData userData);
    public void failderRegistration();
    public void successfulRegistration();
    
    public void setUserDoesNotExistsText(String username);
    public void setUserInNotOnlineText(String username);
    public void setUserIsAlreadyPlayingText(String username);
    public void setWaitingResponseFromUserText(String username);
    
    public void showRequestDialog(String username);
    
    public void responseFromUser(String username, boolean accept, boolean firstPlayer);
    
    public void playerMadeAMove(int row, int col);
}
