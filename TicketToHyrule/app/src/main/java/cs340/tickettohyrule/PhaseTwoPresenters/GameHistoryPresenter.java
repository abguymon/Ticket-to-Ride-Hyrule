package cs340.tickettohyrule.PhaseTwoPresenters;

import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.GameHistoryFragment;

/**
 * Created by adam on 2/28/18.
 */

public class GameHistoryPresenter implements Observer{
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private GameHistoryFragment view = null;

    public void updateHistory(){

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

    public void setView(GameHistoryFragment view){
        this.view = view;
    }
}
