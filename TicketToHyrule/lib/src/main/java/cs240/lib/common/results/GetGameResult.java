package cs240.lib.common.results;

import cs240.lib.Model.Game;

/**
 * Created by savannah.jane on 2/26/2018.
 */

public class GetGameResult {
    private Game game;
    private String errorMessage;

    public GetGameResult(String errorMessage){this.errorMessage = errorMessage;}
    public GetGameResult(Game newGame) {this.game = newGame;}

    public Game getGameStarted() {
        return game;
    }

    public void setGameStarted(Game gameStarted) {
        this.game = gameStarted;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
