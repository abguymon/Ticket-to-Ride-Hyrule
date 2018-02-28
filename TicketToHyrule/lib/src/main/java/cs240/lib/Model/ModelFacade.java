package cs240.lib.Model;


import java.util.ArrayList;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.results.ChatResult;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by adam on 2/7/18.
 */

public class ModelFacade {
    private ArrayList<Game> gameList = new ArrayList<>();
    private ArrayList<Game> startedGames = new ArrayList<>();
    private Login currentUser = null;
    public ModelFacade(){}



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

    public String joinGame(String userName, String gameName){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        JoinResult result = ServerProxy.SINGLETON.joinGame(userName,gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                //TODO: how does the observer pattern/poller work with start game code? -David
                return "";
            }catch(Exception ex){
                return "EXCEPTION! " + ex;
            }
        }
    }

    public String sendMessage(String message){
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getAuthToken());
        ChatResult result = ServerProxy.SINGLETON.sendMessage(message);
        //WILL THIS BE NULL OR EMPTY STRING???
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            return "";
        }
    }


    public Game getGame(String gameName){
        for(Game g : gameList){
            if(g.getGameName().equals(gameName)) return g;
        }
        return null;
    }
    public ArrayList<Game> getGames(){
        return gameList;
    }

    public Login getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Login currentUser) {
        this.currentUser = currentUser;
    }

    public void setGames(ArrayList<Game> games){
        this.gameList = games;
    }

    public ArrayList<Game> getStartedGames() {
        return startedGames;
    }

    public void setStartedGames(ArrayList<Game> startedGames) {
        this.startedGames = startedGames;
    }
}
