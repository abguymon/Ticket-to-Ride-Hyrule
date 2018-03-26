package cs240.lib.Model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.gameParts.Player;
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
    }

    public void claimRoute(Player player, int route){
        //gameData.getMap().getRoutes().get(route).claim(player, gameData.getTrainCardDiscard());
        gameData.getPlayer(player.getPlayerName()).getTrainCards().remove(0);
        gameData.getPlayer(player.getPlayerName()).dropDestinationCard(gameData.getPlayer(player.getPlayerName()).getDestinationCards().get(0));
        gameData.getPlayer(player.getPlayerName()).setTrainsRemaining(gameData.getPlayer(player.getPlayerName()).getTrainsRemaining() - 7);
        setChanged();
        notifyObservers();
    }
    public void drawFaceUpTrainCard(String playerName, String gameName, int card){
        gameData.getPlayer(playerName).addTrainCard(gameData.getFaceUpTrainCards().pick(card, gameData.getTrainCardDeck(), gameData.getTrainCardDiscard()));
        setChanged();
        notifyObservers();
    }
    public void drawTrainCard(String playerName, String gameName){
        gameData.getPlayer(playerName).addTrainCard(gameData.getTrainCardDeck().draw(gameData.getTrainCardDiscard()));
        setChanged();
        notifyObservers();
    }

    public void endTurn(String playerName, String gameName){
        int playerTurnEnded = gameData.getPlayer(playerName).getPlayerNum();
        int newPlayerTurn;
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
//        Gson gson = new Gson();
//        DestinationCard dCardOne = gson.fromJson(cardOne, DestinationCard.class);
//        DestinationCard dCardTwo = gson.fromJson(cardTwo, DestinationCard.class);
//        if(!cardOne.equals("null")) {
////            gameData.getPlayer(playerName).dropDestinationCard(dCardOne);
//            gameData.putbackDestinationCard(dCardOne);
//            for(int i = 0; i < cardsDrawn.size(); i++){
//                if(cardsDrawn.get(i).equals(dCardOne)){
//                    cardsDrawn.remove(i);
//                    break;
//                }
//            }
//            if(!cardTwo.equals("null")) {
////                gameData.getPlayer(playerName).dropDestinationCard(dCardTwo);
//                gameData.putbackDestinationCard(dCardTwo);
//                for (int i = 0; i < cardsDrawn.size(); i++) {
//                    if (cardsDrawn.get(i).equals(dCardTwo)) {
//                        cardsDrawn.remove(i);
//                        break;
//                    }
//                }
//            }
//            for(int i = 0; i < cardsDrawn.size(); i++) {
//                gameData.getPlayer(playerName).addDestinationCard(cardsDrawn.get(i));
//            }
//        }
//        setChanged();
//        notifyObservers();
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
            case("endTurn"):
                endTurn((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
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
