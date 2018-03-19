package cs240.lib.Model.states;

import com.sun.security.ntlm.Server;

import cs240.lib.Model.gameParts.Player;
import cs240.lib.client.ServerProxy;

/**
 * Created by David on 3/19/2018.
 */

public class DrawnFirstCard implements IState {
    @Override
    public void claimRoute(Player container) {
        //nothing
    }

    @Override
    public void drawTrainCard(Player container) {
        container.setState(new DrawnFinalCard());
        //ServerProxy.SINGLETON.drawTrainCard(name, game);
    }

    @Override
    public void drawDestinationCard(Player container) {
        //nothing
    }

    @Override
    public void drawFaceUpTrainCard(Player container) {
        container.setState(new DrawnFinalCard());
        //ServerProxy.SINGLETON.drawFaceUpTrainCard(name, game, cardIndex);
    }

    @Override
    public void drawLocomotive(Player container) {
        //nothing
    }
}
