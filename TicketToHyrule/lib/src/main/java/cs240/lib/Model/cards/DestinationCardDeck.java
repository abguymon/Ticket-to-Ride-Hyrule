package cs240.lib.Model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by David on 2/28/2018.
 */

public class DestinationCardDeck {
    private Stack<DestinationCard> destinationCards;

    public DestinationCardDeck(){
        createDeck();
    }

    public void shuffle(){
        Collections.shuffle(destinationCards);
    }

    public DestinationCard draw(){
        DestinationCard toDraw = destinationCards.pop();
        return toDraw;
    }

    private void createDeck() {
        ArrayList<DestinationCard> temp = DestCardsList.SINGLETON.getDestinationCards();
        for (int i = 0; i < temp.size(); ++i){
            destinationCards.push(temp.get(i));
        }
    }
}
