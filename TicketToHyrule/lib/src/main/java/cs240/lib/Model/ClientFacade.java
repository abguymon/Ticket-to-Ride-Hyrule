package cs240.lib.Model;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.Model.states.DrawnFirstCard;
import cs240.lib.Model.states.IState;
import cs240.lib.Model.states.TurnEnded;
import cs240.lib.Model.states.TurnStarted;
import cs240.lib.client.Poller;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.Command;
import cs240.lib.common.results.PollerResult;
import sun.security.krb5.internal.crypto.Des;

/**
 * Created by adam on 2/12/18.
 */

public class ClientFacade extends Observable{
    private ArrayList<LobbyGame> lobbyGameList = new ArrayList<>();
    private Queue<LobbyGame> startedLobbyGames = new LinkedList<>();
    private Game gameData = null;
    private ArrayList<DestinationCard> cardsDrawn = new ArrayList<>();
    private static ClientFacade instance = null;

    private ClientFacade(){}


    public static ClientFacade getInstance(){
        if(instance == null){
            instance = new ClientFacade();
            Thread thread = new Thread(Poller.getInstance());
            thread.start();
        }
        return instance;
    }

    public void createGame(String userName, String gameName, int maxPlayers){
        LobbyGame g = new LobbyGame(maxPlayers, 0, gameName);
        lobbyGameList.add(g);
        joinGame(userName, gameName);
    }

    public boolean joinGame(String userName, String gameName){
        LobbyGame g = getGame(gameName);
        try {
            g.addPlayer(userName);
            if(g.getPlayersJoined() == g.getMaxPlayers()) {
                lobbyGameList.remove(g);
                startedLobbyGames.add(g);
            }
            setChanged();
            notifyObservers(lobbyGameList);
            return true;
        }catch(Exception ex){
            System.out.println("EXCEPTION "+ex);
            return false;
        }
    }

    public boolean startGame(String gameName){
        try {
            //MAKE SURE WE ARE SETTING THE CLIENTFACADE GAMEDATA SOMEWHERE (I BELIEVE WE ARE)
            return true;
        }catch(Exception ex){
            System.out.println("EXCEPTION "+ex);
            return false;
        }
    }

    public void leaveGame(String userName, String gameName){
        LobbyGame g = getGame(gameName);
        g.removePlayer(userName);
        if(g.getPlayersJoined() == 0) lobbyGameList.remove(g);
        setChanged();
        notifyObservers(lobbyGameList);
    }
    public void sendMessage(String playerName, String message){
        //ADD FUNCTION
        ChatEntry chatEntry = new ChatEntry(playerName, message);
        gameData.getChatHistory().add(chatEntry);
        setChanged();
        notifyObservers();
    }
    public void submitDestinationCards(String playerName, String card){
        Gson gson = new Gson();
        DestinationCard dcard = gson.fromJson(card, DestinationCard.class);
        if(card != null) {
            gameData.getPlayer(playerName).dropDestinationCard(dcard);
            gameData.putbackDestinationCard(dcard);
        }
        endTurn(playerName, gameData.getGameName());
        setChanged();
        notifyObservers();
    }
    public void addGameHistory(String gameName){

    }
    public void addToGameHistory(String newHistory){
        gameData.getGameHistory().add(newHistory);
        setChanged();
        notifyObservers();
    }
    public void drawDestinationCard(String player, String gameName){
//        gameData.getPlayer(player).addDestinationCard(gameData.drawDestinationCard());
//        gameData.getPlayer(player).addDestinationCard(gameData.drawDestinationCard());
//        gameData.getPlayer(player).addDestinationCard(gameData.drawDestinationCard());
//        cardsDrawn.add(gameData.drawDestinationCard());
//        cardsDrawn.add(gameData.drawDestinationCard());
//        cardsDrawn.add(gameData.drawDestinationCard());
//        setChanged();
//        notifyObservers();
        gameData.getPlayer(player).setState(new DrawnFirstCard());
    }

    public void claimRoute(String player, int route_id, String color){
        Gson gson = new Gson();
        TrainCardColor routeColor = gson.fromJson(color, TrainCardColor.class);
        gameData.getMap().getRoutes().get(route_id).claim(gameData.getPlayer(player), gameData.getTrainCardDiscard(), gameData.getMap().getRoutes().get(route_id).getColor());
        //gameData.getPlayer(player.getPlayerName()).getTrainCards().remove(0);
        //gameData.getPlayer(player.getPlayerName()).dropDestinationCard(gameData.getPlayer(player.getPlayerName()).getDestinationCards().get(0));
        //gameData.getPlayer(player.getPlayerName()).setTrainsRemaining(gameData.getPlayer(player.getPlayerName()).getTrainsRemaining() - 7);
        int i = 0;
        while (i < 1000000){
            i++;
        }
        endTurn(player, gameData.getGameName());
        setChanged();
        notifyObservers();
    }
    public void drawFaceUpTrainCard(String playerName, String gameName, int card){
        gameData.getPlayer(playerName).addTrainCard(gameData.getFaceUpTrainCards().pick(card, gameData.getTrainCardDeck(), gameData.getTrainCardDiscard()));
        if(gameData.getPlayer(playerName).getState() instanceof TurnStarted) gameData.getPlayer(playerName).setState(new DrawnFirstCard());
        else endTurn(playerName, gameName);
        setChanged();
        notifyObservers();
    }
    public void drawTrainCard(String playerName, String gameName){
        TrainCard card = gameData.getTrainCardDeck().draw(gameData.getTrainCardDiscard());
        if (card != null) {
            gameData.getPlayer(playerName).addTrainCard(card);
        }
        if(gameData.getPlayer(playerName).getState() instanceof TurnStarted) gameData.getPlayer(playerName).setState(new DrawnFirstCard());
        else endTurn(playerName, gameName);
        setChanged();
        notifyObservers();
    }

