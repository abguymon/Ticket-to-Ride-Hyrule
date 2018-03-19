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
    public void claimRoute(Player container, String gameName, Route route) {
        ClaimRouteResult result = ServerProxy.SINGLETON.claimRoute(container.getPlayerName(), gameName, route);
        if (result.getErrorMessage() == null) {
            container.setState(new TurnEnded());
        }else{
            //TODO: toast error message
        }

    }

    @Override
    public void drawTrainCard(Player container, String gameName) {
        DrawTrainCardResult result = ServerProxy.SINGLETON.drawTrainCard(container.getPlayerName(), gameName);
        TrainCard drawnCard = result.getDrawnCard();
        container.addTrainCard(drawnCard);
        container.setState(new DrawnFirstCard());
    }

    @Override
    public void drawDestinationCard(Player container, String gameName) {
        DrawDestinationCardResult result = ServerProxy.SINGLETON.drawDestinationCard(container.getPlayerName(), gameName);
        ArrayList<DestinationCard> cards = result.getCardArray();
        //TODO: how to integrate with card selection fragment
        container.setState(new TurnEnded());
    }

    @Override
    public void drawFaceUpTrainCard(Player container, String gameName, int positionPicked) {
        DrawFaceUpTrainCardResult result =
                ServerProxy.SINGLETON.drawFaceUpTrainCard(container.getPlayerName(), gameName, positionPicked);
        TrainCard cardPicked = result.getDrawnCard();
        container.addTrainCard(cardPicked);
        container.setState(new DrawnFirstCard());
    }

    @Override
    public void drawLocomotive(Player container, String gameName, int positionPicked) {
        DrawFaceUpTrainCardResult result =
                ServerProxy.SINGLETON.drawFaceUpTrainCard(container.getPlayerName(), gameName, positionPicked);
        TrainCard cardPicked = result.getDrawnCard();
        container.addTrainCard(cardPicked);
        container.setState(new TurnEnded());
    }
}
