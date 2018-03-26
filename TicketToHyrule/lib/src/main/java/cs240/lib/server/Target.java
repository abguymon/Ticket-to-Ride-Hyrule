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
import cs240.lib.Model.colors.PlayerColor;
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.GameMap;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.client.Poller;
import cs240.lib.common.Command;
import cs240.lib.common.IServer;
import cs240.lib.common.results.ChatResult;
import cs240.lib.common.results.ClaimRouteResult;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;
import cs240.lib.common.results.EndGameResult;
import cs240.lib.common.results.EndTurnResult;
import cs240.lib.common.results.GameHistoryResult;
import cs240.lib.common.results.GetGameResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.PollerResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.common.results.StartGameResult;
import cs240.lib.common.results.SubmitResult;


/**
 *Stores all of the users, players, and games currently being supported
 * by the app in addition to processing all of the clients' requests
 * by performing the appropriate data manipulations.
 * Operations received via generic commans.
 * Supported operations include user registration and login, creating games as a user,
 * joining game as a player, leaving games as a player, starting games, in game chat,
 * and in game retrieval and storage of the game's history
 *
 */
public class Target implements IServer {
    /**
     * Singleton object so there will
     * only ever be one server target
     * for the clients to interact with
     */
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

    /**
     *@pre SINGLETON must not be null
     * @post none
     * @return the registered users
     */
    public ArrayList<User> getRegisteredUsers() {
        return registeredUsers;
    }

