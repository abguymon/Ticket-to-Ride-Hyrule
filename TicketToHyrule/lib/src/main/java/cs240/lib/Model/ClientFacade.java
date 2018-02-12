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
        if(instance == null){
            instance = new ClientFacade();
            Thread thread = new Thread(Poller.getInstance());
            thread.start();
            //Poller.getInstance().run();
        }
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
            if(g.getPlayersJoined() == g.getMaxPlayers()) gameList.remove(g);
            setChanged();
            notifyObservers(gameList);
            return true;
        }catch(Exception ex){
            System.out.println("EXCEPTION "+ex);
            return false;
        }
    }
    public void leaveGame(String userName, String gameName){
        //System.out.println("we got here");
        Game g = getGame(gameName);
        g.removePlayer(userName);
        if(g.getPlayersJoined() == 0) gameList.remove(g);
        setChanged();
        notifyObservers(gameList);
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
            joinGame((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                    (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
        }
        else if(myCommand.getMethodName().equals("leaveGame")){
            //System.out.println("leave game called");
            leaveGame((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                    (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1));
        }
        else if(myCommand.getMethodName().equals("createGame")){
            //System.out.println("create game called");
            createGame((String)myCommand.getParametersAsJsonStrings()[0].substring(1,myCommand.getParametersAsJsonStrings()[0].length()-1),
                    (String)myCommand.getParametersAsJsonStrings()[1].substring(1,myCommand.getParametersAsJsonStrings()[1].length()-1),
                    Integer.parseInt(myCommand.getParametersAsJsonStrings()[2]));
        }

    }

    public void updateModel(PollerResult result){

        while(!result.getCommands().isEmpty())
        {
           // System.out.println("parameter " +  result.getCommands().peek().toString());
            for(Game g: gameList)
            {
                System.out.println(g.toString());
            }
            handleObject(result.getCommands().remove());
        }
//        for(int i = Poller.getInstance().getCommandIndex(); i < result.getCommands().size(); i++){
//            System.out.println("update model called for index " +  result.getCommands().size());
//            handleObject(result.getCommands().get(i));// (Command)ClientCommunicator.SINGLETON.send((result.getCommands().get(i))),
//        }
//        Poller.getInstance().setCommandIndex(result.getCommands().size());
    }

    public String pollerCheckServer(int index){
        PollerResult result = ServerProxy.SINGLETON.pollerCheckServer(index);
        if(result.getErrorMessage() != null){
            return result.getErrorMessage();
        }
        else{
            try{
                //compareServerToClient(result);
                updateModel(result);
                Poller.getInstance().setCommandIndex(result.getCommands().size() + Poller.getInstance().getCommandIndex());
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
}
