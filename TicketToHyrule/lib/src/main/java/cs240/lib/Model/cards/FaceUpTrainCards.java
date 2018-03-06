package cs240.lib.Model.cards;

import java.util.ArrayList;

/**
 * Created by David on 2/28/2018.
 */

public class FaceUpTrainCards {
    private TrainCard[] faceUpCards;

    public FaceUpTrainCards(TrainCardDeck deck){
        faceUpCards = new TrainCard[5];
        for (int i = 0; i < 5; ++i){
            faceUpCards[i] = deck.draw();
        }
    }

    public TrainCard[] getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(TrainCard[] faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public TrainCard pick(int positionPicked, TrainCardDeck deck){
        TrainCard picked = faceUpCards[positionPicked];
        faceUpCards[positionPicked] = deck.draw();
        return picked;
    }

    public void shuffleIntoDeck(TrainCardDeck deck){
        ArrayList<TrainCard> temp = new ArrayList<>();
        for (int i = 0; i < faceUpCards.length; ++i){
            temp.add(faceUpCards[i]);
        }
        deck.shuffleInDiscard(temp);
        for (int i = 0; i < faceUpCards.length; ++i){
            faceUpCards[i] = deck.draw();
        }
    }
}
