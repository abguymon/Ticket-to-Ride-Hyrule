package cs240.lib.Model;


import java.util.ArrayList;

import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.Model.states.IState;
import cs240.lib.Model.states.TurnEnded;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.results.ChatResult;
import cs240.lib.common.results.ClaimRouteResult;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.DrawDestinationCardResult;
import cs240.lib.common.results.DrawFaceUpTrainCardResult;
import cs240.lib.common.results.DrawTrainCardResult;
import cs240.lib.common.results.GameHistoryResult;
import cs240.lib.common.results.GetGameResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.common.results.StartGameResult;
import cs240.lib.common.results.SubmitResult;
import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by adam on 2/7/18.
 */
/**
 * This class is responsible for being a facade to the model classes and stores the
 * data currently needed by the client
 * */
public class ModelFacade {
    /**
     * This is the list of games currently displayed in the game lobby
     */
    private ArrayList<LobbyGame> lobbyGameList = new ArrayList<>();
    /**
     * This is the list of games that have just reached max players
     */
    private ArrayList<LobbyGame> startedLobbyGames = new ArrayList<>();
    /**
     * This is the information on the current game joined by the player
     */
    private Game gameData = null;
    /**
     * This is the current user logged in
     */
    private Login currentUser = null;
    /**
     * This is the data of the current player
     */
    private Player currentPlayer = null;
    public ModelFacade(){}

    public GetGameResult sync(){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        GetGameResult getGameResult = ServerProxy.SINGLETON.sync(getGameData().getGameName());
        ArrayList<Player> playerArray = gameData.getPlayerArray();
        int currentPlayer = 0;
        IState oldState = new TurnEnded();
        for(int i = 0; i < playerArray.size(); i++){
            if(playerArray.get(i).getState() instanceof TurnEnded){}
            else {oldState = playerArray.get(i).getState();
            currentPlayer = i;}
        }

        setGameData(getGameResult.getGameStarted());

        playerArray = gameData.getPlayerArray();
        for(int i = 0; i <playerArray.size(); i++){
            if(i == currentPlayer) playerArray.get(i).setState(oldState);
            else playerArray.get(i).setState(new TurnEnded());
        }

        return getGameResult;
    }

