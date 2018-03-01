package cs240.lib.Model;

import java.util.ArrayList;

/**
 * Created by savannah.jane on 3/1/2018.
 */

public class LobbyGame {
    private int maxPlayers;
    private int playersJoined;
    private String gameName;
    private ArrayList<String> playerArray;

    public LobbyGame() {
        maxPlayers = 0;
        playersJoined = 0;
        gameName = "";
        playerArray = new ArrayList<>();
    }

    public LobbyGame(int maxPlayers, int playersJoined, String gameName) {
        this.maxPlayers = maxPlayers;
        this.playersJoined = playersJoined;
        this.gameName = gameName;
        playerArray = new ArrayList<>();
    }

    public int getMaxPlayers() {return maxPlayers;}
    public void setMaxPlayers(int maxPlayers) {this.maxPlayers = maxPlayers;}

    public int getPlayersJoined() {return playersJoined;}
    public void setPlayersJoined(int playersJoined) {this.playersJoined = playersJoined;}

    public String getGameName() {return gameName;}
    public void setGameName(String gameName) {this.gameName = gameName;}

    public ArrayList<String> getPlayerArray() {return playerArray;}
    public void setPlayerArray(ArrayList<String> playerArray) {this.playerArray = playerArray;}

    public void addPlayer(String newPlayer) throws Exception{
        if (playersJoined >= maxPlayers) {
            throw new Exception("Game: " + gameName + " is already full");
        }
        else {

            //System.out.println("player added: " + newPlayer + " to game " + gameName);
            playerArray.add(newPlayer);
            ++playersJoined;
        }
    }
    public void removePlayer(String player) {
        for (int i = 0; i < playerArray.size(); ++i) {
            String currentPlayer = playerArray.get(i);
            if (currentPlayer.equals(player)) {
                playerArray.remove(i);
                --playersJoined;
                return;
            }
        }
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gameName + "joined/max: ");
        stringBuilder.append(playersJoined + "playerArray: ");
        stringBuilder.append(playerArray);
        return stringBuilder.toString();
    }
}
