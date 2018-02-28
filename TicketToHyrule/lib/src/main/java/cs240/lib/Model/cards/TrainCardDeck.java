package cs240.lib.Model.cards;

import java.util.ArrayList;
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
    }

    public void shuffle(){
        //TODO: finish the shuffle
        System.out.println("Shuffle not finished yet");
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
