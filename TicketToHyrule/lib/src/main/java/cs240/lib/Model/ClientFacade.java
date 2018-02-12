package cs240.lib.Model;

import java.util.ArrayList;

/**
 * Created by adam on 2/12/18.
 */

public class ClientFacade {
    private ArrayList<Game> gameList = new ArrayList<>();
    private static ClientFacade instance = null;

    private ClientFacade(){}

    public static ClientFacade getInstance(){
        if(instance == null) instance = new ClientFacade();
        return instance;
    }

    public String createGame(String userName, String gameName, int maxPlayers){
        Game g = new Game(maxPlayers, 0, gameName);
        gameList.add(g);
        joinGame();
    }
    public String joinGame(String userName, String gameName){
        Game g = getGame(gameName);
        g.addPlayer(userName);
    }

    public Game getGame(String gameName){
        for(Game g : gameList){
            if(g.getGameName().equals(gameName)) return g;
        }
        return null;
    }
}
