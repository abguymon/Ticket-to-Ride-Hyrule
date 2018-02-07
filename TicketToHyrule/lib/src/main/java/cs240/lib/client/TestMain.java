package cs240.lib.client;


import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by David on 2/7/2018.
 */

public class TestMain {
    public static void main(String[] args){
        SignInResult dadamsRegister = ServerProxy.SINGLETON.register("dadams", "dude");
        System.out.println("dadams: " + dadamsRegister.getAuthToken());
        System.out.println("Error: " + dadamsRegister.getErrorMessage());

        SignInResult badregisterResult = ServerProxy.SINGLETON.register("dadams", "dude");
        System.out.println(badregisterResult.getAuthToken());
        System.out.println(badregisterResult.getErrorMessage());

        SignInResult dudeRegister = ServerProxy.SINGLETON.register("dude", "dude");
        System.out.println(dudeRegister.getAuthToken());
        System.out.println(dudeRegister.getErrorMessage());

        ClientCommunicator.SINGLETON.setAuthToken(dadamsRegister.getAuthToken());
        CreateResult createResult = ServerProxy.SINGLETON.createGame("dadams", "game1", 3);
        System.out.println(createResult.getGameName());
        System.out.println(createResult.getTotalPlayers());
        System.out.println(createResult.getErrorMessage());

        ClientCommunicator.SINGLETON.setAuthToken(dudeRegister.getAuthToken());
        JoinResult joinResult = ServerProxy.SINGLETON.joinGame("dude", "game1");
        System.out.println(joinResult.getGameName());
        System.out.println(joinResult.getNewPlayerNumber());
        System.out.println(joinResult.getErrorMessage());
    }
}
