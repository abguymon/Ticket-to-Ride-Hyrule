package cs240.lib.common.results;

/**
 * Created by savannah.jane on 2/26/2018.
 */

public class ChatResult {
    private String playerName;
    private String chatMessage;
    private String errorMessage;

    public ChatResult(String playerName, String chatMessage) {
        this.playerName = playerName;
        this.chatMessage = chatMessage;
    }

    public ChatResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
