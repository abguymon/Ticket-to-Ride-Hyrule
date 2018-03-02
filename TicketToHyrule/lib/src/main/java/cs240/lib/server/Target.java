package cs240.lib.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;

import cs240.lib.Model.ChatEntry;
import cs240.lib.Model.Game;
import cs240.lib.Model.LobbyGame;
import cs240.lib.Model.Login;
import cs240.lib.Model.User;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.gameParts.GameMap;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.client.Poller;
import cs240.lib.common.Command;
import cs240.lib.common.IServer;
import cs240.lib.common.results.ChatResult;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.GameHistoryResult;
import cs240.lib.common.results.GetGameResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.PollerResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.common.results.StartGameResult;
import cs240.lib.common.results.SubmitResult;


//implements IServer
public class Target implements IServer {
    public static final Target SINGLETON = new Target();

    private ArrayList<User> registeredUsers;
    private ArrayList<Login> loggedinUsers;
    private ArrayList<LobbyGame> availableGames;
    private ArrayList<Game> activeGames;
    private ArrayList<Command> commandHistory;
    private Queue<Command> commandQueue;

    private Target() {
        registeredUsers = new ArrayList<>();
        loggedinUsers = new ArrayList<>();
        availableGames = new ArrayList<>();
        activeGames = new ArrayList<>();
        commandHistory = new ArrayList<>();
        commandQueue = new LinkedList<>();
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

    public ArrayList<LobbyGame> getAvailableGames() {
        return availableGames;
    }

    public void setAvailableGames(ArrayList<LobbyGame> availableGames) {
        this.availableGames = availableGames;
    }

    public ArrayList<Game> getActiveGames() {
        return activeGames;
    }

    public void setActiveGames(ArrayList<Game> activeGames) {
        this.activeGames = activeGames;
    }

    public SignInResult login(String username, String password) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, password};
        Command loginCommand = new Command("login", parameterTypeNames, parameters);
        commandHistory.add(loginCommand);
        commandQueue.add(loginCommand);
        Poller.getInstance().incrementCommandIndex();
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
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, password};
        Command registerCommand = new Command("register", parameterTypeNames, parameters);
        commandHistory.add(registerCommand);
        commandQueue.add(registerCommand);
        Poller.getInstance().incrementCommandIndex();
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
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, gameName};
        Command joinGameCommand = new Command("joinGame", parameterTypeNames, parameters);
        commandHistory.add(joinGameCommand);
        commandQueue.add(joinGameCommand);
        Poller.getInstance().incrementCommandIndex();
        if (username.equals("") || username == null) {
            return new JoinResult("Invalid username");
        }
        if (gameName.equals("") || gameName == null) {
            return new JoinResult("Invalid game name");
        }
        for (int i = 0; i < availableGames.size(); ++i) {
            String curGame = availableGames.get(i).getGameName();
            if (curGame.equals(gameName)) {
                LobbyGame targetGame = availableGames.get(i);
                int index = findPlayerIndex(username);
                registeredUsers.get(index).addGame(targetGame.getGameName());
                User player = findPlayer(username);
                try {
                    targetGame.addPlayer(player.getUsername());
                    return new JoinResult(targetGame.getGameName(), targetGame.getPlayersJoined());

                } catch(Exception e) {
                    String message = e.getMessage();
                    return new JoinResult(message);
                }

            }
        }
        return new JoinResult("Game name not found");
    }

    public LeaveResult leaveGame(String username, String gameName) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {username, gameName};
        Command leaveCommand = new Command("leaveGame", parameterTypeNames, parameters);
        commandHistory.add(leaveCommand);
        commandQueue.add(leaveCommand);
        Poller.getInstance().incrementCommandIndex();
        if (username.equals("") || username == null) {
            return new LeaveResult("Invalid username");
        }
        if (gameName.equals("") || gameName == null) {
            return new LeaveResult("invalid game name");
        }
        for (int i = 0; i < availableGames.size(); ++i) {
            String curGame = availableGames.get(i).getGameName();
            if (curGame.equals(gameName)) {
                LobbyGame targetGame = availableGames.get(i);
                int index = findPlayerIndex(username);
                registeredUsers.get(index).removeGame(targetGame.getGameName());
                User player = findPlayer(username);
                try {
                    targetGame.removePlayer(player.getUsername());
                    if (targetGame.getPlayersJoined() == 0) {
                        removeGame(targetGame.getGameName());
                    }
                    return new LeaveResult(targetGame.getGameName(), targetGame.getPlayersJoined());
                } catch(Exception e) {
                    String message = e.getMessage();
                    return new LeaveResult(message);
                }

            }
        }
        return new LeaveResult("Game name not found");
    }

    public CreateResult createGame(String username, String gameName, int maxPlayers) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName(), int.class.getName()};
        Object[] parameters = {username, gameName, maxPlayers};
        Command createGameCommand = new Command("createGame", parameterTypeNames, parameters);
        commandHistory.add(createGameCommand);
        commandQueue.add(createGameCommand);
        Poller.getInstance().incrementCommandIndex();
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
        LobbyGame newGame = new LobbyGame(maxPlayers, 0, gameName);
        int index = findPlayerIndex(username);
        registeredUsers.get(index).addGame(newGame.getGameName());
        User player = findPlayer(username);
        try {
            newGame.addPlayer(player.getUsername());
        } catch(Exception e) {
            String message = e.getMessage();
            return new CreateResult(message);
        }
        availableGames.add(newGame);
        return new CreateResult(gameName, maxPlayers);
    }

    public StartGameResult startGame(String gameName) {
        String[] parameterTypeNames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command startGameCommand = new Command("startGame", parameterTypeNames, parameters);
        commandHistory.add(startGameCommand);
        commandQueue.add(startGameCommand);
        Poller.getInstance().incrementCommandIndex();

        if (gameName.equals("") || gameName == null) {
            return new StartGameResult("Invalid username");
        }
        for (int i = 0; i < availableGames.size(); ++i) {
            LobbyGame curGame = availableGames.get(i);
            if (curGame.getGameName().equals(gameName)) {
                availableGames.remove(i);
                Game newGame = initializeGame(curGame);
                activeGames.add(newGame);
                return new StartGameResult(curGame.getPlayerArray());
            }
        }
        return new StartGameResult("Error: Game not started");
    }

    public PollerResult pollerCheckServer(int index){
        Queue<Command> execute = new LinkedList<>();
//        while(!commandQueue.isEmpty())
//        {
//            execute.add(commandQueue.remove());
//        }
        for(int i = index; i < commandHistory.size(); i++){
            execute.add(commandHistory.get(i));
        }
//        System.out.println("COMMAND HISTORY " + commandHistory.size());
//        System.out.println("INDEX " + index);
        //commandQueue.clear();
        return new PollerResult(execute);
    }

    @Override
    public GameHistoryResult getGameHistory(String gameName) {
        if (gameName == null) {
            return new GameHistoryResult("Error: Invalid game name");
        }
        if (gameName.equals("")) {
            return new GameHistoryResult("Invalid game name");
        }
        for (int i = 0; i < activeGames.size(); ++i) {
            if (activeGames.get(i).getGameName().equals(gameName)) {
                return new GameHistoryResult(activeGames.get(i).getGameHistory());
            }
        }
        return new GameHistoryResult("Game not found");
    }

    //won't add to chat history here to avoid duplicate entries, but maybe should
    @Override
    public ChatResult chat(String playerName, String message, String gameName) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, message, gameName};
        Command chatGameCommand = new Command("chat", parameterTypeNames, parameters);
        commandHistory.add(chatGameCommand);
        commandQueue.add(chatGameCommand);
        Poller.getInstance().incrementCommandIndex();
        if (playerName == null || message == null || gameName == null) {
            return new ChatResult("1 or more null parameters");
        }
        for (int i = 0; i < activeGames.size(); ++i) {
            if (activeGames.get(i).getGameName().equals(gameName)) {
                Game game = activeGames.get(i);
                for (int j = 0; j < game.getPlayerArray().size(); ++j) {
                    Player player = game.getPlayerArray().get(j);
                    if (player.getPlayerName().equals(playerName)) {
                        return new ChatResult(playerName, message);
                    }
                }
            }
        }
        return new ChatResult("Game not found");
    }

    @Override
    public DrawDestinationCardResult drawDestinationCard(String playerName, String gameName) {return null;}

    @Override
    public SubmitResult submitDestinationCards(String playerName, String gameName, DestinationCard card) {return null;}

    @Override
    public GetGameResult getGameData(String gameName) {
        //command won't be added to the poller queue or game history
        if (gameName == null) {
            return new GetGameResult("Invalid game name");
        }
        if (gameName.equals("")) {
            return new GetGameResult("Invalid game name");
        }
        for (int i = 0; i < activeGames.size(); ++i) {
            if (activeGames.get(i).getGameName().equals(gameName)) {
                return new GetGameResult(activeGames.get(i));
            }
        }
        return new GetGameResult("Error: Game not found");
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

    private Game initializeGame(LobbyGame gameToStart) {
        Game game = new Game(gameToStart.getGameName());
        initializeTrainCardDeck(game);
        initializeDestinationCards(game);
        initializeGameMap(game);
        initializeFaceUpCards(game);
        initializePlayers(game);
        game.addToGameHistory(gameToStart.getGameName() + " started!");
        return game;
    }

    private void initializeTrainCardDeck(Game game) {

    }

    private void initializeDestinationCards(Game game) {

    }

    private void initializeGameMap(Game game) {

    }

    private void initializeFaceUpCards(Game game) {

    }

    private void initializePlayers(Game game) {

    }
}
