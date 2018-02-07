package cs240.lib.Model;

import java.util.ArrayList;

import cs240.lib.client.ServerProxy;
import cs240.lib.common.results.JoinResult;

/**
 * Created by adam on 2/7/18.
 */

public class ModelFacade {
    private ArrayList<Game> gameList = new ArrayList<>();

    private static ModelFacade instance;
    private ModelFacade(){}
    public static ModelFacade getInstance(){
        if(instance == null){
            instance = new ModelFacade();
        }
        return instance;
    }

    public String joinGame(String userName, String gameName){
        JoinResult result = ServerProxy.SINGLETON.joinGame(userName,gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            getGame(gameName).addPlayer();
        }
    }
    public Game getGame(String gameName){
        for(Game g : gameList){
            if(g.getGameName().equals(gameName)) return g;
        }
        return null;
    }
}
