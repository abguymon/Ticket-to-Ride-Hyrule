package cs240.lib.common.results;

import cs240.lib.Model.Game;

/**
 * Created by savannah.jane on 3/21/2018.
 */

public class EndGameResult {
    private Game endedGame;
    private String errorMessage;

    public EndGameResult(String errorMessage) {this.errorMessage = errorMessage;}
    public EndGameResult(Game endedGame) {this.endedGame = endedGame;}

    public Game getEndedGame() {
        return endedGame;
    }

    public void setEndedGame(Game endedGame) {
        this.endedGame = endedGame;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
