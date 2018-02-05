package model;

import java.util.ArrayList;

import model.Game;

public class User {
    private String username;
    private String password;
    private ArrayList<Game> gameArray;

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

    public ArrayList<Game> getGameArray() {return gameArray;}
    public void setGameArray(ArrayList<Game> gameArray) {this.gameArray = gameArray;}

    public void addGame(Game newGame) {gameArray.add(newGame);}
    public void removeGame(Game game) {
        String gameToRemove = game.getGameName();
        for (int i = 0; i < gameArray.size(); ++i) {
            String currentGame = gameArray.get(i).getGameName();
            if (currentGame.equals(gameToRemove)) {
                gameArray.remove(i);
                return;
            }
        }
    }
}
