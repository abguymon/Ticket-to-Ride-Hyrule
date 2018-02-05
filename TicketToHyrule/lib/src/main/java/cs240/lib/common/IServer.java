package common;

import common.results.CreateResult;
import common.results.JoinResult;
import common.results.LeaveResult;
import common.results.SignInResult;

/**
 * Created by David on 1/31/2018.
 */

public interface IServer {
    SignInResult login(String username, String password);
    SignInResult register(String username, String password);
    JoinResult joinGame(String username, String gameName);
    LeaveResult leaveGame(String username, String gameName);
    CreateResult createGame(String username, String gameName, int maxPlayers);
    String startGame(String gameName);
}
