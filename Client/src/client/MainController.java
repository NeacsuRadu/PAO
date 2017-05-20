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
    public void invaliCredentials();
    public void loginSuccessful(UserData userData);
    public void failderRegistration();
    public void successfulRegistration();
    
    public void setUserDoesNotExistsText(String username);
    public void setUserInNotOnlineText(String username);
    public void setUserIsAlreadyPlayingText(String username);
    public void setWaitingResponseFromUserText(String username);
    
    public void responseFromUser(String username, boolean accept);
    
    public void playerMadeAMove(int row, int col);
}
