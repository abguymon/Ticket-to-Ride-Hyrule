package cs240.lib.server;

import java.util.ArrayList;
import java.util.Stack;

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
    public StartGameResult startGame(String gameName) {
        return Target.SINGLETON.startGame(gameName);
    }

    @Override
    public PollerResult pollerCheckServer(int index) {
        return Target.SINGLETON.pollerCheckServer(index);
    }

    @Override
    public GameHistoryResult getGameHistory(String gameName) {
        return Target.SINGLETON.getGameHistory(gameName);
    }

    @Override
    public ChatResult chat(String playerName, String message, String gameName) {
        return Target.SINGLETON.chat(playerName, message, gameName);
    }

    @Override
    public DrawDestinationCardResult drawDestinationCard(String playerName, String gameName) {
        return Target.SINGLETON.drawDestinationCard(playerName, gameName);
    }

    @Override
    public SubmitResult submitDestinationCards(String playerName, String gameName, DestinationCard card) {
        return Target.SINGLETON.submitDestinationCards(playerName, gameName, card);
    }

    @Override
    public SubmitResult discardDestinationCards(String playerName, String gameName, DestinationCard card1, DestinationCard card2) {
        return Target.SINGLETON.submitDestinationCards(playerName, gameName, card1, card2);
    }

    @Override
    public ClaimRouteResult claimRoute(String playerName, String gameName, Route route, TrainCardColor chosenCardsColor) {
        return Target.SINGLETON.claimRoute(playerName, gameName, route, chosenCardsColor);
    }

    @Override
    public DrawTrainCardResult drawTrainCard(String playerName, String gameName) {
        return Target.SINGLETON.drawTrainCard(playerName, gameName);
    }

    @Override
    public EndTurnResult endTurn(String playerName, String gameName) {
        return Target.SINGLETON.endTurn(playerName, gameName);
    }

    @Override
    public EndGameResult endGame(String gameName) {
        return Target.SINGLETON.endGame(gameName);
    }

    @Override
    public DrawFaceUpTrainCardResult drawFaceUpTrainCard(String playerName, String gameName, int positionPicked) {
        return Target.SINGLETON.drawFaceUpTrainCard(playerName, gameName, positionPicked);
    }

    @Override
    public GetGameResult getGameData(String gameName) {
        return Target.SINGLETON.getGameData(gameName);
    }
}
