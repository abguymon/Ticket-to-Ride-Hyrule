package cs240.lib.Model.states;

import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;

/**
 * Created by David on 3/19/2018.
 */

public interface IState {
    void claimRoute(Player container, String gameName, Route route);
    void drawTrainCard(Player container, String gameName);
    void drawDestinationCard(Player container, String gameName);
    void drawFaceUpTrainCard(Player container, String gameName, int positionPicked);
    void drawLocomotive(Player container, String gameName, int positionPicked);
}
