package cs240.lib.client;


import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.common.Command;
import cs240.lib.common.IServer;
import cs240.lib.common.results.ChatResult;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.GameHistoryResult;
import cs240.lib.common.results.GetGameResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.PollerResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.common.results.StartGameResult;
import cs240.lib.common.results.SubmitResult;
import cs240.lib.communicator.ClientCommunicator;

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
    public StartGameResult startGame(String gameName) {
        String[] parameterTypeNames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command startCommand = new Command("startGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(startCommand);
        return (StartGameResult) result;
    }

    @Override
    public PollerResult pollerCheckServer(int index) {
        String[] parameterTypeNames = {int.class.getName()};
        Object[] parameters = {index};
        Command pollerCommand = new Command("pollerCheckServer", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(pollerCommand);
        return (PollerResult)result;
    }

    @Override
    public GameHistoryResult getGameHistory(String gameName) {
        String[] parameterTypenames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command getGameHistoryCommand = new Command("getGameHistory", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(getGameHistoryCommand);
        return (GameHistoryResult)result;
    }

    @Override
    public ChatResult chat(String playerName, String message) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, message};
        Command chatCommand = new Command("chat", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(chatCommand);
        return (ChatResult) result;}

    @Override
    public DrawDestinationCardResult drawDestinationCard(String playerName, String gameName) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, gameName};
        Command drawDestinationCardCommand = new Command("drawDestinationCard", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(drawDestinationCardCommand);
        return (DrawDestinationCardResult) result;
    }

    @Override
    public SubmitResult submitDestinationCards(String playerName, String gameName, DestinationCard card) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName(), DestinationCard.class.getName()};
        Object[] parameters = {playerName, gameName, card};
        Command submitDestinationCardsCommand = new Command("submitDestinationCards", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(submitDestinationCardsCommand);
        return (SubmitResult) result;
    }

    @Override
    public GetGameResult getGame(String gameName) {
        String[] parameterTypenames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command getGameCommand = new Command("getGame", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(getGameCommand);
        return (GetGameResult) result;
    }
}
