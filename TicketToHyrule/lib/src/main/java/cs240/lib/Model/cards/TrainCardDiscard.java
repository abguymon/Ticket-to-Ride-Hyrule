package cs240.lib.Model.cards;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by savannah.jane on 3/2/2018.
 */

public class TrainCardDiscard {
    private ArrayList<TrainCard> trainCards;

    public TrainCardDiscard(){trainCards = new ArrayList<>();}
    public ArrayList<TrainCard> getTrainCards() {return trainCards;}
    public void setTrainCards(ArrayList<TrainCard> trainCards) {this.trainCards = trainCards;}
    public void add(TrainCard card) {trainCards.add(card);}
    public void reset() {trainCards = new ArrayList<>();}
}
