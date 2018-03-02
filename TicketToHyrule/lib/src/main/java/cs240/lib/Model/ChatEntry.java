package cs240.lib.Model;

/**
 * Created by savannah.jane on 3/2/2018.
 */

public class ChatEntry {
    private String playerName;
    private String message;

    public ChatEntry(String playerName, String message) {
        this.playerName = playerName;
        this.message = message;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
