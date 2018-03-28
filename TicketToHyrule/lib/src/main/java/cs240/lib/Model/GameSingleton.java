package cs240.lib.Model;

/**
 * Created by David on 3/28/2018.
 */

public class GameSingleton {
    public static GameSingleton instance;
    private Game game = null;

    private GameSingleton(){}
    public static GameSingleton getInstance(){
        if(instance == null){
            instance = new GameSingleton();
        }
        return instance;
    }

    public Game getGame(){
        return game;
    }
    public void setGame(Game game){
        this.game = game;
    }
}
