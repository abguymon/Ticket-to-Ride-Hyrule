package cs240.lib.Model.gameParts;

import java.util.ArrayList;

import cs240.lib.Model.cards.TrainCard;
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

    public void claim(Player claimingPlayer){
        if (!claimed) {
            ArrayList<TrainCard> playerCards = claimingPlayer.getTrainCards();
            int cardColorNum = 0;
            ArrayList<Integer> foundColorCardsIndex = new ArrayList<>();
            for (int i = 0; i < playerCards.size(); ++i){
                if (playerCards.get(i).getColor().equals(this.color)){
                    cardColorNum++;
                    foundColorCardsIndex.add(i);
                }
            }
            if (cardColorNum >= length) {
                setClaimed(true);
                setOwner(claimingPlayer);
                addPoints(claimingPlayer);
                claimingPlayer.minusTrains(length);
                discardCards(claimingPlayer, foundColorCardsIndex);
            }
        }
    }

    private void discardCards(Player claimingPlayer, ArrayList<Integer> foundColorCardsIndex) {
        ArrayList<TrainCard> cardsAfterDicard = claimingPlayer.getTrainCards();
        ArrayList<TrainCard> discarded = new ArrayList<>();
        for (int i = length - 1; i >= 0; --i){
            discarded.add(cardsAfterDicard.get(foundColorCardsIndex.get(i))); //TODO: should the cards be placed in a discard list?
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
        claimingPlayer.addScore(points);
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
