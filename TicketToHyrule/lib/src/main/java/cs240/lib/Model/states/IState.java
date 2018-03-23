package cs240.lib.Model.states;

import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.common.results.ClaimRouteResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;
import cs240.lib.common.results.SubmitResult;

/**
 * Created by David on 3/19/2018.
 */

public interface IState {
    ClaimRouteResult claimRoute(Player container, String gameName, Route route, TrainCardColor chosenCardsColor);
    DrawTrainCardResult drawTrainCard(Player container, String gameName);
    DrawDestinationCardResult drawDestinationCard(Player container, String gameName);
    DrawFaceUpTrainCardResult drawFaceUpTrainCard(Player container, String gameName, int positionPicked);
    DrawFaceUpTrainCardResult drawLocomotive(Player container, String gameName, int positionPicked);
}
