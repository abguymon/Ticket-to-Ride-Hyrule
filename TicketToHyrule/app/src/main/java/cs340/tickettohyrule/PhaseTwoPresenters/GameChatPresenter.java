package cs340.tickettohyrule.PhaseTwoPresenters;

import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.GameChatFragment;

/**
 * Created by adam on 2/28/18.
 */

public class GameChatPresenter implements Observer {
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private GameChatFragment view = null;

    public void sendMessage(String message){
        modelFacade.sendMessage(message);
    }
    public void updateChat(){

    }

    @Override
    public void update (Observable observable, Object o){
        CurrentUserSingleton.getInstance().getModelFacade().setGames(ClientFacade.getInstance().getGames());
//        getActivity().runOnUiThread(new Runnable(){
//            @Override
//            public void run(){
//                updateUI();
//            }
//        });
    }

    public void setView(GameChatFragment view){
        this.view = view;
    }
}
