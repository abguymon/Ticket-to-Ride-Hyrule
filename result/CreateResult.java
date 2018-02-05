package result;

public class CreateResult {
    private String gameName;
    private int totalPlayers;
    private String errorMessage;

    public CreateResult(String gameName, int totalPlayers) {
        this.gameName = gameName;
        this.totalPlayers = totalPlayers;
        errorMessage = "";
    }

    public CreateResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getGameName() {return gameName;}
    public void setGameName(String gameName) {this.gameName = gameName;}

    public String getErrorMessage() {return errorMessage;}
    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}

    public int getTotalPlayers() {return totalPlayers;}
    public void setTotalPlayers(int totalPlayers) {this.totalPlayers = totalPlayers;}
}
