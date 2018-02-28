package cs240.lib.Model.cards;

import java.util.ArrayList;

/**
 * Created by David on 2/28/2018.
 */

public class FaceUpTrainCards {
    private TrainCard[] faceUpCards;

    public FaceUpTrainCards(){
        faceUpCards = new TrainCard[5];
    }

    public TrainCard pick(int positionPicked){
        TrainCard picked = faceUpCards[positionPicked];
        faceUpCards[positionPicked] = TrainCardDeck.SINGLETON.draw();
        return picked;
    }

    public void shuffleIntoDeck(){
        ArrayList<TrainCard> temp = new ArrayList<>();
        for (int i = 0; i < faceUpCards.length; ++i){
            temp.add(faceUpCards[i]);
        }
        TrainCardDeck.SINGLETON.shuffleInDiscard(temp);
        for (int i = 0; i < faceUpCards.length; ++i){
            faceUpCards[i] = TrainCardDeck.SINGLETON.draw();
        }
    }
}
