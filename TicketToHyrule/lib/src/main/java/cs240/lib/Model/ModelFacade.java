package cs240.lib.Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import cs240.lib.client.ServerProxy;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by adam on 2/7/18.
 */

public class ModelFacade extends Observable{
    private ArrayList<Game> gameList = new ArrayList<>();
    private User currentUser = null;

    private static ModelFacade instance;
    private ModelFacade(){}
    public static ModelFacade getInstance(){
        if(instance == null){
            instance = new ModelFacade();
        }
        return instance;
    }



    public String createGame(String userName, String gameName, int maxPlayers){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getPassword());
        CreateResult result = ServerProxy.SINGLETON.createGame(userName, gameName, maxPlayers);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            Game g = new Game(maxPlayers, 0, gameName);
            try{
                g.addPlayer(currentUser);
                gameList.add(g);
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
            Game g = getGame(gameName);
            g.removePlayer(currentUser);
            return "";
        }
    }

    public String login(String userName, String password){
        SignInResult result = ServerProxy.SINGLETON.login(userName, password);
        if(!result.getErrorMessage().equals("")){
            return result.getErrorMessage();
        }
        else{
            currentUser = new User(userName, result.getAuthToken());
            return "";
        }
    }
    public String register(String userName, String password){
        SignInResult result = ServerProxy.SINGLETON.register(userName, password);
        if(!result.getErrorMessage().equals("")){
            return result.getErrorMessage();
        }
        else{
            currentUser = new User(userName, result.getAuthToken());
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
                getGame(gameName).addPlayer(currentUser);
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
