package cs240.lib.Model.gameParts;

import cs240.lib.Model.colors.TrainCardColor;

/**
 * Created by David on 2/21/2018.
 */

public class Route {
    private CityPair cityNodes;
    private int length;
    private TrainCardColor color;
    private TrainCardColor secondaryColor; //secondary color only needed for select routes
    private boolean claimed; //claimed during gameplay
    private Player owner; //assigned at claimed

    public Route(CityPair cityNodes, int length, TrainCardColor color){ //other fields are added as game progresses
        this.cityNodes = cityNodes;
        this.length = length;
        this.color = color;
    }

    public void claim(Player claimingPlayer){
        setClaimed(true);
        setOwner(claimingPlayer);
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

    public TrainCardColor getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(TrainCardColor secondaryColor) {
        this.secondaryColor = secondaryColor;
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
        if (secondaryColor != null){
            toString = toString + " Secondary color:" + secondaryColor;
        }
        return toString;
    }
}
