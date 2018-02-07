package cs240.lib.client;


import cs240.lib.common.results.CreateResult;
import cs240.lib.common.results.JoinResult;
import cs240.lib.common.results.LeaveResult;
import cs240.lib.common.results.SignInResult;
import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by David on 2/7/2018.
 */

public class TestMain {
    public static void main(String[] args){
        SignInResult dadamsRegister = ServerProxy.SINGLETON.register("dadams", "dude");
        System.out.println("dadams authToken: " + dadamsRegister.getAuthToken());
        System.out.println("Error: " + dadamsRegister.getErrorMessage());

        SignInResult badregisterResult = ServerProxy.SINGLETON.register("dadams", "dude");
        System.out.println("bad authToken: " + badregisterResult.getAuthToken());
        System.out.println("Error: " + badregisterResult.getErrorMessage());

        SignInResult dudeRegister = ServerProxy.SINGLETON.register("dude", "dude");
        System.out.println("dude authToken: " + dudeRegister.getAuthToken());
        System.out.println("Error: " + dudeRegister.getErrorMessage());

        ClientCommunicator.SINGLETON.setAuthToken(dadamsRegister.getAuthToken());
        CreateResult createResult = ServerProxy.SINGLETON.createGame("dadams", "game1", 3);
        System.out.println("creation: " + createResult.getGameName());
        System.out.println("players: " + createResult.getTotalPlayers());
        System.out.println("Error: " + createResult.getErrorMessage());

        ClientCommunicator.SINGLETON.setAuthToken(dudeRegister.getAuthToken());
        JoinResult joinResult = ServerProxy.SINGLETON.joinGame("dude", "game1");
        System.out.println("joined: " + joinResult.getGameName());
        System.out.println("players: " + joinResult.getNewPlayerNumber());
        System.out.println("Error: " + joinResult.getErrorMessage());

        ClientCommunicator.SINGLETON.setAuthToken(dudeRegister.getAuthToken());
        LeaveResult leaveResult = ServerProxy.SINGLETON.leaveGame("dude", "game1");
        System.out.println("left: " + leaveResult.getGameName());
        System.out.println("players: " + leaveResult.getNewPlayerNumber());
        System.out.println("Error: " + leaveResult.getErrorMessage());
    }
}
