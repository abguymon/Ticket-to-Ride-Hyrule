package cs240.lib.common;

import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.common.results.ChatResult;
import cs240.lib.common.results.ClaimRouteResult;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;
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
 * Created by David on 1/31/2018.
 */

public interface IServer {
    SignInResult login(String username, String password);
    SignInResult register(String username, String password);
    JoinResult joinGame(String username, String gameName);
    LeaveResult leaveGame(String username, String gameName);
    CreateResult createGame(String username, String gameName, int maxPlayers);
    StartGameResult startGame(String gameName);
    PollerResult pollerCheckServer(int index);
    GameHistoryResult getGameHistory(String gameName);
    ChatResult chat(String playerName, String message, String gameName);
    ClaimRouteResult claimRoute(String playerName, String gameName, Route route);
    DrawTrainCardResult drawTrainCard(String playerName, String gameName);
    DrawFaceUpTrainCardResult drawFaceUpTrainCard(String playerName, String gameName, int positionPicked);
    DrawDestinationCardResult drawDestinationCard(String playerName,String gameName);
    SubmitResult submitDestinationCards(String playerName, String gameName, DestinationCard card);
    SubmitResult submitDestinationCards(String playerName, String gameName, ArrayList<DestinationCard> cards);
    EndTurnResult endTurn(String gameName);
    GetGameResult getGameData(String gameName);
}