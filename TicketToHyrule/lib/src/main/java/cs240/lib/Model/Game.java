package cs240.lib.Model;

import java.util.ArrayList;

import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.gameParts.GameMap;
import cs240.lib.Model.gameParts.Player;

public class Game {
    private String gameName;
    private ArrayList<Player> playerArray;
    private ArrayList<TrainCard> trainCardDeck;
    private GameMap map;
    private ArrayList<TrainCard> faceUpTrainCards;
    private ArrayList<String> gameHistory;
    private ArrayList<ChatEntry> chatHistory;

    public Game(String newName) {
        this.gameName = newName;
        playerArray = new ArrayList<>();
        trainCardDeck = new ArrayList<>();
        faceUpTrainCards = new ArrayList<>();
        gameHistory = new ArrayList<>();
        chatHistory = new ArrayList<>();
    }

    public String getGameName() {return gameName;}
    public void setGameName(String gameName) {this.gameName = gameName;}
    public ArrayList<Player> getPlayerArray() {return playerArray;}
    public void setPlayerArray(ArrayList<Player> playerArray) {this.playerArray = playerArray;}
    public ArrayList<TrainCard> getTrainCardDeck() {return trainCardDeck;}
    public void setTrainCardDeck(ArrayList<TrainCard> trainCardDeck) {this.trainCardDeck = trainCardDeck;}
    public GameMap getMap() {return map;}
    public void setMap(GameMap map) {this.map = map;}
    public ArrayList<TrainCard> getFaceUpTrainCards() {return faceUpTrainCards;}
    public void setFaceUpTrainCards(ArrayList<TrainCard> faceUpTrainCards) {this.faceUpTrainCards = faceUpTrainCards;}
    public ArrayList<String> getGameHistory() {return gameHistory;}
    public void setGameHistory(ArrayList<String> gameHistory) {this.gameHistory = gameHistory;}
    public ArrayList<ChatEntry> getChatHistory() {return chatHistory;}
    public void setChatHistory(ArrayList<ChatEntry> chatHistory) {this.chatHistory = chatHistory;}
}
