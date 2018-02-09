package cs240.lib.client;


import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.communicator.ClientCommunicator;
import cs240.lib.communicator.ServerCommunicator;

/**
 * Created by David on 2/7/2018.
 */

public class TestMain {
    public static void main(String[] args){

        SignInResult result = ServerProxy.SINGLETON.login("davidadams", "game1");
        String authToken = result.getAuthToken();
        if (result.getErrorMessage().equals("")) {
            System.out.println("Success!!!!");
            System.out.println(result.getAuthToken());
        }
        else {
            System.out.println("Failure! Fix Your Code");
            System.out.println(result.getErrorMessage());
        }

        ClientCommunicator.SINGLETON.setAuthToken(authToken);
        CreateResult createResult = ServerProxy.SINGLETON.createGame("davidadams", "grimgor", 4);
        if (createResult.getErrorMessage() == null) {
            System.out.println("Success!!!! You're Amazing");
            System.out.println(createResult.getGameName());
            System.out.println(createResult.getTotalPlayers());
        }
        else {
            System.out.println("Failure! Fix Your Code");
            System.out.println(createResult.getErrorMessage());
        }

        JoinResult joinResult = ServerProxy.SINGLETON.joinGame("davidadams", "grimgor");
        if (joinResult.getErrorMessage() == null) {
            System.out.println(joinResult.getGameName());
            System.out.println(joinResult.getNewPlayerNumber());
            System.out.println("Game joined!");
        }
        else {
            System.out.println("Game not joined");
        }

        joinResult = ServerProxy.SINGLETON.joinGame("davidadams", "grimgor");
        if (joinResult.getErrorMessage() == null) {
            System.out.println(joinResult.getGameName());
            System.out.println(joinResult.getNewPlayerNumber());
            System.out.println("bestgame.01 joined!");
        }
        else {
            System.out.println("Game not joined");
        }
    }
}
