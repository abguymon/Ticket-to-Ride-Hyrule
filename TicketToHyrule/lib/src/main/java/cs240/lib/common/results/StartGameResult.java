package cs240.lib.common.results;

import cs240.lib.Model.Game;

/**
 * Created by savannah.jane on 2/26/2018.
 */

public class StartGameResult {
    private Game gameStarted;
    private String errorMessage;

    public StartGameResult(String errorMessage){this.errorMessage = errorMessage;}
    public StartGameResult(Game newGame) {this.gameStarted = newGame;}

    public Game getGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(Game gameStarted) {
        this.gameStarted = gameStarted;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
