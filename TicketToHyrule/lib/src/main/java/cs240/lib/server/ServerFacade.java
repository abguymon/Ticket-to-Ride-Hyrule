package server;

import com.sun.corba.se.spi.activation.Server;

import common.IServer;
import common.results.CreateResult;
import common.results.JoinResult;
import common.results.LeaveResult;
import common.results.SignInResult;

/**
 * Created by David on 1/13/2018.
 */

public class ServerFacade implements IServer {

    public static final ServerFacade SINGLETON = new ServerFacade();

    public ServerFacade(){}

    @Override
    public SignInResult login(String username, String password) {
        return ServerTarget.SINGLETON.login(username, password);
    }

    @Override
    public SignInResult register(String username, String password) {
        return ServerTarget.SINGLETON.register(username, password);
    }

    @Override
    public JoinResult joinGame(String username, String gameName) {
        return ServerTarget.SINGLETON.joinGame(username, gameName);
    }

    @Override
    public LeaveResult leaveGame(String username, String gameName) {
        return ServerTarget.SINGLETON.leaveGame(username, gameName);
    }

    @Override
    public CreateResult createGame(String username, String gameName, int maxPlayers) {
        return ServerTarget.SINGLETON.createGame(username, gameName, maxPlayers);
    }

    @Override
    public String startGame(String gameName) {
        return ServerTarget.SINGLETON.startGame(gameName);
    }
}
