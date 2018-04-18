package cs240.lib.common.results;

import java.util.ArrayList;

import cs240.lib.Model.Game;
import cs240.lib.Model.LobbyGame;

/**
 * Created by David on 1/31/2018.
 */

public class SignInResult {
    private String authToken;
    private String errorMessage;
    private ArrayList<LobbyGame> userGames;

    public SignInResult(){}
    public SignInResult(String errorMessage){this.errorMessage = errorMessage;}
    public SignInResult(String authToken, String errorMessage, ArrayList<LobbyGame> userGames){
        this.authToken = authToken;
        this.errorMessage = errorMessage;
        this.userGames = userGames;
    }

    public ArrayList<LobbyGame> getUserGames() {
        return userGames;
    }

    public void setUserGames(ArrayList<LobbyGame> userGames) {
        this.userGames = userGames;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
