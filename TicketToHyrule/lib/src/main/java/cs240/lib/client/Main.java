package client;

import common.results.CreateResult;
import common.results.JoinResult;
import common.results.LeaveResult;
import common.results.SignInResult;
import server.ServerTarget;

/**
 * Created by David on 1/17/2018.
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Login");
        SignInResult loginResult = ServerProxy.SINGLETON.login("david", "adams");
        if (!errorCheck(loginResult.getErrorMessage())){
            System.out.println("AuthToken: " + loginResult.getAuthToken());
        }

        System.out.println("Register");
        SignInResult registerResult = ServerProxy.SINGLETON.register("david", "adams");
        if (!errorCheck(registerResult.getErrorMessage())){
            System.out.println("AuthToken: " + registerResult.getAuthToken());
        }

        System.out.println("Join Game");
        JoinResult joinResult = ServerProxy.SINGLETON.joinGame("david", "game1");
        if (!errorCheck(joinResult.getErrorMessage())){
            System.out.println("Game Name: " + joinResult.getGameName());
            System.out.println("New Player Number: " + joinResult.getNewPlayerNumber());
        }

        System.out.println("Leave Game");
        LeaveResult leaveResult = ServerProxy.SINGLETON.leaveGame("david", "game1");
        if (!errorCheck(leaveResult.getErrorMessage())){
            System.out.println("Game Name: " + leaveResult.getGameName());
            System.out.println("New Player Number: " + leaveResult.getNewPlayerNumber());
        }

        System.out.println("Create Game");
        CreateResult createResult = ServerProxy.SINGLETON.createGame("david", "game1", 5);
        if (!errorCheck(createResult.getErrorMessage())){
            System.out.println("Game Name: " + createResult.getGameName());
            System.out.println("Total Player Number: " + createResult.getTotalPlayers());
        }

        System.out.println("Start Game");
        String result = ServerProxy.SINGLETON.startGame("game1");
        if (result.equals("")){
            System.out.println("Game started");
        }else{
            System.out.println("Error: " + result);
        }
    }

    private static boolean errorCheck(String errorMessage){
        if (!errorMessage.equals("")){
            System.out.println("Error: " + errorMessage);
            return true;
        }else{
            return false;
        }
    }
}