    public void endTurn(String playerName, String gameName){
        int playerTurnEnded = gameData.getPlayer(playerName).getPlayerNum();
        int newPlayerTurn;
        gameData.getPlayer(playerName).setState(new TurnEnded());
        if (playerTurnEnded == gameData.getPlayerArray().size()){
            newPlayerTurn = 1;
        }else{
            newPlayerTurn = playerTurnEnded + 1;
        }
        gameData.getPlayerArray().get(newPlayerTurn-1).setState(new TurnStarted());
        //TODO: toast player name of turn started
        setChanged();
        notifyObservers();
    }

    public void discardDestinationCards(String playerName, String cardOne, String cardTwo){
        endTurn(playerName,gameData.getGameName());
    }
    public void sync(){
        ArrayList<Player> playerArray = gameData.getPlayerArray();
        IState oldState = new TurnEnded();
        int currentPlayer = 0;
        for(int i = 0; i < playerArray.size(); i++){
            if(playerArray.get(i).getState() instanceof TurnEnded){}
            else if(playerArray.get(i).getState() instanceof DrawnFirstCard){oldState = new DrawnFirstCard(); currentPlayer = i;;}
            else {oldState = new TurnStarted(); currentPlayer = i;}
        }

        setGameData(ServerProxy.SINGLETON.getGameData(gameData.getGameName()).getGameStarted());

        playerArray = gameData.getPlayerArray();
        for(int i = 0; i <playerArray.size(); i++){
            if(i == currentPlayer) playerArray.get(i).setState(oldState);
            else playerArray.get(i).setState(new TurnEnded());
        }

        setChanged();
        notifyObservers();
    }


    public void handleObject( Command myCommand){
        switch(myCommand.getMethodName()){
            case "login": return;
            case "register": return;
            case "startGame":
                startGame((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1));
                break;
            case "joinGame":
                joinGame((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
                break;
            case "leaveGame":
                leaveGame((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
                break;
            case "createGame":
                createGame((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1),
                        Integer.parseInt(myCommand.getParametersAsJsonStrings()[2]));
                break;
            case "chat":
                sendMessage((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
                break;
            case "submitDestinationCards":
                submitDestinationCards((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String) myCommand.getParametersAsJsonStrings()[2]);
                break;
            case "discardDestinationCards":
                discardDestinationCards((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String) myCommand.getParametersAsJsonStrings()[2], (String) myCommand.getParametersAsJsonStrings()[3]);
                break;
            case "getGameHistory":
                addGameHistory((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1));
                break;
            case ("drawFaceUpTrainCard"):
                drawFaceUpTrainCard((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1),
                        Integer.parseInt(myCommand.getParametersAsJsonStrings()[2]));
                break;
            case("drawTrainCard"):
                drawTrainCard((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
                break;
            case("drawDestinationCard"):
                drawDestinationCard((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
                break;
            case("claimRoute"):
                claimRoute((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        Integer.parseInt(myCommand.getParametersAsJsonStrings()[2]),
                        (String)myCommand.getParametersAsJsonStrings()[3].substring(1,myCommand.getParametersAsJsonStrings()[3].length()-1));
                        //Integer.parseInt(myCommand.getParametersAsJsonStrings()[4]));
            case("endTurn"):
                endTurn((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
                break;
            case("sync"):
                sync();
                break;
        }
    }

    public void updateModel(PollerResult result){

        while(!result.getCommands().isEmpty())
        {
            handleObject(result.getCommands().remove());
        }
    }

    public String pollerCheckServer(int index){
        PollerResult result = ServerProxy.SINGLETON.pollerCheckServer(index);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                Poller.getInstance().setCommandIndex(result.getCommands().size() + Poller.getInstance().getCommandIndex());
                updateModel(result);
                return "";
            }catch(Exception ex){
                return "EXCEPTION! " + ex;
            }
        }
    }

    public LobbyGame getGame(String gameName){
        for(LobbyGame g : lobbyGameList){
            if(g.getGameName().equals(gameName)) return g;
        }
        return null;
    }

    public ArrayList<LobbyGame> getGames(){
        return lobbyGameList;
    }

    public Queue<LobbyGame> getStartedLobbyGames() {
        return startedLobbyGames;
    }

    public void setStartedLobbyGames(Queue<LobbyGame> startedLobbyGames) {
        this.startedLobbyGames = startedLobbyGames;
    }

    public Game getGameData() {
        return gameData;
    }

    public void setGameData(Game gameData) {
        this.gameData = gameData;
    }

    public ArrayList<DestinationCard> getCardsDrawn(){return cardsDrawn;}


}
