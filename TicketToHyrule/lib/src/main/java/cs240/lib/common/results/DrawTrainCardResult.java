package cs240.lib.common.results;

import cs240.lib.Model.cards.TrainCard;

/**
 * Created by David on 3/19/2018.
 */

public class DrawTrainCardResult {
    private TrainCard drawnCard;
    private String errorMessage;

    public DrawTrainCardResult(TrainCard drawnCard) {
        this.drawnCard = drawnCard;
    }

    public DrawTrainCardResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public TrainCard getDrawnCard() {
        return drawnCard;
    }

    public void setDrawnCard(TrainCard drawnCard) {
        this.drawnCard = drawnCard;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
