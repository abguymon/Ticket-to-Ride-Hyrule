package cs240.lib.Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by adam on 2/12/18.
 */

public class ClientFacade extends Observable{
    private ArrayList<Game> gameList = new ArrayList<>();
    private static ClientFacade instance = null;

    private ClientFacade(){}

    public static ClientFacade getInstance(){
        if(instance == null) instance = new ClientFacade();
        return instance;
    }

    public void createGame(String userName, String gameName, int maxPlayers){
        Game g = new Game(maxPlayers, 0, gameName);
        gameList.add(g);
        joinGame(userName, gameName);
    }

    public boolean joinGame(String userName, String gameName){
        Game g = getGame(gameName);
        try {
            g.addPlayer(userName);
            setChanged();
            notifyObservers();
            return true;
        }catch(Exception ex){
            System.out.println("EXCEPTION "+ex);
            return false;
        }
    }
    public void leaveGame(String userName, String gameName){
        Game g = getGame(gameName);
        g.removePlayer(userName);
        setChanged();
        notifyObservers();
    }

    public Game getGame(String gameName){
        for(Game g : gameList){
            if(g.getGameName().equals(gameName)) return g;
        }
        return null;
    }
}
