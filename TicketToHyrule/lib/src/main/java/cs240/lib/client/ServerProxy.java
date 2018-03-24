package cs240.lib.client;


import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.common.Command;
import cs240.lib.common.IServer;
import cs240.lib.common.results.ChatResult;
import cs240.lib.common.results.ClaimRouteResult;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;
import cs240.lib.common.results.EndGameResult;
import cs240.lib.common.results.EndTurnResult;
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

/**
 * Server proxy is responsible for packaging parameters into commands
 * to be sent through the Client Communicator singleton to the server
 */
public class ServerProxy implements IServer {

    public static final ServerProxy SINGLETON = new ServerProxy();

    /**
     * Private Class Constructor for the singleton instance of the proxy.
     */
    private ServerProxy() {}

    /**
     * Prepares entered parameters for attempted login by a user.
     * Returns SignInResult of attempted login with potential error message.
     * @param username The entered username of the user trying to log in.
     * @param password The entered password of the user trying to log in.
     * @return Sign in result (success or failure) of the login with potential error message
     *
     * pre username and password must not be null
     * post a SignInResult with potential error message displaying what went wrong.
     */
    @Override
    public SignInResult login(String username, String password) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, password};
        Command loginCommand = new Command("login", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(loginCommand);
        return (SignInResult) result;
    }

    /**
     * Prepares entered parameters for attempted register by a user.
     * Returns SignInResult of attempted register with potential error message.
     * @param username The entered username of the user trying to register.
     * @param password The entered password of the user trying to register.
     * @return Sign in result (success or failure) of the register with potential error message
     *
     * pre username and password are not null
     * post SignInResult with success or failure of register with potential error message
     *
     */
    @Override
    public SignInResult register(String username, String password) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, password};
        Command registerCommand = new Command("register", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(registerCommand);
        return (SignInResult) result;
    }

    /**
     * Prepares entered parameters for user attempting to join a created game
     * Returns JoinResult of attempted join with potential error message.
     * @param username username of user attempting to join a game
     * @param gameName name of game user is attempting to join
     * @return JoinResult with potential error message
     *
     * pre - username and gameName are not null
     * post - JoinResult with potential error message
     */
    @Override
    public JoinResult joinGame(String username, String gameName) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, gameName};
        Command joinCommand = new Command("joinGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(joinCommand);
        return (JoinResult) result;
    }

    /**
     * Prepares parameters for user attempting to leave a game
     * @param username name of user attempting to leave a game
     * @param gameName name of game user is attempting to leave
     * @return LeaveResult with potential error message.
     *
     * pre - username and gameName are not null
     * post - LeaveResult with potential error message
     */
    @Override
    public LeaveResult leaveGame(String username, String gameName) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, gameName};
        Command leaveCommand = new Command("leaveGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(leaveCommand);
        return (LeaveResult) result;
    }

    /**
     * Prepares parameters for user attempting to create a game.
     * @param username name of user attempting to create a game
     * @param gameName name of game user is attempting to create
     * @param maxPlayers number of players required to start the game
     * @return CreateResult with potential error message
     *
     * pre username, gameName, and maxPlayers are not null
     * post CreateResult with potential error messsage
     */
    @Override
    public CreateResult createGame(String username, String gameName, int maxPlayers) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName(), int.class.getName()};
        Object[] parameters = {username, gameName, maxPlayers};
        Command createCommand = new Command("createGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(createCommand);
        return (CreateResult) result;
    }

    /**
     * Prepares parameters for attempted start of a game
     * @param gameName name of game attempting to be started
     * @return StartGameResult with potential error message
     *
     * pre  gameName is not null
     * post StartGameResult with potential error message
     */
    @Override
    public StartGameResult startGame(String gameName) {
        String[] parameterTypeNames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command startCommand = new Command("startGame", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(startCommand);
        return (StartGameResult) result;
    }

    /**
     * Retrieves unexecuted server commands
     * @param index position in the command queue of the server, enables query of entire command history
     * @return PollerResult with queue of unexecuted commands
     *
     * pre index is not null
     * post PollerResult with queue of unexecuted commands
     */
    @Override
    public PollerResult pollerCheckServer(int index) {
        String[] parameterTypeNames = {int.class.getName()};
        Object[] parameters = {index};
        Command pollerCommand = new Command("pollerCheckServer", parameterTypeNames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(pollerCommand);
        return (PollerResult)result;
    }

    /**
     * Prepares paramters for retrieving game history for specified game
     * @param gameName name of game containing game history
     * @return GameHistoryResult with game history object with potential error message
     *
     * pre gameName is not null
     * post GameHistoryResult with game history object with potential error message
     */
    @Override
    public GameHistoryResult getGameHistory(String gameName) {
        String[] parameterTypenames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command getGameHistoryCommand = new Command("getGameHistory", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(getGameHistoryCommand);
        return (GameHistoryResult)result;
    }

    /**
     * Prepares parameters to send specified message to specified gameName
     * @param playerName player who is sending specified message
     * @param message message to be sent
     * @param gameName name of game player is sending chat to
     * @return ChatResult with potential error message
     *
     * pre playerName, message, and gameName are not null
     * post ChatResult with potential error message
     */
    @Override
    public ChatResult chat(String playerName, String message, String gameName) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, message, gameName};
        Command chatCommand = new Command("chat", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(chatCommand);
        return (ChatResult) result;
    }

    @Override
    public ClaimRouteResult claimRoute(String playerName, String gameName, Route route, TrainCardColor chosenCardsColor) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName(), Route.class.getName(), TrainCardColor.class.getName()};
        Object[] parameters = {playerName, gameName, route, chosenCardsColor};
        Command claimRouteCommand = new Command("claimRoute", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(claimRouteCommand);
        return (ClaimRouteResult) result;
    }

    @Override
    public DrawTrainCardResult drawTrainCard(String playerName, String gameName) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, gameName};
        Command drawTrainCardCommand = new Command("drawTrainCard", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(drawTrainCardCommand);
        return (DrawTrainCardResult) result;
    }

    @Override
    public DrawFaceUpTrainCardResult drawFaceUpTrainCard(String playerName, String gameName, int positionPicked) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName(), int.class.getName()};
        Object[] parameters = {playerName, gameName, positionPicked};
        Command drawFaceUpTrainCardCommand = new Command("drawFaceUpTrainCard", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(drawFaceUpTrainCardCommand);
        return (DrawFaceUpTrainCardResult) result;
    }

    /**
     * Prepares paramters for drawing destination card from server.
     * Returns DrawDestinationCardResult with result of command.
     * @param playerName name of player drawing card
     * @param gameName name of game player is in
     * @return DrawDestinationCardResult with Destination card object and potential error message
     *
     * pre playerName and gameName are not null
     * post DrawDestinationCardResult with Destination card object and potential error message
     */
    @Override
    public DrawDestinationCardResult drawDestinationCard(String playerName, String gameName) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, gameName};
        Command drawDestinationCardCommand = new Command("drawDestinationCard", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(drawDestinationCardCommand);
        return (DrawDestinationCardResult) result;
    }

    /**
     * Prepares paramters for submitting chosen 0 or 1 destination card
     * @param playerName name of player submitting chosen 0 or 1 destination card
     * @param gameName name of game player belongs to
     * @param card 0 or 1 chosen destination card to submit
     * @return SubmitResult with potential error message
     *
     * pre playerName and gameName are not null
     * post SubmitResult with potential error message
     */
    @Override
    public SubmitResult submitDestinationCards(String playerName, String gameName, DestinationCard card) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName(), DestinationCard.class.getName()};
        Object[] parameters = {playerName, gameName, card};
        Command submitDestinationCardsCommand = new Command("submitDestinationCards", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(submitDestinationCardsCommand);
        return (SubmitResult) result;
    }

    @Override
    public SubmitResult submitDestinationCards(String playerName, String gameName, ArrayList<DestinationCard> cards) {
        //Check parameter type names
        String[] parameterTypenames = {String.class.getName(), String.class.getName(), DestinationCard.class.getName()};
        Object[] parameters = {playerName, gameName, cards};
        Command submitDestinationCardsCommand = new Command("submitDestinationCards", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(submitDestinationCardsCommand);
        return (SubmitResult) result;
    }

    @Override
    public EndTurnResult endTurn(String playerName, String gameName) {
        String[] parameterTypenames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, gameName};
        Command endTurnCommand = new Command("endTurn", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(endTurnCommand);
        return (EndTurnResult) result;
    }

    @Override
    public EndGameResult endGame(String gameName) {
        String[] parameterTypenames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command submitDestinationCardsCommand = new Command("endGame", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(submitDestinationCardsCommand);
        return (EndGameResult) result;
    }

    /**
     * Prepares parameters for retrieving game data from the server.
     * @param gameName name of game to retrieve game data
     * @return GetGameResult with game data and potential error message
     *
     * pre gameName is not null
     * post GetGameResult with game data and potential error message
     */
    @Override
    public GetGameResult getGameData(String gameName) {
        String[] parameterTypenames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command getGameCommand = new Command("getGameData", parameterTypenames, parameters);
        Object result = ClientCommunicator.SINGLETON.send(getGameCommand);
        return (GetGameResult) result;
    }
}
