package cs240.lib.Model.gameParts;

import java.util.ArrayList;

import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.cards.TrainCardDiscard;
import cs240.lib.Model.colors.PlayerColor;
import cs240.lib.Model.colors.TrainCardColor;

/**
 * Created by David on 2/21/2018.
 */

public class Route {
    private CityPair cityNodes;
    private int length;
    private TrainCardColor color;
    private boolean claimed; //claimed during gameplay
    private String owner; //assigned at claimed
    private PlayerColor ownerColor;

    public Route(CityPair cityNodes, int length, TrainCardColor color){ //other fields are added as game progresses
        this.cityNodes = cityNodes;
        this.length = length;
        this.color = color;
        this.owner = null;
    }

    public String getCity1Name() {return cityNodes.getCity1().getCityName();}
    public String getCity2Name() {return cityNodes.getCity2().getCityName();}

    public boolean claim(Player claimingPlayer, TrainCardDiscard discard, TrainCardColor chosenCardsColor){
        //for real claim route
        if (!claimed) {
            ArrayList<TrainCard> playerCards = claimingPlayer.getTrainCards();
            int cardColorNum = 0;
            int wildCardNum = 0;
            ArrayList<Integer> foundColorCardsIndex = new ArrayList<>();
            ArrayList<Integer> foundWildCardIndex = new ArrayList<>();
            for (int i = 0; i < playerCards.size(); ++i){
                if (playerCards.get(i).getColor() == chosenCardsColor){
                    cardColorNum++;
                    foundColorCardsIndex.add(i);
                }
                else if (playerCards.get(i).getColor() == TrainCardColor.WILD) {
                    wildCardNum++;
                    foundWildCardIndex.add(i);
                }
            }
            if ((cardColorNum + wildCardNum >= length) && claimingPlayer.getTrainsRemaining() >= length) {
                setClaimed(true);
                setOwner(claimingPlayer.getPlayerName());
                setOwnerColor(claimingPlayer.getColor());
                addPoints(claimingPlayer);
                claimingPlayer.minusTrains(length);
                discardCards(claimingPlayer, foundWildCardIndex, foundColorCardsIndex, discard, cardColorNum, wildCardNum);
                claimingPlayer.addRoute(this);
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
                        if (this.isClaimed() == route.isClaimed()) {
                            if (this.owner.equals(route.getOwner()))
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void discardCards(Player claimingPlayer, ArrayList<Integer> wildCardsFound, ArrayList<Integer> foundColorCardsIndex, TrainCardDiscard discard, int numColorCards, int numWildCards) {
        ArrayList<TrainCard> cardsAfterDiscard = claimingPlayer.getTrainCards();
        if (numColorCards >= length) {
            for (int i = length - 1; i >= 0; --i) {
                discard.add(cardsAfterDiscard.get(foundColorCardsIndex.get(i)));
                cardsAfterDiscard.remove(foundColorCardsIndex.get(i).intValue());
            }
            claimingPlayer.setTrainCards(cardsAfterDiscard);
        }
        else {
            int tempLength = length;
            for (int i = numColorCards - 1; i >= 0; --i) {
                discard.add(cardsAfterDiscard.get(foundColorCardsIndex.get(i)));
                cardsAfterDiscard.remove(foundColorCardsIndex.get(i).intValue());
                --tempLength;
            }
            while (tempLength != 0) {
                for (int i = cardsAfterDiscard.size() - 1; i >= 0; --i) {
                    if (cardsAfterDiscard.get(i).getColor() == TrainCardColor.WILD) {
                        discard.add(cardsAfterDiscard.get(i));
                        cardsAfterDiscard.remove(i);
                        --tempLength;
                        break;
                    }
                }
            }
            claimingPlayer.setTrainCards(cardsAfterDiscard);
        }
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public PlayerColor getOwnerColor() {return ownerColor;}

    public void setOwnerColor(PlayerColor ownerColor) {this.ownerColor = ownerColor;}

    @Override
    public String toString(){
        String city1 = cityNodes.getCity1().getCityName();
        String city2 = cityNodes.getCity2().getCityName();
        String toString = city1 + " -- " + city2 + " | Length:" + length + " | Color:" + color;
        return toString;
    }
}
