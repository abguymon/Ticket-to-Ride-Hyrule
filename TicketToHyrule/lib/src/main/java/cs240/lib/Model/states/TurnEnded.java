package cs240.lib.Model.states;

import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.common.results.ClaimRouteResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;

/**
 * Created by David on 3/19/2018.
 */

public class TurnEnded implements IState {

    @Override
    public ClaimRouteResult claimRoute(Player container, String gameName, Route route, TrainCardColor chosenCardsColor) {
        ClaimRouteResult result = new ClaimRouteResult("Not your turn");
        return result;
    }

    @Override
    public DrawTrainCardResult drawTrainCard(Player container, String gameName) {
        DrawTrainCardResult result = new DrawTrainCardResult("Not your turn");
        return result;
    }

    @Override
    public DrawDestinationCardResult drawDestinationCard(Player container, String gameName) {
        DrawDestinationCardResult result = new DrawDestinationCardResult("Not your turn");
        return result;
    }

    @Override
    public DrawFaceUpTrainCardResult drawFaceUpTrainCard(Player container, String gameName, int positionPicked) {
        DrawFaceUpTrainCardResult result = new DrawFaceUpTrainCardResult("Not your turn");
        return result;
    }

    @Override
    public DrawFaceUpTrainCardResult drawLocomotive(Player container, String gameName, int positionPicked) {
        DrawFaceUpTrainCardResult result = new DrawFaceUpTrainCardResult("Not your turn");
        return result;
    }
}
