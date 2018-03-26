package cs240.lib.Model.gameParts;

import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.colors.PlayerColor;
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.states.IState;
import cs240.lib.Model.states.TurnEnded;
import cs240.lib.common.results.ClaimRouteResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;
import cs240.lib.common.results.SubmitResult;

/**
 * Created by David on 2/21/2018.
 */

public class Player {
    private String playerName;
    private PlayerColor color;
    private int playerNum;
    private int trainsRemaining;
    private ArrayList<DestinationCard> destinationCards;
    private ArrayList<DestinationCard> drawnDestinationCards = new ArrayList<>();
    private ArrayList<TrainCard> trainCards;
    private IState state;

    private int totalPoints;
    private int routePoints;
    private int positiveDestinationPoints;
    private int negativeDestinationPoints;
    private int bonusPoints;
    private ArrayList<Route> claimedRoutes;
    private int longestPath;

    private final int MAX_TRAIN_CARS = 45;

    public Player(PlayerColor color, String playerName) {
        playerNum = 0;
        this.color = color;
        this.totalPoints = 0;
        this.routePoints = 0;
        this.positiveDestinationPoints = 0;
        this.negativeDestinationPoints = 0;
        this.bonusPoints = 0;
        this.trainsRemaining = MAX_TRAIN_CARS;
        this.destinationCards = new ArrayList<>();
        this.claimedRoutes = new ArrayList<>();
        this.trainCards = new ArrayList<>();
        this.playerName = playerName;
        longestPath = 0;
        //this.state = new TurnEnded();
    }

    public int getLongestPath() {return longestPath;}

    public void setLongestPath(int longestPath) {this.longestPath = longestPath;}

    public void addRoute(Route route) {claimedRoutes.add(route);}
    public ArrayList<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public void setClaimedRoutes(ArrayList<Route> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }

    public ClaimRouteResult claimRoute(String gameName, Route route, TrainCardColor chosenCardsColor){
        return state.claimRoute(this, gameName, route, chosenCardsColor);
    }
    public DrawTrainCardResult drawTrainCard(String gameName){
        return state.drawTrainCard(this, gameName);
    }
    public DrawDestinationCardResult drawDestinationCard(String gameName){
        return state.drawDestinationCard(this, gameName);
    }
    public SubmitResult submitDestinationCards(String gameName, ArrayList<DestinationCard> cards){
        return state.submitDestinationCard(this, gameName, cards);
    }
    public DrawFaceUpTrainCardResult drawFaceUpTrainCard(String gameName, int positionPicked){
        return state.drawFaceUpTrainCard(this, gameName, positionPicked);
    }
    public DrawFaceUpTrainCardResult drawLocomotive(String gameName, int positionPicked){
        return state.drawLocomotive(this, gameName, positionPicked);
    }

    public int getTotalPoints() {return totalPoints;}
    public void setTotalPoints(int totalPoints) {this.totalPoints = totalPoints;}
    public int getRoutePoints() {return routePoints;}
    public void setRoutePoints(int routePoints) {this.routePoints = routePoints;}
    public int getPositiveDestinationPoints() {return positiveDestinationPoints;}
    public void setPositiveDestinationPoints(int positiveDestinationPoints) {this.positiveDestinationPoints = positiveDestinationPoints;}
    public int getNegativeDestinationPoints() {return negativeDestinationPoints;}
    public void setNegativeDestinationPoints(int negativeDestinationPoints) {this.negativeDestinationPoints = negativeDestinationPoints;}
    public int getBonusPoints() {return bonusPoints;}
    public void setBonusPoints(int bonusPoints) {this.bonusPoints = bonusPoints;}
    public void addRoutePoints(int points) {routePoints += points;}
    public void addPositiveDestinationPoints(int points) {positiveDestinationPoints += points;}
    public void addNegativeDestinationPoints(int points) {negativeDestinationPoints += points;}
    public void addBonusPoints(int points) {bonusPoints += points;}
    public int calculateScore() {
        totalPoints = routePoints + positiveDestinationPoints + bonusPoints - negativeDestinationPoints;
        return totalPoints;
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
        return calculateScore();
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
        for (int i = 0; i < destinationCards.size(); i++) {
            DestinationCard curCard = destinationCards.get(i);
            if (curCard.equals(card)) {
                destinationCards.remove(i);
                break;
            }
        }
    }
    public ArrayList<DestinationCard> getDrawnDestinationCards(){
        return drawnDestinationCards;
    }
}
