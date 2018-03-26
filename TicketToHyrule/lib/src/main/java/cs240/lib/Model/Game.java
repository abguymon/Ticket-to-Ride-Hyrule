package cs240.lib.Model;

import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.DestinationCardDeck;
import cs240.lib.Model.cards.FaceUpTrainCards;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.cards.TrainCardDeck;
import cs240.lib.Model.cards.TrainCardDiscard;
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.CitiesList;
import cs240.lib.Model.gameParts.City;
import cs240.lib.Model.gameParts.GameMap;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.Model.gameParts.RouteList;
import cs240.lib.common.Command;
import cs240.lib.common.DestinationCardResolution;
import cs240.lib.common.LongestPathCalculator;

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
    private int playerTurn;
    private boolean isFinalRound;

    public Game(String newName) {
        this.gameName = newName;
        playerArray = new ArrayList<>();
        trainCardDeck = new TrainCardDeck();
        trainCardDiscard = new TrainCardDiscard();
        destinationCardDeck = new DestinationCardDeck();
        faceUpTrainCards = new FaceUpTrainCards(trainCardDeck, trainCardDiscard);
        gameHistory = new ArrayList<>();
        chatHistory = new ArrayList<>();
        destinationCardDeck.shuffle();
        initializeGameMap();
        playerTurn = 1;
        isFinalRound = false;
    }

    public boolean isFinalRound() {return isFinalRound;}
    public void setFinalRound(boolean T_F) {isFinalRound = T_F;}
    public int getPlayerTurn() {return playerTurn;}
    public void setPlayerTurn(int newTurn) {playerTurn = newTurn;}
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
    public void addPlayer(Player player) {playerArray.add(player);}
    public DestinationCard drawDestinationCard(){return destinationCardDeck.draw();}
    public void putbackDestinationCard(DestinationCard card) {destinationCardDeck.putback(card);}

    public void discardTrainCard(TrainCard card) {trainCardDiscard.add(card);}

    public TrainCard drawTrainCard() {
        TrainCard card = trainCardDeck.draw(trainCardDiscard);
        return card;
    }

    public int endTurn() {
        int numPlayers = playerArray.size();
        if (playerTurn == numPlayers) {
            playerTurn = 1;
        }
        else {
            ++playerTurn;
        }
        return playerTurn;
    }

    public TrainCard drawFaceUpTrainCard(int position) {
        return faceUpTrainCards.pick(position, trainCardDeck, trainCardDiscard);
    }

    public Route getRoute(Route route) {
        for (int i = 0; i < map.getRoutes().size(); ++i) {
            if (map.getRoutes().get(i).equals(route)) {
                return map.getRoutes().get(i);
            }
        }
        return null;
    }

    public boolean claimRoute(Player player, Route route, TrainCardColor chosenCardsColor) {
        Route gameRoute = getRoute(route);
        if (route != null) {
            if (gameRoute.claim(player, trainCardDiscard, chosenCardsColor)) {
                if (player.getTrainsRemaining() == 0) {
                    isFinalRound = true;
                }
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public void endGame() {
        calculateLongestPath();
        resolveDestinationCards();
        calculatePlayerScores();
    }

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

    public Player getPlayer(String name){
        for(int i = 0; i < playerArray.size(); i++){
            if(playerArray.get(i).getPlayerName().equals(name)) return playerArray.get(i);
        }
        return null;
    }

    private void calculateLongestPath() {
        ArrayList<String> playersWithLongestPath = new ArrayList<>();

        playersWithLongestPath = LongestPathCalculator.SINGLETON.calculate(this);
        for (int i = 0; i < playersWithLongestPath.size(); ++i) {
            Player player = getPlayer(playersWithLongestPath.get(i));
            player.addBonusPoints(10);
        }
    }

    private void resolveDestinationCards() {
        DestinationCardResolution.SINGLETON.resolveDestinationCards(this);
    }

    private void calculatePlayerScores() {
        for (int i = 0; i < playerArray.size(); ++i) {
            playerArray.get(i).calculateScore();
        }
    }
}
