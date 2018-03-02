package cs240.lib.Model;

import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.DestinationCardDeck;
import cs240.lib.Model.cards.FaceUpTrainCards;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.cards.TrainCardDeck;
import cs240.lib.Model.cards.TrainCardDiscard;
import cs240.lib.Model.gameParts.CitiesList;
import cs240.lib.Model.gameParts.City;
import cs240.lib.Model.gameParts.GameMap;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.Model.gameParts.RouteList;
import cs240.lib.common.Command;

public class Game {
    private String gameName;
    private ArrayList<Player> playerArray;
    private TrainCardDeck trainCardDeck;
    private TrainCardDiscard trainCardDiscard;
    private DestinationCardDeck destinationCardDeck;
    private GameMap map;
    private FaceUpTrainCards faceUpTrainCards;
    private ArrayList<String> gameHistory;
    private ArrayList<ChatEntry> chatHistory;

    public Game(String newName) {
        this.gameName = newName;
        playerArray = new ArrayList<>();
        trainCardDeck = new TrainCardDeck();
        trainCardDiscard = new TrainCardDiscard();
        destinationCardDeck = new DestinationCardDeck();
        faceUpTrainCards = new FaceUpTrainCards();
        gameHistory = new ArrayList<>();
        chatHistory = new ArrayList<>();
        trainCardDeck.shuffle();
        initializeGameMap();
        initializeFaceUpCards();
    }

    public TrainCardDiscard getTrainCardDiscard() {return trainCardDiscard;}
    public void setTrainCardDiscard(TrainCardDiscard trainCardDiscard) {this.trainCardDiscard = trainCardDiscard;}
    public DestinationCardDeck getDestinationCardDeck() {return destinationCardDeck;}
    public void setDestinationCardDeck(DestinationCardDeck destinationCardDeck) {this.destinationCardDeck = destinationCardDeck;}
    public String getGameName() {return gameName;}
    public void setGameName(String gameName) {this.gameName = gameName;}
    public ArrayList<Player> getPlayerArray() {return playerArray;}
    public void setPlayerArray(ArrayList<Player> playerArray) {this.playerArray = playerArray;}
    public TrainCardDeck getTrainCardDeck() {return trainCardDeck;}
    public void setTrainCardDeck(TrainCardDeck trainCardDeck) {this.trainCardDeck = trainCardDeck;}
    public GameMap getMap() {return map;}
    public void setMap(GameMap map) {this.map = map;}
    public FaceUpTrainCards getFaceUpTrainCards() {return faceUpTrainCards;}
    public void setFaceUpTrainCards(FaceUpTrainCards faceUpTrainCards) {this.faceUpTrainCards = faceUpTrainCards;}
    public ArrayList<String> getGameHistory() {return gameHistory;}
    public void setGameHistory(ArrayList<String> gameHistory) {this.gameHistory = gameHistory;}
    public ArrayList<ChatEntry> getChatHistory() {return chatHistory;}
    public void setChatHistory(ArrayList<ChatEntry> chatHistory) {this.chatHistory = chatHistory;}
    public void addToChatHistory(ChatEntry newEntry) {chatHistory.add(newEntry);}
    public void addToGameHistory(String newEntry) {gameHistory.add(newEntry);}

    public void initializeGameMap(){
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<Route> routes = new ArrayList<>();
        for (City city : CitiesList.SINGLETON.getCities()) {
            cities.add(city);
        }
        for (Route route : RouteList.SINGLETON.getRoutes()) {
            routes.add(route);
        }
        map = new GameMap(routes, cities);
    }
    public void initializeFaceUpCards(){
        TrainCard[] cards = new TrainCard[5];
        for (int i = 0; i < 5; ++i) {
             cards[i] = trainCardDeck.draw();
        }
        faceUpTrainCards.setFaceUpCards(cards);
    }
}
