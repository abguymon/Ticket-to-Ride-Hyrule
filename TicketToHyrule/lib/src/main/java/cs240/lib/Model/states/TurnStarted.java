package cs240.lib.Model.states;

import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.results.ClaimRouteResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;

/**
 * Created by David on 3/19/2018.
 */

public class TurnStarted implements IState {

    @Override
    public ClaimRouteResult claimRoute(Player container, String gameName, Route route) {
        ClaimRouteResult result = ServerProxy.SINGLETON.claimRoute(container.getPlayerName(), gameName, route);
        container.setState(new TurnEnded());
        return result;
    }

    @Override
    public DrawTrainCardResult drawTrainCard(Player container, String gameName) {
        DrawTrainCardResult result = ServerProxy.SINGLETON.drawTrainCard(container.getPlayerName(), gameName);
        container.setState(new DrawnFirstCard());
        return result;
    }

    @Override
    public DrawDestinationCardResult drawDestinationCard(Player container, String gameName) {
        DrawDestinationCardResult result = ServerProxy.SINGLETON.drawDestinationCard(container.getPlayerName(), gameName);
        //TODO: how to integrate with card selection fragment
        container.setState(new TurnEnded());
        return result;
    }

    @Override
    public DrawFaceUpTrainCardResult drawFaceUpTrainCard(Player container, String gameName, int positionPicked) {
        DrawFaceUpTrainCardResult result =
                ServerProxy.SINGLETON.drawFaceUpTrainCard(container.getPlayerName(), gameName, positionPicked);
        container.setState(new DrawnFirstCard());
        return result;
    }

    @Override
    public DrawFaceUpTrainCardResult drawLocomotive(Player container, String gameName, int positionPicked) {
        DrawFaceUpTrainCardResult result =
                ServerProxy.SINGLETON.drawFaceUpTrainCard(container.getPlayerName(), gameName, positionPicked);
        container.setState(new TurnEnded());
        return result;
    }
}
