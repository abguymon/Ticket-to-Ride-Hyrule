package cs240.lib.Model.cards;

import java.util.ArrayList;

import cs240.lib.Model.colors.TrainCardColor;

/**
 * Created by David on 2/28/2018.
 */

public class FaceUpTrainCards {
    private TrainCard[] faceUpCards;
    private int numWilds;

    public FaceUpTrainCards(TrainCardDeck deck, TrainCardDiscard discard){
        numWilds = 0;
        resetFaceUpTrainCards(deck, discard);
    }

    public TrainCard[] getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(TrainCard[] faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public TrainCard pick(int positionPicked, TrainCardDeck deck, TrainCardDiscard discard){
        TrainCard picked = faceUpCards[positionPicked];
        if (picked.getColor() == TrainCardColor.WILD) {
            --numWilds;
        }
        TrainCard card = deck.draw(discard);
        if (card != null) {
            faceUpCards[positionPicked] = card;
        }
        else {
            faceUpCards[positionPicked] = new TrainCard(TrainCardColor.GREY);
        }
        if (faceUpCards[positionPicked].getColor() == TrainCardColor.WILD) {
            ++numWilds;
        }
        if (numWilds >= 3) {
            discardFaceUpTrainCards(discard);
            resetFaceUpTrainCards(deck, discard);
        }
        return picked;
    }

    public void discardFaceUpTrainCards(TrainCardDiscard discard) {
        for (int i = 0; i < faceUpCards.length; ++i) {
            discard.add(faceUpCards[i]);
        }
    }

    public void resetFaceUpTrainCards(TrainCardDeck deck, TrainCardDiscard discard) {
        faceUpCards = new TrainCard[5];
        numWilds = 0;
        for (int i = 0; i < faceUpCards.length; ++i){
            TrainCard card = deck.draw(discard);
            if (card != null) {
                faceUpCards[i] = card;
            }
            else {
                faceUpCards[i] = new TrainCard(TrainCardColor.GREY);
            }
            if (faceUpCards[i].getColor() == TrainCardColor.WILD) {
                ++numWilds;
            }
        }
        if (numWilds >= 3) {
            discardFaceUpTrainCards(discard);
            resetFaceUpTrainCards(deck, discard);
        }
    }
}
