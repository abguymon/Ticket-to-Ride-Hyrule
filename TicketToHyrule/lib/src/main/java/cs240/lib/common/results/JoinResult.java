package cs240.lib.common.results;

/**
 * Created by David on 1/31/2018.
 */

public class JoinResult {
    private String gameName;
    private int newPlayerNumber;
    private String errorMessage;

    public JoinResult() {}

    public JoinResult(String gameName, int newPlayerNumber) {
        this.gameName = gameName;
        this.newPlayerNumber = newPlayerNumber;
    }
    public JoinResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getNewPlayerNumber() {
        return newPlayerNumber;
    }

    public void setNewPlayerNumber(int newPlayerNumber) {
        this.newPlayerNumber = newPlayerNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
