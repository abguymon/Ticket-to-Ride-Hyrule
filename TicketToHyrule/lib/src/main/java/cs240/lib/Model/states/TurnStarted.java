package cs240.lib.Model.states;

import cs240.lib.Model.gameParts.Player;

/**
 * Created by David on 3/19/2018.
 */

public class TurnStarted implements IState {

    @Override
    public void claimRoute(Player container) {
        container.setState(new RouteClaimed());

    }

    @Override
    public void drawTrainCard(Player container) {
        container.setState(new DrawnFirstCard());
    }

    @Override
    public void drawDestinationCard(Player container) {
        container.setState(new DrawnFinalCard());
    }

    @Override
    public void drawFaceUpTrainCard(Player container) {
        container.setState(new DrawnFirstCard());
    }

    @Override
    public void drawLocomotive(Player container) {
        container.setState(new DrawnFinalCard());
    }
}
