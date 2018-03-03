package cs240.lib.Model;


import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.results.ChatResult;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.GetGameResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.common.results.StartGameResult;
import cs240.lib.common.results.SubmitResult;
import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by adam on 2/7/18.
 */

public class ModelFacade {
    private ArrayList<LobbyGame> lobbyGameList = new ArrayList<>();
    private ArrayList<LobbyGame> startedLobbyGames = new ArrayList<>();
    private Game gameData = null;
    private Login currentUser = null;
    private Player currentPlayer = null;
    public ModelFacade(){}



    public Object getGameData(String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        GetGameResult result = ServerProxy.SINGLETON.getGameData(gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                gameData = result.getGameStarted();
                return result;
            }catch(Exception ex){
                return("EXCEPTION" + ex);
            }
        }
    }

    public String createGame(String userName, String gameName, int maxPlayers){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        CreateResult result = ServerProxy.SINGLETON.createGame(userName, gameName, maxPlayers);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                return "";
            }catch(Exception ex){
                return("EXCEPTION " + ex);
            }
        }
    }

    public String leaveGame(String userName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        LeaveResult result = ServerProxy.SINGLETON.leaveGame(userName, gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    public String login(String userName, String password){
        SignInResult result = ServerProxy.SINGLETON.login(userName, password);
        if(!result.getErrorMessage().equals("")){
            return result.getErrorMessage();
        }
        else{
            currentUser = new Login(userName, result.getAuthToken());
            return "";
        }
    }
    public String register(String userName, String password){
        SignInResult result = ServerProxy.SINGLETON.register(userName, password);
        if(!result.getErrorMessage().equals("")){
            return result.getErrorMessage();
        }
        else{
            currentUser = new Login(userName, result.getAuthToken());
            return "";
        }
    }

    public String joinGame(String userName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        boolean start = false;
        if(getGame(gameName).getPlayersJoined() == (getGame(gameName).getMaxPlayers() - 1)){
            start = true;
        }
        JoinResult result = ServerProxy.SINGLETON.joinGame(userName,gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                //TODO: how does the observer pattern/poller work with start game code? -David
                if(start) return startGame(gameName);
                else return "";
            }catch(Exception ex){
                return "EXCEPTION! " + ex;
            }
        }
    }
    public String startGame(String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        StartGameResult result = ServerProxy.SINGLETON.startGame(gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    public String sendMessage(String message, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        ChatResult result = ServerProxy.SINGLETON.chat(currentUser.getUsername(), message, gameName);
        //WILL THIS BE NULL OR EMPTY STRING???
        //It'll be null
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    public String submitDestinationCard(String gameName, DestinationCard card){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        SubmitResult result = ServerProxy.SINGLETON.submitDestinationCards(currentUser.getUsername(), gameName, card);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
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

    public Login getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Login currentUser) {
        this.currentUser = currentUser;
    }

    public void setGames(ArrayList<LobbyGame> lobbyGames){
        this.lobbyGameList = lobbyGames;
    }

    public ArrayList<LobbyGame> getStartedLobbyGames() {
        return startedLobbyGames;
    }

    public void setStartedLobbyGames(ArrayList<LobbyGame> startedLobbyGames) {
        this.startedLobbyGames = startedLobbyGames;
    }

    public Game getGameData() {
        return gameData;
    }

    public void setGameData(Game gameData) {
        this.gameData = gameData;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
