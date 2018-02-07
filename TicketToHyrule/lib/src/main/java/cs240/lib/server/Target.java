package cs240.lib.server;

import java.util.ArrayList;
import java.util.UUID;

import cs240.lib.Model.Game;
import cs240.lib.Model.Login;
import cs240.lib.Model.User;
import cs240.lib.common.IServer;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.SignInResult;


//implements IServer
public class Target implements IServer {
    public static final Target SINGLETON = new Target();

    private ArrayList<User> registeredUsers;
    private ArrayList<Login> loggedinUsers;
    private ArrayList<Game> availableGames;
    private ArrayList<Game> activeGames;

    private Target() {
        registeredUsers = new ArrayList<>();
        loggedinUsers = new ArrayList<>();
        availableGames = new ArrayList<>();
        activeGames = new ArrayList<>();
    }

    public ArrayList<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(ArrayList<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public ArrayList<Login> getLoggedinUsers() {
        return loggedinUsers;
    }

    public void setLoggedinUsers(ArrayList<Login> loggedinUsers) {
        this.loggedinUsers = loggedinUsers;
    }

    public ArrayList<Game> getAvailableGames() {
        return availableGames;
    }

    public void setAvailableGames(ArrayList<Game> availableGames) {
        this.availableGames = availableGames;
    }

    public ArrayList<Game> getActiveGames() {
        return activeGames;
    }

    public void setActiveGames(ArrayList<Game> activeGames) {
        this.activeGames = activeGames;
    }

    public SignInResult login(String username, String password) {
        SignInResult result = new SignInResult();
        if (username.equals("") || username == null) {
            result.setErrorMessage("Invalid username");
            return result;
        }
        if (password.equals("") || password == null) {
            result.setErrorMessage("Invalid password");
        }
        for (int i = 0; i < registeredUsers.size(); ++i) {
            String curUser = registeredUsers.get(i).getUsername();
            if (curUser.equals(username)) {
                String curPassword = registeredUsers.get(i).getPassword();
                if (curPassword.equals(password)) {
                    UUID newID = UUID.randomUUID();
                    String newToken = newID.toString();
                    Login newLogin = new Login(username, newToken);
                    loggedinUsers.add(newLogin);
                    result.setAuthToken(newToken);
                    result.setErrorMessage("");
                    return result;
                }
                else {
                    result.setErrorMessage("Incorrect password");
                    return result;
                }
            }
        }
        result.setErrorMessage("Username not found, Try again");
        return result;
    }

    public SignInResult register(String username, String password) {
        SignInResult result = new SignInResult();

        if (username.equals("") || username == null) {
            result.setErrorMessage("Invalid username");
            return result;
        }
        if (password.equals("") || password == null) {
            result.setErrorMessage("Invalid password");
            return result;
        }
        for (int i = 0; i < registeredUsers.size(); ++i) {
            String curUser = registeredUsers.get(i).getUsername();
            if (curUser.equals(username)) {
                result.setErrorMessage("Username already taken");
                return result;
            }
        }
        User newUser = new User(username, password);
        registeredUsers.add(newUser);
        return login(username, password);
    }

    public JoinResult joinGame(String username, String gameName) {
        if (username.equals("") || username == null) {
            return new JoinResult("Invalid username");
        }
        if (gameName.equals("") || gameName == null) {
            return new JoinResult("Invalid game name");
        }
        for (int i = 0; i < availableGames.size(); ++i) {
            String curGame = availableGames.get(i).getGameName();
            if (curGame.equals(gameName)) {
                Game targetGame = availableGames.get(i);
                int index = findPlayerIndex(username);
                registeredUsers.get(index).addGame(targetGame);
                User player = findPlayer(username);
                try {
                    targetGame.addPlayer(player);
                } catch(Exception e) {
                    String message = e.getMessage();
                    return new JoinResult(message);
                }
                if (targetGame.getPlayersJoined() == targetGame.getMaxPlayers()) {
                    startGame(targetGame.getGameName());
                }
            }
        }
        return new JoinResult("Game name not found");
    }

    public LeaveResult leaveGame(String username, String gameName) {
        if (username.equals("") || username == null) {
            return new LeaveResult("Invalid username");
        }
        if (gameName.equals("") || gameName == null) {
            return new LeaveResult("invalid game name");
        }
        for (int i = 0; i < availableGames.size(); ++i) {
            String curGame = availableGames.get(i).getGameName();
            if (curGame.equals(gameName)) {
                Game targetGame = availableGames.get(i);
                int index = findPlayerIndex(username);
                registeredUsers.get(index).removeGame(targetGame);
                User player = findPlayer(username);
                try {
                    targetGame.removePlayer(player);
                } catch(Exception e) {
                    String message = e.getMessage();
                    return new LeaveResult(message);
                }
                if (targetGame.getPlayersJoined() == 0) {
                    removeGame(targetGame.getGameName());
                }
            }
        }
        return new LeaveResult("Game name not found");
    }

    public CreateResult createGame(String username, String gameName, int maxPlayers) {
        for (int i = 0; i < availableGames.size(); ++i) {
            String curGame = availableGames.get(i).getGameName();
            if (curGame.equals(gameName)) {
                return new CreateResult("Game name already in use");
            }
        }
        for (int i = 0; i < activeGames.size(); ++i) {
            String curGame = activeGames.get(i).getGameName();
            if (curGame.equals(gameName)) {
                return new CreateResult("Game name already in use");
            }
        }
        if (maxPlayers < 2 && maxPlayers > 5) {
            return new CreateResult("Invalid number of players");
        }
        if (gameName.equals("") || gameName == null) {
            return new CreateResult("Invalid Game Name");
        }
        if (username.equals("") || username == null) {
            return new CreateResult("Invalid username");
        }
        Game newGame = new Game(maxPlayers, 1, gameName);
        int index = findPlayerIndex(username);
        registeredUsers.get(index).addGame(newGame);
        User player = findPlayer(username);
        try {
            newGame.addPlayer(player);
        } catch(Exception e) {
            String message = e.getMessage();
            return new CreateResult(message);
        }
        availableGames.add(newGame);
        return new CreateResult(gameName, maxPlayers);
    }

    public String startGame(String gameName) {
        if (gameName.equals("") || gameName == null) {
            return "Invalid username";
        }
        for (int i = 0; i < availableGames.size(); ++i) {
            Game curGame = availableGames.get(i);
            if (curGame.getGameName().equals(gameName)) {
                availableGames.remove(i);
                activeGames.add(curGame);
                return "";
            }
        }
        return "Error: Game not started";
    }

    public void clear() {
        registeredUsers.clear();
        loggedinUsers.clear();
        availableGames.clear();
        activeGames.clear();
    }

    private int findPlayerIndex(String username) {
        for (int i = 0; i < registeredUsers.size(); ++i) {
            String curPlayer = registeredUsers.get(i).getUsername();
            if (curPlayer.equals(username)) {
                return i;
            }
        }
        return -1;
    }

    private void removeGame(String gameName) {
        for (int i = 0; i < availableGames.size(); ++i) {
            String curGame  = availableGames.get(i).getGameName();
            if (curGame.equals(gameName)) {
                availableGames.remove(i);
                return;
            }
        }
    }

    private User findPlayer(String username) {
        for (int i = 0; i < registeredUsers.size(); ++i) {
            String curPlayer = registeredUsers.get(i).getUsername();
            if (curPlayer.equals(username)) {
                return registeredUsers.get(i);
            }
        }
        return null;
    }

    public boolean isValidAuthToken(String username, String authToken){
        for (int i = 0; i < loggedinUsers.size(); ++i){
            if (loggedinUsers.get(i).getUsername().equals(username)){
                if (loggedinUsers.get(i).getAuthToken().equals(authToken)){
                    return true;
                }
            }
        }
        return false;
    }
}