    /**
     *@pre SINGLETON must not be null
     * @post sets the registered users to the parameter
     * @param registeredUsers the new array of registered users
     */
    public void setRegisteredUsers(ArrayList<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    /**
     *@pre SINGLETON must not be null
     * @post none
     * @return all logged in users
     */
    public ArrayList<Login> getLoggedinUsers() {
        return loggedinUsers;
    }

    /**
     *@pre SINGLETON must not be null
     * @post none
     * @param loggedinUsers new list of logged in users
     */
    public void setLoggedinUsers(ArrayList<Login> loggedinUsers) {
        this.loggedinUsers = loggedinUsers;
    }

    /**
     *@pre SINGLETON must not be null
     * @post none
     * @return all available or joinable games to be displayed in the lobby
     */
    public ArrayList<LobbyGame> getAvailableGames() {
        return availableGames;
    }

    /**
     *@pre SINGELTON must not be null
     * @post none
     * @param availableGames new list of available games that may be joined
     */
    public void setAvailableGames(ArrayList<LobbyGame> availableGames) {
        this.availableGames = availableGames;
    }

    /**
     *@pre SINGLETON must not be null
     * @post none
     * @return active or already started games
     */
    public ArrayList<Game> getActiveGames() {
        return activeGames;
    }

    /**
     *@pre SINGLETON must not be null
     * @post none
     * @param activeGames new list of started games
     */
    public void setActiveGames(ArrayList<Game> activeGames) {
        this.activeGames = activeGames;
    }

    /**
     *@pre username and password can't be null nor the empty string. Password must match username
     * under the registered users
     * @post user is logged in and is returned an auth token
     * @param username the username of an already registered user to be logged in
     * @param password the corresponding password to username.
     * @return a SignInResult containing the authorization token on success or an error message on failure
     */
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

    /**
     *@pre username and password must not be null nor the empty string. username can't match that of
     * an already registered user
     * @post new user is registered and logged in
     * @param username the user name desired for the new user.
     *                 Can't be the same as an already registered user
     * @param password the password for future logins under username
     * @return a SignInResult with an auth token or error message if failure occurs
     */
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

    /**
     *@pre username and gameName can't be null nor the empty string. The
     * user with username must be registered and logged in and a game with gameName must be available
     * @post user is added to the game under gameName and begins as a player in that
     * game when the game is full
     * @param username username of the user to be added to the seleced game
     * @param gameName the name of the game to which the user is to be added
     * @return JoinResult containing the new number of players in the game or
     * an error message in the case of failure
     */
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

    /**
     *@pre username and gameName can't be null nor the empty string
     * the user with username must be logged in and registered and already have
     * joined the game with gameName.
     * gameName must match an available game in the lobby
     * @post user with username is removed from the game with gameName
     * @param username the name of the user to be removed from the selected game
     * @param gameName the name of the from which the user si to be removed
     * @return LeaveResult containing the new number of players in the game or
     * an error messagein the case of failure
     */
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

    /**
     *@pre username and gameName can't be null nor the empty string
     * username must match a logged in and registered user
     * gameName can't match an available game or an active game
     * maxPlayers must be 2 or larger and 5 or less
     * @post a new lobby game is created and becomes available, user under username
     * is automatically joined to the new game as a player
     * @param username the username of the creating user
     * @param gameName the name of the new game
     * @param maxPlayers The number of players in the new game
     * @return CreateResult with all of the information to create a new lobby game
     * in the client or an error message in the case of failure
     */
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

    /**
     *@pre gameName can't be null nor the empty string
     * gameName must match an available lobby game
     * the available with game name must be full (have players joined equal to its max players)
     * @post the game with gameName is removed from available games
     * a new game object is initialized and all players are initialized
     * the new game is added to active games
     * @param gameName the name of the game to be started
     * @return StartGameResult containing the game data of the newly initialized game
     * including the 3 destination cards from which the players may choose two or keep all three
     * also includes an error message in the case of failure
     */
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

    /**
     *@pre index can't be less than 0 or larger than the size of command history
     * @post poller sends the commands after index to all connected devices so they update accordingly
     * @param index the current index that the poller has last checked (last command sent)
     * @return PollerResult containing the results of the poller's check to the server
     */
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

    /**
     *@pre gameName can't be null nor the empty string
     * gameName must match an active game
     * @post grabs and returns the game history of the game with gameName
     * @param gameName the name of the game whose game history is to be returned
     * @return GameHistoryResult which contains the game history of the desired game or
     * an error message in the case of failure
     */
    @Override
    public GameHistoryResult getGameHistory(String gameName) {
        if (gameName == null) {
            return new GameHistoryResult("Error: Invalid game name");
        }
        if (gameName.equals("")) {
            return new GameHistoryResult("Invalid game name");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            return new GameHistoryResult(game.getGameHistory());
        }
        return new GameHistoryResult("Game not found");
    }

    /**
     *@pre playerName and gameName can't be null nor the empty string
     * gameName must match an active game
     * playerName must match a player in the active game with gameName
     * message can't be null
     * @post message is sent to all players in the game with gameName
     * entry is added in the game with gameName's chat history
     * @param playerName the name of the player sending the chat message
     * @param message the content of the message to be sent over chat
     * @param gameName the name of the game where to whose players are sent the chat message
     * @return ChatResult containing the message and the name of the player sending the chat or
     * an error message in the case of failure
     */
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
        Game game = getActiveGame(gameName);
        if (game != null) {
            Player player = game.getPlayer(playerName);
            if (player != null) {
                game.addToGameHistory(playerName + " sent a chat message");
                return new ChatResult(playerName, message);
            }
            else {
                return new ChatResult("Player not found");
            }
        }
        return new ChatResult("Game not found");
    }

