package cs340.tickettohyrule;

/**
 * Created by eholm on 2/9/2018.
 */

public class InGameSingleton {
    private boolean inGame;
    private String gameImIn;

    private static InGameSingleton instance;
    private InGameSingleton(){inGame = false;}
    public static InGameSingleton getInstance(){
        if(instance == null){
            instance = new InGameSingleton();
        }
        return instance;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public String getGameImIn() {
        return gameImIn;
    }

    public void setGameImIn(String gameImIn) {
        this.gameImIn = gameImIn;
    }
}