    public String claimRoute(Route route, String gameName, TrainCardColor chosenCardsColor){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        ClaimRouteResult result = currentPlayer.claimRoute(gameName, route, chosenCardsColor);
        if(result != null && result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    public String chooseDestinationCards(String playerName, String gameName, ArrayList<DestinationCard> cards){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        SubmitResult result = currentPlayer.submitDestinationCards(gameName, cards); //THIS NEEDS TO BE A METHOD IN PLAYER I BELIEVE... ARE WE SUBMITTING CARDS KEPT OR CARDS SENT BACK?
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    public String drawLocomotive(int card, String playerName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        DrawFaceUpTrainCardResult result = currentPlayer.drawLocomotive(gameName, card);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    public String drawFaceUpTrainCard(int card, String playerName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        DrawFaceUpTrainCardResult result = currentPlayer. drawFaceUpTrainCard(gameName, card);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }
    public String drawTrainCard(String playerName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        DrawTrainCardResult result = currentPlayer.drawTrainCard(gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }
    public String drawDestinationCards(String playerName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        DrawDestinationCardResult result = currentPlayer.drawDestinationCard(gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }
    /**
     * This gets all the data on the game that the user just joined
     * @param gameName name of the game that the user just joined
     * @return returns either an error message String or the data of the Game
     * pre - the gameName needs to be a correct game that has players joined equal to the max players
     * post - the client receives the data on the game that just started
     */
    public Object getGameData(String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        GetGameResult result = ServerProxy.SINGLETON.getGameData(gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                gameData = result.getGameStarted();
                return result;
            }catch(Exception ex){
                return("EXCEPTION" + ex);
            }
        }
    }

    /**
     * creates a game that will be stored on the server as a joinable game
     * @param userName name of the user creating the game
     * @param gameName name of the game the user is creating
     * @param maxPlayers number of players the user wants the game to have
     * @return returns a string either an error message or blank to signify a success
     * pre - the userName must be correct and be associated with a valid auth token, gameName can't be taken, max players must be 2-5
     * post - the game is created on the server and will be displayed in the lobby as a joinable game
     */
    public String createGame(String userName, String gameName, int maxPlayers){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        CreateResult result = ServerProxy.SINGLETON.createGame(userName, gameName, maxPlayers);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                return "";
            }catch(Exception ex){
                return("EXCEPTION " + ex);
            }
        }
    }

    /**
     * removes the user passed in from the game associated with the gameName passed in
     * @param userName name associated with user to be remvoed
     * @param gameName name associated with game user is to be removed from
     * @return returns string either error message or null if command was processed correctly
     * pre - userName and gameName must exist in server and must be leavable
     * post - removes user from the game on the server
     */
    public String leaveGame(String userName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        LeaveResult result = ServerProxy.SINGLETON.leaveGame(userName, gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    /**
     * Tries to login using the information entered
     * @param userName name of user to be logged in
     * @param password password of user to be logged in
     * @return returns string either error message or empty if command was processed correctly
     * pre - userName and password must not be empty
     * post - logs user in to access the games in the lobby
     */
    public String login(String userName, String password){
        SignInResult result = ServerProxy.SINGLETON.login(userName, password);
        if(!result.getErrorMessage().equals("")){
            return result.getErrorMessage();
        }
        else{
            currentUser = new Login(userName, result.getAuthToken());
            return "";
        }
    }

    /**
     * tries to register a user with the information entered
     * @param userName name of user to be registered
     * @param password password of user to be registered
     * @return returns string either error message or empty if command was processed correctly
     * pre - userName and password must not be empty
     * post - creates a new user to be stored in the server associated with an authtoken
     */
    public String register(String userName, String password){
        SignInResult result = ServerProxy.SINGLETON.register(userName, password);
        if(!result.getErrorMessage().equals("")){
            return result.getErrorMessage();
        }
        else{
            currentUser = new Login(userName, result.getAuthToken());
            return "";
        }
    }

    /**
     * tries to add the user to the joinable game in the lobby
     * @param userName name of user to join the game
     * @param gameName name of game user is to join
     * @return returns string either error message or empty if command was processed correctly
     * pre - userName and gameName must exist in the server
     * post - joins user to game in the server
     */
    public String joinGame(String userName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        boolean start = false;
        if(getGame(gameName).getPlayersJoined() == (getGame(gameName).getMaxPlayers() - 1)){
            start = true;
        }
        JoinResult result = ServerProxy.SINGLETON.joinGame(userName,gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                //TODO: how does the observer pattern/poller work with start game code? -David
                if(start) return startGame(gameName);
                else return "";
            }catch(Exception ex){
                return "EXCEPTION! " + ex;
            }
        }
    }

    /**
     * tries to start a game in the lobby
     * @param gameName name of game to be started
     * @return returns string either error message or null if command was processed correctly
     * pre - game must exist in the server and have reached its max players
     * post - initializes the game in the server
     */
    public String startGame(String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        StartGameResult result = ServerProxy.SINGLETON.startGame(gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    /**
     * adds a chat message to the chat in a game
     * @param message string to be added to the game chat
     * @param gameName game chat is to be added to
     * @return returns string either error message or null if command was processed correctly
     * pre - game must be a game that is started in the server
     * post - message is added to the game chat history
     */
    public String sendMessage(String message, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        ChatResult result = ServerProxy.SINGLETON.chat(currentUser.getUsername(), message, gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    /**
     * submits the server if a destination card will be discarded
     * @param gameName name of game destination card is attached to
     * @param card card that is sent back to the deck or null if all cards are kept
     * @return returns string either error message or null if command was processed correctly
     * pre - game must be a started game in the server and destination card must be formatted correctly
     * post - destination card is removed from user and placed back in the deck
     */
    public String submitDestinationCard(String gameName, DestinationCard card){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        SubmitResult result = ServerProxy.SINGLETON.submitDestinationCards(currentUser.getUsername(), gameName, card);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }

    /**
     * requests the history of the game passed in
     * @param gameName name of game history is being requested from
     * @return returns string either error message or null if command was processed correctly
     * pre - game must be started in the server
     * post - history of game is sent back to client
     */
    public String getGameHistory(String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        GameHistoryResult result = ServerProxy.SINGLETON.getGameHistory(gameName);
        if(result.getErrorMessage()!= null)
            return result.getErrorMessage();
        else{
            return"";
        }
    }

    public LobbyGame getGame(String gameName){
        for(LobbyGame g : lobbyGameList){
            if(g.getGameName().equals(gameName)) return g;
        }
        return null;
    }
    public ArrayList<LobbyGame> getGames(){
        return lobbyGameList;
    }

    public Login getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Login currentUser) {
        this.currentUser = currentUser;
    }

    public void setGames(ArrayList<LobbyGame> lobbyGames){
        this.lobbyGameList = lobbyGames;
    }

    public ArrayList<LobbyGame> getStartedLobbyGames() {
        return startedLobbyGames;
    }

    public void setStartedLobbyGames(ArrayList<LobbyGame> startedLobbyGames) {
        this.startedLobbyGames = startedLobbyGames;
    }

    public Game getGameData() {
        return gameData;
    }

    public void setGameData(Game gameData) {
        this.gameData = gameData;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
