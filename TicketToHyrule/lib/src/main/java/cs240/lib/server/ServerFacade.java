package cs240.lib.server;

import com.sun.corba.se.spi.activation.Server;

import cs240.lib.common.IServer;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.SignInResult;

/**
 * Created by David on 1/13/2018.
 */

public class ServerFacade implements IServer {

    public static final ServerFacade SINGLETON = new ServerFacade();

    public ServerFacade(){}

    @Override
    public SignInResult login(String username, String password) {
        return Target.SINGLETON.login(username, password);
    }

    @Override
    public SignInResult register(String username, String password) {
        return Target.SINGLETON.register(username, password);
    }

    @Override
    public JoinResult joinGame(String username, String gameName) {
        return Target.SINGLETON.joinGame(username, gameName);
    }

    @Override
    public LeaveResult leaveGame(String username, String gameName) {
        return Target.SINGLETON.leaveGame(username, gameName);
    }

    @Override
    public CreateResult createGame(String username, String gameName, int maxPlayers) {
        return Target.SINGLETON.createGame(username, gameName, maxPlayers);
    }

    @Override
    public String startGame(String gameName) {
        return Target.SINGLETON.startGame(gameName);
    }
}
