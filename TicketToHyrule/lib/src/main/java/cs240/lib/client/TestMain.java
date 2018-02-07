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
        System.out.println(result.getAuthToken());
        String authToken = result.getAuthToken();
        if (result.getErrorMessage().equals("")) {
            System.out.println("Success!!!!");
        }
        else {
            System.out.println("Failure! Fix Your Code");
            System.out.println(result.getErrorMessage());
        }

        ClientCommunicator.SINGLETON.setAuthToken(authToken);
        CreateResult createResult = ServerProxy.SINGLETON.createGame("davidadams", "bestgame2", 4);
        System.out.println(createResult.getGameName());
        System.out.println(createResult.getTotalPlayers());
        if (createResult.getErrorMessage().equals("")) {
            System.out.println("Success!!!! You're Amazing");
        }
        else {
            System.out.println("Failure! Fix Your Code");
            System.out.println(createResult.getErrorMessage());
        }
    }
}
