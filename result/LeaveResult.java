package result;

public class LeaveResult {
    private String gameName;
    private int newPlayerNum;
    private String errorMessage;

    public LeaveResult(String gameName, int newPlayerNum) {
        this.gameName = gameName;
        this.newPlayerNum = newPlayerNum;
        errorMessage = "";
    }

    public LeaveResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getGameName() {return gameName;}
    public void setGameName(String gameName) {this.gameName = gameName;}

    public int getNewPlayerNum() {return newPlayerNum;}
    public void setNewPlayerNum(int newPlayerNum) {this.newPlayerNum = newPlayerNum;}

    public String getErrorMessage() {return errorMessage;}
    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}
}
