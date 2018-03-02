package cs240.lib.common.results;

import java.util.ArrayList;

import cs240.lib.common.Command;

/**
 * Created by savannah.jane on 2/26/2018.
 */

public class GameHistoryResult {
    private ArrayList<String> gameHistory;
    private String errorMessage;

    public GameHistoryResult(ArrayList<String> gameHistory) {
        this.gameHistory = gameHistory;
    }

    public GameHistoryResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<String> getGameHistory() {
        return gameHistory;
    }

    public void setGameHistory(ArrayList<String> gameHistory) {
        this.gameHistory = gameHistory;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
