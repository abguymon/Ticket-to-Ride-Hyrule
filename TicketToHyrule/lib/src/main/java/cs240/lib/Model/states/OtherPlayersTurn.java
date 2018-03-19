package cs240.lib.Model.states;

import java.time.chrono.IsoChronology;

import cs240.lib.Model.gameParts.Player;

/**
 * Created by David on 3/19/2018.
 */

public class OtherPlayersTurn implements IState {

    //all does nothing, not your turn
    @Override
    public void claimRoute(Player container) {

    }

    @Override
    public void drawTrainCard(Player container) {

    }

    @Override
    public void drawDestinationCard(Player container) {

    }

    @Override
    public void drawFaceUpTrainCard(Player container) {

    }

    @Override
    public void drawLocomotive(Player container) {

    }
}
