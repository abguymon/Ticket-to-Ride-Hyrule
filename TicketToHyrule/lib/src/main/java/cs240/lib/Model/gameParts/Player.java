package cs240.lib.Model.gameParts;

import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.colors.PlayerColor;
import cs240.lib.Model.states.IState;
import cs240.lib.Model.states.OtherPlayersTurn;

/**
 * Created by David on 2/21/2018.
 */

public class Player {
    private String playerName;
    private PlayerColor color;
    private int playerNum;
    private int score;
    private int trainsRemaining;
    private ArrayList<DestinationCard> destinationCards;
    private ArrayList<TrainCard> trainCards;
    private IState state;

    private final int MAX_TRAIN_CARS = 45;

    public Player(PlayerColor color, String playerName) {
        playerNum = 0;
        this.color = color;
        this.score = 0;
        this.trainsRemaining = MAX_TRAIN_CARS;
        this.destinationCards = new ArrayList<>();
        this.trainCards = new ArrayList<>();
        this.playerName = playerName;
        this.state = new OtherPlayersTurn();

    }
    public void addTrainCard(TrainCard card) {trainCards.add(card);}
    public void addDestinationCard(DestinationCard card) {destinationCards.add(card);}
    public int getPlayerNum(){return playerNum;}
    public void setPlayerNum(int newNum) {playerNum = newNum;}

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

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public void dropDestinationCard(DestinationCard card) {
        for (int i = 0; i < destinationCards.size(); ++i) {
            DestinationCard curCard = destinationCards.get(i);
            if (curCard.equals(card)) {
                destinationCards.remove(i);
            }
        }
    }
}
