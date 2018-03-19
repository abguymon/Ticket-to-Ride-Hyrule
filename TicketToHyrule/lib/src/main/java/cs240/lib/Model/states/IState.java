package cs240.lib.Model.states;

import cs240.lib.Model.gameParts.Player;

/**
 * Created by David on 3/19/2018.
 */

public interface IState {
    void claimRoute(Player container);
    void drawTrainCard(Player container);
    void drawDestinationCard(Player container);
    void drawFaceUpTrainCard(Player container);
    void drawLocomotive(Player container);
}
