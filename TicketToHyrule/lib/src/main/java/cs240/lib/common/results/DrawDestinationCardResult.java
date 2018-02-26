package cs240.lib.common.results;

import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;

/**
 * Created by savannah.jane on 2/26/2018.
 */

public class DrawDestinationCardResult {
    private ArrayList<DestinationCard> cardArray;
    private String errorMessage;

    public DrawDestinationCardResult(ArrayList<DestinationCard> cardArray) {
        this.cardArray = cardArray;
    }

    public DrawDestinationCardResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<DestinationCard> getCardArray() {
        return cardArray;
    }

    public void setCardArray(ArrayList<DestinationCard> cardArray) {
        this.cardArray = cardArray;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
