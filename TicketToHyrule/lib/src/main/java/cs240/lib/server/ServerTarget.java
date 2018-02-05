package server;

import common.results.CreateResult;
import common.results.JoinResult;
import common.results.LeaveResult;
import common.results.SignInResult;

/**
 * Created by David on 1/13/2018.
 */

public class ServerTarget {
    public static final ServerTarget SINGLETON = new ServerTarget();

    private ServerTarget() {}

    SignInResult login(String username, String password){
        SignInResult result = new SignInResult();
        result.setAuthToken("auth1234567890");
        result.setErrorMessage("");
        return result;
    }
    SignInResult register(String username, String password){
        SignInResult result = new SignInResult();
        result.setAuthToken("auth0987654321");
        result.setErrorMessage("");
        return result;
    }
    JoinResult joinGame(String username, String gameName){
        JoinResult result = new JoinResult();
        result.setGameName(gameName);
        result.setNewPlayerNumber(2);
        result.setErrorMessage("");
        return result;
    }
    LeaveResult leaveGame(String username, String gameName){
        LeaveResult result = new LeaveResult();
        result.setGameName(gameName);
        result.setNewPlayerNumber(1);
        result.setErrorMessage("");
        return result;
    }
    CreateResult createGame(String username, String gameName, int maxPlayers){
        CreateResult result = new CreateResult();
        result.setGameName(gameName);
        result.setTotalPlayers(maxPlayers);
        result.setErrorMessage("");
        return result;
    }
    String startGame(String gameName){
        String result = "";
        return result;
    }
}
