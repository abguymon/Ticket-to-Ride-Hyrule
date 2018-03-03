package cs240.lib.Model.cards;

import cs240.lib.Model.gameParts.City;

/**
 * Created by David on 2/21/2018.
 */

public class DestinationCard {
    private final int value;
    private final City startCity;
    private final City endCity;

    public DestinationCard(int value, City startCity, City endCity) {
        this.value = value;
        this.startCity = startCity;
        this.endCity = endCity;
    }

    public int getValue() {
        return value;
    }

    public City getStartCity() {
        return startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public boolean equals(DestinationCard card1) {
        if (card1.getStartCity().getCityName().equals(this.getStartCity().getCityName())) {
            if (card1.getEndCity().getCityName().equals(this.getEndCity().getCityName())) {
                if (card1.getValue() == this.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        return startCity + "\n" + endCity + "\n" + value;
    }
}