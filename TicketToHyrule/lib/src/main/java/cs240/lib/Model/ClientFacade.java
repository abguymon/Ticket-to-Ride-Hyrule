package cs240.lib.Model;

import java.util.ArrayList;
import java.util.Observable;

import cs240.lib.client.Poller;
import cs240.lib.client.ServerProxy;
import cs240.lib.common.Command;
import cs240.lib.common.results.PollerResult;

/**
 * Created by adam on 2/12/18.
 */

public class ClientFacade extends Observable{
    private ArrayList<Game> gameList = new ArrayList<>();
    private static ClientFacade instance = null;

    private ClientFacade(){}

    public static ClientFacade getInstance(){
        if(instance == null) instance = new ClientFacade();
        return instance;
    }

    public void createGame(String userName, String gameName, int maxPlayers){
        Game g = new Game(maxPlayers, 0, gameName);
        gameList.add(g);
        joinGame(userName, gameName);
    }

    public boolean joinGame(String userName, String gameName){
        Game g = getGame(gameName);
        try {
            g.addPlayer(userName);
            setChanged();
            notifyObservers();
            return true;
        }catch(Exception ex){
            System.out.println("EXCEPTION "+ex);
            return false;
        }
    }
    public void leaveGame(String userName, String gameName){
        Game g = getGame(gameName);
        g.removePlayer(userName);
        setChanged();
        notifyObservers();
    }

    public void handleObject( Command myCommand){ // myCommand.getParameters()[0]
        if(myCommand.getMethodName().equals("login")){
            //SignInResult thisResult = (SignInResult)myResult;
            //login((String)myCommand.getParameters()[0], (String)myCommand.getParameters()[1]);
            return;
        }
        else if(myCommand.getMethodName().equals("register")){
            //register((String)myCommand.getParameters()[0], (String)myCommand.getParameters()[1]);
            return;
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
        for(int i = Poller.getInstance().getCommandIndex(); i < result.getCommands().size(); i++){
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
}
