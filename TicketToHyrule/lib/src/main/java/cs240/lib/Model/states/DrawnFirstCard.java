package cs240.lib.Model.states;

import com.sun.security.ntlm.Server;

import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;

/**
 * Created by David on 3/19/2018.
 */

public class DrawnFirstCard implements IState {
    @Override
    public void claimRoute(Player container, String gameName, Route route) {
        //TODO: toast hand-slapping scold
    }

    @Override
    public void drawTrainCard(Player container, String gameName) {
        DrawTrainCardResult result = ServerProxy.SINGLETON.drawTrainCard(container.getPlayerName(), gameName);
        TrainCard drawnCard = result.getDrawnCard();
        container.addTrainCard(drawnCard);
        container.setState(new TurnEnded());
    }

    @Override
    public void drawDestinationCard(Player container, String gameName) {
        //TODO: toast hand-slapping scold
    }

    @Override
    public void drawFaceUpTrainCard(Player container, String gameName, int positionPicked) {
        DrawFaceUpTrainCardResult result =
                ServerProxy.SINGLETON.drawFaceUpTrainCard(container.getPlayerName(), gameName, positionPicked);
        TrainCard pickedCard = result.getDrawnCard();
        container.addTrainCard(pickedCard);
        container.setState(new TurnEnded());
    }

    @Override
    public void drawLocomotive(Player container, String gameName, int positionPicked) {
        //TODO: toast hand-slapping scold
    }
}