    /**
     *@pre playerName and gameName can't be null or the empty string
     * gameName must match an active game
     * playerName must match a plyer in the game with gameName
     * @post draws a destination for the player with playerName in the game with gameName
     * @param playerName the name of the player for whom a card is to be drawn
     * @param gameName the name of the game for which the destination card is to be drawn
     * @return DrawDestinationCardResult containing the destination card drawn or an error
     * message in the case of a failure
     */
    @Override
    public DrawDestinationCardResult drawDestinationCard(String playerName, String gameName) {
        String[] parameterTypeNames = {String.class.getName()};
        Object[] parameters = {playerName, gameName};
        Command submitCommand = new Command("drawDestinationCard", parameterTypeNames, parameters);
        commandHistory.add(submitCommand);
        commandQueue.add(submitCommand);
        Poller.getInstance().incrementCommandIndex();
        if (playerName == null || gameName == null) {
            return new DrawDestinationCardResult("1 or more null fields");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            if (game.getGameName().equals(gameName)) {
                Player player = game.getPlayer(playerName);
                if (player != null) {
                    ArrayList<DestinationCard> cardArray = new ArrayList<>();
                    for (int i = 0; i < 3; ++i) {
                        DestinationCard cardDrawn = game.drawDestinationCard();
                        cardArray.add(cardDrawn);
                        player.addDestinationCard(cardDrawn);
                        game.addToGameHistory(playerName + " drew destination cards");
                        return new DrawDestinationCardResult(cardArray);
                    }
                }
                else {
                    return new DrawDestinationCardResult("Player not found");
                }
            }
        }
        return new DrawDestinationCardResult("Game not found");
    }

