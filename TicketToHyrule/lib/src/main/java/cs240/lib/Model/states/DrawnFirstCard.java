package cs240.lib.Model.states;

import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.colors.TrainCardColor;
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

public class DrawnFirstCard implements IState {
    @Override
    public ClaimRouteResult claimRoute(Player container, String gameName, Route route, TrainCardColor chosenCardsColor) {
        //TODO: toast hand-slapping scold
        ClaimRouteResult result = new ClaimRouteResult("Already drawn card");
        return result;
    }

    @Override
    public DrawTrainCardResult drawTrainCard(Player container, String gameName) {
        DrawTrainCardResult result = ServerProxy.SINGLETON.drawTrainCard(container.getPlayerName(), gameName);
        container.setState(new TurnEnded());
        return result;
    }

    @Override
    public DrawDestinationCardResult drawDestinationCard(Player container, String gameName) {
        //TODO: toast hand-slapping scold
        DrawDestinationCardResult result = new DrawDestinationCardResult("Card already drawn");
        return result;
    }

    @Override
    public DrawFaceUpTrainCardResult drawFaceUpTrainCard(Player container, String gameName, int positionPicked) {
        DrawFaceUpTrainCardResult result =
                ServerProxy.SINGLETON.drawFaceUpTrainCard(container.getPlayerName(), gameName, positionPicked);
        container.setState(new TurnEnded());
        return  result;
    }

    @Override
    public DrawFaceUpTrainCardResult drawLocomotive(Player container, String gameName, int positionPicked) {
        //TODO: toast hand-slapping scold
        DrawFaceUpTrainCardResult result = new DrawFaceUpTrainCardResult("Cannot draw Locomotive as second card");
        return result;
    }
}
