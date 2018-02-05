package client;

import common.Command;
import common.IServer;
import common.results.CreateResult;
import common.results.JoinResult;
import common.results.LeaveResult;
import common.results.SignInResult;
import communicator.ClientCommunicator;

/**
 * Created by David on 1/17/2018.
 */

public class ServerProxy implements IServer {

    public static final ServerProxy SINGLETON = new ServerProxy();

    private ServerProxy() {}

    @Override
    public SignInResult login(String username, String password) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, password};
        Command loginCommand = new Command("login", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(loginCommand);
        return (SignInResult) result;
    }

    @Override
    public SignInResult register(String username, String password) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, password};
        Command registerCommand = new Command("register", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(registerCommand);
        return (SignInResult) result;
    }

    @Override
    public JoinResult joinGame(String username, String gameName) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, gameName};
        Command joinCommand = new Command("joinGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(joinCommand);
        return (JoinResult) result;
    }

    @Override
    public LeaveResult leaveGame(String username, String gameName) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, gameName};
        Command leaveCommand = new Command("leaveGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(leaveCommand);
        return (LeaveResult) result;
    }

    @Override
    public CreateResult createGame(String username, String gameName, int maxPlayers) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName(), int.class.getName()};
        Object[] parameters = {username, gameName, maxPlayers};
        Command createCommand = new Command("createGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(createCommand);
        return (CreateResult) result;
    }

    @Override
    public String startGame(String gameName) {
        String[] parameterTypeNames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command startCommand = new Command("startGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(startCommand);
        return (String)result;
    }
}
