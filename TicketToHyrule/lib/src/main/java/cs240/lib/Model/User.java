package cs240.lib.Model;

import java.util.ArrayList;


public class User {
    private String username;
    private String password;
    private ArrayList<String> gameArray;

    public User() {
        username = "";
        password = "";
        gameArray = new ArrayList<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        gameArray = new ArrayList<>();
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public ArrayList<String> getGameArray() {return gameArray;}
    public void setGameArray(ArrayList<String> gameArray) {this.gameArray = gameArray;}

    public void addGame(String newGame) {gameArray.add(newGame);}
    public void removeGame(String game) {
        for (int i = 0; i < gameArray.size(); ++i) {
            String currentGame = gameArray.get(i);
            if (currentGame.equals(game)) {
                gameArray.remove(i);
                return;
            }
        }
    }
}
