package cs240.lib.Model.gameParts;

import java.util.ArrayList;

import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.cards.TrainCardDiscard;
import cs240.lib.Model.colors.TrainCardColor;

/**
 * Created by David on 2/21/2018.
 */

public class Route {
    private CityPair cityNodes;
    private int length;
    private TrainCardColor color;
    private boolean claimed; //claimed during gameplay
    private Player owner; //assigned at claimed

    public Route(CityPair cityNodes, int length, TrainCardColor color){ //other fields are added as game progresses
        this.cityNodes = cityNodes;
        this.length = length;
        this.color = color;
    }

    public boolean claim(Player claimingPlayer, TrainCardDiscard discard, TrainCardColor chosenCardsColor){
        //for real claim route
        if (!claimed) {
            ArrayList<TrainCard> playerCards = claimingPlayer.getTrainCards();
            int cardColorNum = 0;
            ArrayList<Integer> foundColorCardsIndex = new ArrayList<>();
            for (int i = 0; i < playerCards.size(); ++i){
                if (playerCards.get(i).getColor().equals(chosenCardsColor)){
                    cardColorNum++;
                    foundColorCardsIndex.add(i);
                }
            }
            if (cardColorNum >= length && claimingPlayer.getTrainsRemaining() >= length) {
                setClaimed(true);
                setOwner(claimingPlayer);
                addPoints(claimingPlayer);
                claimingPlayer.minusTrains(length);
                discardCards(claimingPlayer, foundColorCardsIndex, discard);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
        //for test, needs to be deleted for real game
        /*if (!claimed){
            setClaimed(true);
            setOwner(claimingPlayer);
            addPoints(claimingPlayer);
        }*/
    }

    public boolean equals (Route route) {
        if (this.color == route.getColor()) {
            if (this.length == route.getLength()) {
                if (this.cityNodes.getCity1().getCityName().equals(route.getCityNodes().getCity1().getCityName())) {
                    if (this.cityNodes.getCity2().getCityName().equals(route.getCityNodes().getCity2().getCityName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void discardCards(Player claimingPlayer, ArrayList<Integer> foundColorCardsIndex, TrainCardDiscard discard) {
        ArrayList<TrainCard> cardsAfterDicard = claimingPlayer.getTrainCards();
        ArrayList<TrainCard> discarded = new ArrayList<>();
        for (int i = length - 1; i >= 0; --i){
            discard.add(cardsAfterDicard.get(foundColorCardsIndex.get(i))); //TODO: should the cards be placed in a discard list? Yep, DONE
            cardsAfterDicard.remove(foundColorCardsIndex.get(i).intValue());
        }
        claimingPlayer.setTrainCards(cardsAfterDicard);
    }

    private void addPoints(Player claimingPlayer) {
        int points = 0;
        switch (length){
            case (1):
                points = 1;
                break;
            case (2):
                points = 2;
                break;
            case (3):
                points = 4;
                break;
            case(4):
                points = 7;
                break;
            case (5):
                points = 10;
                break;
            case(6):
                points = 15;
                break;
        }
        claimingPlayer.addRoutePoints(points);
    }

    public CityPair getCityNodes() {
        return cityNodes;
    }

    public void setCityNodes(CityPair cityNodes) {
        this.cityNodes = cityNodes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public TrainCardColor getColor() {
        return color;
    }

    public void setColor(TrainCardColor color) {
        this.color = color;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public String toString(){
        String city1 = cityNodes.getCity1().getCityName();
        String city2 = cityNodes.getCity2().getCityName();
        String toString = city1 + " -- " + city2 + " | Length:" + length + " | Color:" + color;
        return toString;
    }
}
