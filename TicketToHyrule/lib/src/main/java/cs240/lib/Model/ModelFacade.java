package cs240.lib.Model;

import java.util.ArrayList;

import cs240.lib.client.ServerProxy;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.SignInResult;

/**
 * Created by adam on 2/7/18.
 */

public class ModelFacade {
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

    public String login(String userName, String password){
        SignInResult signInResult = new SignInResult();
        currentUser = new User(userName, signInResult.getAuthToken());
        return null;
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
}
