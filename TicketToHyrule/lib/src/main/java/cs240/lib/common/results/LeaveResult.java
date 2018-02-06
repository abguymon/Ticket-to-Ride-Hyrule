package cs240.lib.common.results;

/**
 * Created by David on 1/31/2018.
 */

public class LeaveResult {
    private String gameName;
    private int newPlayerNumber;
    private String errorMessage;

    public LeaveResult() {}

    public LeaveResult(String gameName, int newPlayerNumber, String errorMessage) {
        this.gameName = gameName;
        this.newPlayerNumber = newPlayerNumber;
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
