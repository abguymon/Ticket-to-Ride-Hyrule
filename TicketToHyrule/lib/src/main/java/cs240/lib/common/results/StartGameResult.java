package cs240.lib.common.results;

import java.util.ArrayList;

import cs240.lib.Model.Game;

/**
 * Created by savannah.jane on 2/26/2018.
 */

public class StartGameResult {
    private ArrayList<String> players;
    private String errorMessage;

    public StartGameResult(ArrayList<String> players) {
        this.players = players;
    }

    public StartGameResult(String errorResult) {
        this.errorMessage = errorResult;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
