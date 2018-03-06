package cs240.lib.Model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import cs240.lib.Model.cards.DestinationCard;
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
            case "sendMessage":
                sendMessage((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
                break;
            case "submitDestinationCards":
                submitDestinationCards((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                        (String) myCommand.getParametersAsJsonStrings()[2]);
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
}
