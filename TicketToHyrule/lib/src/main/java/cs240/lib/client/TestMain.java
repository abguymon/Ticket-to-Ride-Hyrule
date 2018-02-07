package cs240.lib.client;


import cs240.lib.communicator.ClientCommunicator;

/**
 * Created by David on 2/7/2018.
 */

public class TestMain {
    public static void main(String[] args){
        ClientCommunicator.SINGLETON.setAuthToken("asdfghjkl");
        ServerProxy.SINGLETON.joinGame("dadams", "game1");
    }
}
