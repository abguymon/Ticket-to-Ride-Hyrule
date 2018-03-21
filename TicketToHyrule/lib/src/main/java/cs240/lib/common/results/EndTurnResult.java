package cs240.lib.common.results;

/**
 * Created by savannah.jane on 3/21/2018.
 */

public class EndTurnResult {
    private int playerTurn;
    private String errorMessage;

    public EndTurnResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public EndTurnResult(int newTurn) {
        playerTurn = newTurn;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
