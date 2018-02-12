package cs240.lib.Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import cs240.lib.client.Poller;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.Command;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.PollerResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by adam on 2/7/18.
 */

public class ModelFacade extends Observable{
    private ArrayList<Game> gameList = new ArrayList<>();
    private Login currentUser = null;
    public ModelFacade(){}



    public String createGame(String userName, String gameName, int maxPlayers){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        CreateResult result = ServerProxy.SINGLETON.createGame(userName, gameName, maxPlayers);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            //Game g = new Game(maxPlayers, 0, gameName);
            try{
               // g.addPlayer(userName);
                //gameList.add(g);
                //ClientFacade.getInstance().createGame(userName,gameName,maxPlayers);
                return "";
            }catch(Exception ex){
                return("EXCEPTION " + ex);
            }
        }
    }

    public String leaveGame(String userName, String gameName){
        LeaveResult result = ServerProxy.SINGLETON.leaveGame(userName, gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            //Game g = getGame(gameName);
           // g.removePlayer(userName);
            //if (g.getPlayersJoined() == 0) gameList.remove(g);
            //ClientFacade.getInstance().leaveGame(userName,gameName);
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
        JoinResult result = ServerProxy.SINGLETON.joinGame(userName,gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
//                getGame(gameName).addPlayer(userName);
                /*int playersJoined = getGame(gameName).getPlayersJoined();
                int maxPlayers = getGame(gameName).getMaxPlayers();
                if (playersJoined == maxPlayers){
                    String startGameResult = ServerProxy.SINGLETON.startGame(gameName);
                }*/ //TODO: how does the observer pattern/poller work with start game code? -David
                return "";
            }catch(Exception ex){
                return "EXCEPTION! " + ex;
            }
        }
    }

    public Game getGame(String gameName){
        for(Game g : gameList){
            if(g.getGameName().equals(gameName)) return g;
        }
        return null;
    }
    public ArrayList<Game> getGames(){
        return gameList;
    }

    public Login getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Login currentUser) {
        this.currentUser = currentUser;
    }

    public void setGames(ArrayList<Game> games){
        this.gameList = games;
    }
}
