package cs240.lib.Model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

import cs240.lib.Model.colors.TrainCardColor;

/**
 * Created by David on 2/27/2018.
 */

public class TrainCardDeck {
    private Stack<TrainCard> trainCards;
    public TrainCardDeck(){
        createDeck();
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(trainCards);
    }

    public Stack<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(Stack<TrainCard> trainCards) {
        this.trainCards = trainCards;
    }

    public void add(TrainCard newCard) {trainCards.add(newCard);}

    public void shuffleInDiscard(ArrayList<TrainCard> discarded){

    }

    public TrainCard draw(TrainCardDiscard discard){
        if (trainCards.size() == 0) {
            if (!shuffleInDiscard(discard)) {
                return null;
            }
        }
        TrainCard toDraw = trainCards.pop();
        return toDraw;
    }

    private boolean shuffleInDiscard(TrainCardDiscard discard) {
        if (discard.getTrainCards().size() != 0) {
            for (int i = 0; i < discard.getTrainCards().size(); ++i) {
                TrainCard cardToAdd = discard.getTrainCards().get(i);
                trainCards.add(cardToAdd);
            }
            shuffle();
            discard.reset();
            return true;
        }
        return false;
    }

    public int getSize()
    {
        return trainCards.size();
    }

    private void createDeck() {
        trainCards = new Stack<>();
        TrainCard blue = new TrainCard(TrainCardColor.BLUE);
        TrainCard red = new TrainCard(TrainCardColor.RED);
        TrainCard green = new TrainCard(TrainCardColor.GREEN);
        TrainCard yellow = new TrainCard(TrainCardColor.YELLOW);
        TrainCard pink = new TrainCard(TrainCardColor.PINK);
        TrainCard orange = new TrainCard(TrainCardColor.ORANGE);
        TrainCard black = new TrainCard(TrainCardColor.BLACK);
        TrainCard white = new TrainCard(TrainCardColor.WHITE);
        TrainCard wild = new TrainCard(TrainCardColor.WILD);

        insertColoredCards(blue);
        insertColoredCards(red);
        insertColoredCards(green);
        insertColoredCards(yellow);
        insertColoredCards(pink);
        insertColoredCards(orange);
        insertColoredCards(black);
        insertColoredCards(white);
        insertWildCards(wild);
    }

    private void insertWildCards(TrainCard wild) {
        for (int i = 0; i < 14; ++i){
            trainCards.push(wild);
        }
    }

    private void insertColoredCards(TrainCard color) {
        for (int i = 0; i < 12; ++i){
            trainCards.push(color);
        }
    }
}
