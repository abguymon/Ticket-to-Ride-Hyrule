package cs240.lib.server;

import com.sun.corba.se.spi.activation.Server;

import java.util.Stack;

import cs240.lib.common.Command;
import cs240.lib.common.IServer;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.PollerResult;
import cs240.lib.common.results.SignInResult;

/**
 * Created by David on 1/13/2018.
 */

public class ServerFacade implements IServer {

    public static final ServerFacade SINGLETON = new ServerFacade();

    private Stack<Command> commands;

    public ServerFacade(){}

    public Stack<Command> getCommands() {return commands;}
    public void setCommands(Stack<Command> commands) {this.commands = commands;}
    public void addCommand(Command toadd){
        commands.push(toadd);
    }

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

    @Override
    public PollerResult pollerCheckServer() {
        return Target.SINGLETON.pollerCheckServer();
    }
}
