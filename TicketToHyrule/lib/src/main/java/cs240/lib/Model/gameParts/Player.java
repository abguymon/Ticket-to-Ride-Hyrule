package cs240.lib.Model.gameParts;

import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.colors.PlayerColor;

/**
 * Created by David on 2/21/2018.
 */

public class Player {
    private String playerName;
    private PlayerColor color;
    private int score;
    private int trainsRemaining;
    private ArrayList<DestinationCard> destinationCards;
    private ArrayList<TrainCard> trainCards;

    private final int MAX_TRAIN_CARS = 45;

    public Player(PlayerColor color, String playerName) {
        this.color = color;
        this.score = 0;
        this.trainsRemaining = MAX_TRAIN_CARS;
        this.destinationCards = new ArrayList<>();
        this.trainCards = new ArrayList<>();
        this.playerName = playerName;

    }

    public String getPlayerName() {return playerName;}

    public void setPlayerName(String playerName) {this.playerName = playerName;}

    public PlayerColor getColor(){
        return color;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int toAdd){
        score += toAdd;
    }

    public int getTrainsRemaining() {
        return trainsRemaining;
    }

    public void setTrainsRemaining(int trainsRemaining) {
        this.trainsRemaining = trainsRemaining;
    }

    public void minusTrains(int toMinus){
        trainsRemaining -= toMinus;
    }

    public ArrayList<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public void setDestinationCards(ArrayList<DestinationCard> destinationCards) {
        this.destinationCards = destinationCards;
    }

    public ArrayList<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(ArrayList<TrainCard> trainCards) {
        this.trainCards = trainCards;
    }
}
