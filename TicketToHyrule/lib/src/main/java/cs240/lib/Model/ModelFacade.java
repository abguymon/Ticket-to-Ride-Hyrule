package cs240.lib.Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import cs240.lib.client.Poller;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.Command;
import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.PollerResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by adam on 2/7/18.
 */

public class ModelFacade extends Observable{
    private ArrayList<Game> gameList = new ArrayList<>();
    private User currentUser = null;

    private static ModelFacade instance;
    private ModelFacade(){}
    public static ModelFacade getInstance(){
        if(instance == null){
            instance = new ModelFacade();
        }
        return instance;
    }



    public String createGame(String userName, String gameName, int maxPlayers){
        ModelFacade.getInstance().pollerCheckServer();
        Poller.getInstance().incrementCommandIndex();
        ClientCommunicator.SINGLETON.setAuthToken(currentUser.getPassword());
        CreateResult result = ServerProxy.SINGLETON.createGame(userName, gameName, maxPlayers);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            Game g = new Game(maxPlayers, 0, gameName);
            try{
                g.addPlayer(currentUser);
                gameList.add(g);
                setChanged();
                notifyObservers();
                return "";
            }catch(Exception ex){
                return("EXCEPTION " + ex);
            }
        }
    }

    public String leaveGame(String userName, String gameName){
        ModelFacade.getInstance().pollerCheckServer();
        Poller.getInstance().incrementCommandIndex();
        LeaveResult result = ServerProxy.SINGLETON.leaveGame(userName, gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            Game g = getGame(gameName);
            g.removePlayer(currentUser);
            if (g.getPlayersJoined() == 0) gameList.remove(g);
            setChanged();
            notifyObservers();
            return "";
        }
    }

    public String login(String userName, String password){
        ModelFacade.getInstance().pollerCheckServer();
        Poller.getInstance().incrementCommandIndex();
        SignInResult result = ServerProxy.SINGLETON.login(userName, password);
        if(!result.getErrorMessage().equals("")){
            return result.getErrorMessage();
        }
        else{
            currentUser = new User(userName, result.getAuthToken());
            setChanged();
            notifyObservers();
            return "";
        }
    }
    public String register(String userName, String password){
        ModelFacade.getInstance().pollerCheckServer();
        Poller.getInstance().incrementCommandIndex();
        SignInResult result = ServerProxy.SINGLETON.register(userName, password);
        if(!result.getErrorMessage().equals("")){
            return result.getErrorMessage();
        }
        else{
            currentUser = new User(userName, result.getAuthToken());
            return "";
        }
    }

    public String joinGame(String userName, String gameName){
        ModelFacade.getInstance().pollerCheckServer();
        Poller.getInstance().incrementCommandIndex();
        JoinResult result = ServerProxy.SINGLETON.joinGame(userName,gameName);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                getGame(gameName).addPlayer(currentUser);
                setChanged();
                notifyObservers();
                return "";
            }catch(Exception ex){
                return "EXCEPTION! " + ex;
            }
        }
    }

    public void handleObject( Command myCommand){ // myCommand.getParameters()[0]
        if(myCommand.getMethodName().equals("login")){
            //SignInResult thisResult = (SignInResult)myResult;
            login((String)myCommand.getParameters()[0], (String)myCommand.getParameters()[1]);
        }
        else if(myCommand.getMethodName().equals("register")){
            register((String)myCommand.getParameters()[0], (String)myCommand.getParameters()[1]);
        }
        //else if(myCommand.getMethodName().equals("startGame")){
        //    startGame((String)myCommand.getParameters()[0], (String)myCommand.getParameters()[1]);
        //}
        else if(myCommand.getMethodName().equals("joinGame")){
            joinGame((String)myCommand.getParameters()[0], (String)myCommand.getParameters()[1]);
        }
        else if(myCommand.getMethodName().equals("leaveGame")){
            leaveGame((String)myCommand.getParameters()[0], (String)myCommand.getParameters()[1]);
        }
        else if(myCommand.getMethodName().equals("createGame")){
            createGame((String)myCommand.getParameters()[0], (String)myCommand.getParameters()[1], (int)myCommand.getParameters()[2]);
        }

    }

    public void updateModel(PollerResult result){
        for(int i = Poller.getInstance().getCommandIndex();i < result.getCommands().size(); i++){
            handleObject(result.getCommands().get(i));// (Command)ClientCommunicator.SINGLETON.send((result.getCommands().get(i))),

        }
        Poller.getInstance().setCommandIndex(result.getCommands().size());
    }

    public String pollerCheckServer(){
        PollerResult result = ServerProxy.SINGLETON.pollerCheckServer();
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                //compareServerToClient(result);
                updateModel(result);
                return "";
            }catch(Exception ex){
                return "EXCEPTION! " + ex;
            }
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