    @Override
    public SubmitResult discardDestinationCards(String playerName, String gameName, DestinationCard card1, DestinationCard card2) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName(), DestinationCard.class.getName(), DestinationCard.class.getName()};
        Object[] parameters = {playerName, gameName, card1, card2};
        Command submitCommand = new Command("discardDestinationCards", parameterTypeNames, parameters);
        commandHistory.add(submitCommand);
        commandQueue.add(submitCommand);
        Poller.getInstance().incrementCommandIndex();
        if (gameName == null || playerName == null) {
            return new SubmitResult("Error: 1 more null fields");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            Player player = game.getPlayer(playerName);
            if (player != null) {
                if (card1 == null && card2 == null) {
                    game.addToGameHistory(playerName + " took 3 destination cards");
                    return new SubmitResult(true);
                }
                player.dropDestinationCard(card1);
                game.putbackDestinationCard(card1);
                if (card2 == null) {
                    game.addToGameHistory(playerName + " took 2 destination cards");
                }
                else {
                    game.addToGameHistory(playerName + " took 1 destination card");
                    player.dropDestinationCard(card2);
                    game.putbackDestinationCard(card2);
                }
                return new SubmitResult(true);
            }
            return new SubmitResult("Player not found");
        }
        return new SubmitResult("Game not found");
    }

    /**
     *@pre playerName and gameName can't be null nor the empty string
     * gameName must match an active game
     * playerName must match a player in the game with gameName
     * @post if card isn't null, the player with playerName's destination cards are updated with card removed
     * @param playerName the name of the player whose destination cards are to be updated
     * @param gameName the name of game in which a player's destination cards are to be updated
     * @param card if null, all cards are kept in pre game setup. If not, card is removed from the
     *             matching player and placed back into the destination card deck of the game with gameName
     * @return SubmitResult containing a boolean if successful or not and an error message if a failure occurs
     */
    @Override
    public SubmitResult submitDestinationCards(String playerName, String gameName, DestinationCard card) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName(), DestinationCard.class.getName()};
        Object[] parameters = {playerName, gameName, card};
        Command submitCommand = new Command("submitDestinationCards", parameterTypeNames, parameters);
        commandHistory.add(submitCommand);
        commandQueue.add(submitCommand);
        Poller.getInstance().incrementCommandIndex();
        if (gameName == null || playerName == null) {
            return new SubmitResult("Error: 1 more null fields");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            Player player = game.getPlayer(playerName);
            if (player != null) {
                if (card == null) {
                    game.addToGameHistory(playerName + " took 3 destination cards");
                    return new SubmitResult(true);
                }
                player.dropDestinationCard(card);
                game.putbackDestinationCard(card);
                game.addToGameHistory(playerName + " took 2 destination cards");
                return new SubmitResult(true);
            }
            return new SubmitResult("Error: Player nor found");
        }
        return new SubmitResult("Error: Game not found");
    }

    /**
     *@pre gameName can't be null nor the empty string
     * gameName must match an already started game
     * @post grabs and returns the active game data matching gameName
     * @param gameName the name of the game to be returned
     * @return GetGameResult which contains the game data for the game matching gameName
     */
    @Override
    public GetGameResult getGameData(String gameName) {
        //command won't be added to the poller queue or game history
        if (gameName == null) {
            return new GetGameResult("Invalid game name");
        }
        if (gameName.equals("")) {
            return new GetGameResult("Invalid game name");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            return new GetGameResult(game);
        }
        return new GetGameResult("Error: Game not found");
    }

    @Override
    public void sync(String gameName) {
        String[] parameterTypeNames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command syncCommand = new Command("sync", parameterTypeNames, parameters);
        commandHistory.add(syncCommand);
        commandQueue.add(syncCommand);
        Poller.getInstance().incrementCommandIndex();
    }

    @Override
    public ClaimRouteResult claimRoute(String playerName, String gameName, Route route, TrainCardColor chosenCardsColor) {
        String[] parameterTypeNames = {String.class.getName(), Route.class.getName()};
        Object[] parameters = {playerName, gameName, route};
        Command submitCommand = new Command("claimRoute", parameterTypeNames, parameters);
        commandHistory.add(submitCommand);
        commandQueue.add(submitCommand);
        Poller.getInstance().incrementCommandIndex();
        if (playerName == null || gameName == null || route == null) {
            return new ClaimRouteResult("1 or more null fields");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            if (game.getGameName().equals(gameName)) {
                Player player = game.getPlayer(playerName);
                if (player != null) {
                    if (game.claimRoute(player, route, chosenCardsColor)) {
                        game.addToGameHistory(playerName + " claimed the route " + route.toString());
                        if (game.isFinalRound()) {
                            game.addToGameHistory("Final Round!!!");
                            return new ClaimRouteResult(true);
                        }
                        else {
                            return new ClaimRouteResult(false);
                        }
                    }
                    return  new ClaimRouteResult("Route unsuccessfully claimed");
                }
                else {
                    return new ClaimRouteResult("Player not found");
                }
            }
        }
        return new ClaimRouteResult("Game not found");
    }

    @Override
    public DrawTrainCardResult drawTrainCard(String playerName, String gameName) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, gameName};
        Command submitCommand = new Command("drawTrainCard", parameterTypeNames, parameters);
        commandHistory.add(submitCommand);
        commandQueue.add(submitCommand);
        Poller.getInstance().incrementCommandIndex();

        if (playerName == null || gameName == null) {
            return new DrawTrainCardResult("1 or more null fields");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            if (game.getGameName().equals(gameName)) {
                Player player = game.getPlayer(playerName);
                if (player != null) {
                    TrainCard cardPicked = game.drawTrainCard();
                    player.addTrainCard(cardPicked);
                    game.addToGameHistory(playerName + " drew a train card from the deck");
                    return new DrawTrainCardResult(cardPicked);
                }
                else {
                    return new DrawTrainCardResult("Player not found");
                }
            }
        }
        return new DrawTrainCardResult("Game not found");
    }
    @Override
     public DrawFaceUpTrainCardResult drawFaceUpTrainCard(String playerName, String gameName, int positionPicked) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName(), Integer.class.getName()};
        Object[] parameters = {playerName, gameName, positionPicked};
        Command submitCommand = new Command("drawFaceUpTrainCard", parameterTypeNames, parameters);
        commandHistory.add(submitCommand);
        commandQueue.add(submitCommand);
        Poller.getInstance().incrementCommandIndex();
        if (playerName == null || gameName == null) {
            return new DrawFaceUpTrainCardResult("1 or more null fields");
        }
        if (positionPicked < 0 || positionPicked > 4) {
            return new DrawFaceUpTrainCardResult("Position out of Range");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            if (game.getGameName().equals(gameName)) {
               Player player = game.getPlayer(playerName);
               if (player != null) {
                    TrainCard cardPicked = game.drawFaceUpTrainCard(positionPicked);
                    player.addTrainCard(cardPicked);
                    game.addToGameHistory(playerName + " picked a face up train card");
                    return new DrawFaceUpTrainCardResult(cardPicked);
               }
               else {
                   return new DrawFaceUpTrainCardResult("Player not found");
               }
            }
        }
        return new DrawFaceUpTrainCardResult("Game not found");
    }

    @Override
    public EndTurnResult endTurn(String playerName, String gameName) {
        String[] parameterTypeNames = {String.class.getName(), String.class.getName()};
        Object[] parameters = {playerName, gameName};
        Command submitCommand = new Command("endTurn", parameterTypeNames, parameters);
        commandHistory.add(submitCommand);
        commandQueue.add(submitCommand);
        Poller.getInstance().incrementCommandIndex();

        Game game = getActiveGame(gameName);
        if (game != null) {
            int newTurn = game.endTurn();
            game.addToGameHistory("New Turn!");
            return new EndTurnResult(newTurn);
        }
        return new EndTurnResult("Game not found");
    }

    @Override
    public EndGameResult endGame(String gameName) {
        String[] parameterTypeNames = {String.class.getName()};
        Object[] parameters = {gameName};
        Command submitCommand = new Command("endGame", parameterTypeNames, parameters);
        commandHistory.add(submitCommand);
        commandQueue.add(submitCommand);
        Poller.getInstance().incrementCommandIndex();

        if (gameName == null) {
            return new EndGameResult("1 or more null fields");
        }
        Game game = getActiveGame(gameName);
        if (game != null) {
            game.endGame();
            game.addToGameHistory(gameName + " finished");
            removeActiveGame(gameName);
            return new EndGameResult(game);
        }
        return new EndGameResult("Game not found");
    }

    /**
     *@pre none
     * @post clears all the stored data from the server
     */
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

    private void removeActiveGame(String gameName) {
        for (int i = 0; i < activeGames.size(); ++i) {
            if (gameName.equals(activeGames.get(i).getGameName())) {
                activeGames.remove(i);
                return;
            }
        }
    }

    private boolean isValidAuthToken(String username, String authToken){
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
        initializePlayers(game, gameToStart);
        game.addToGameHistory(gameToStart.getGameName() + " started!");
        return game;
    }

    private void initializePlayers(Game game, LobbyGame gameToStart) {
        ArrayList<String> playerNames = gameToStart.getPlayerArray();
        for (int i = 0; i < playerNames.size(); ++i) {
            makeNewPlayer(game, playerNames.get(i), i+1);
        }
    }

    private void makeNewPlayer(Game game, String playerName, int playerNum) {
        Player newPlayer;
        switch (playerNum) {
            case 1:
                newPlayer = new Player(PlayerColor.GREEN, playerName);
                newPlayer.setPlayerNum(1); break;
            case 2:
                newPlayer = new Player(PlayerColor.RED, playerName);
                newPlayer.setPlayerNum(2) ;break;
            case 3:
                newPlayer = new Player(PlayerColor.PINK, playerName);
                newPlayer.setPlayerNum(3); break;
            case 4:
                newPlayer = new Player(PlayerColor.YELLOW, playerName);
                newPlayer.setPlayerNum(4); break;
            case 5:
                newPlayer = new Player(PlayerColor.BLUE, playerName);
                newPlayer.setPlayerNum(5); break;
            default:
                newPlayer = new Player(PlayerColor.GREEN, "Error");
        }
        drawTrainCards(game, newPlayer);
        drawDestinationCards(game, newPlayer);
        game.addPlayer(newPlayer);
    }

    private void drawTrainCards(Game game, Player player) {
        for (int i = 0; i < 4; ++i) {
            TrainCard card = game.drawTrainCard();
            player.addTrainCard(card);
        }
    }

    private void drawDestinationCards(Game game, Player player) {
        for (int i = 0; i < 3; ++i) {
            DestinationCard card = game.drawDestinationCard();
            player.addDestinationCard(card);
        }
    }

    private Game getActiveGame(String gameName) {
        for (int i = 0; i < activeGames.size(); ++i) {
            Game game = activeGames.get(i);
            if (game.getGameName().equals(gameName)) {
                return game;
            }
        }
        return null;
    }
}
