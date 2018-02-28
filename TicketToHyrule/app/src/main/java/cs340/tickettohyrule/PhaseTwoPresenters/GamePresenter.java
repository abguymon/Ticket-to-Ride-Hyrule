package cs340.tickettohyrule.PhaseTwoPresenters;

import java.util.Observable;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.GameActivity;

/**
 * Created by adam on 2/28/18.
 */

public class GamePresenter {
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private GameActivity view = null;

    public void updateHistory(){

    }

    @Override
    public void update (Observable observable, Object o){
        CurrentUserSingleton.getInstance().getModelFacade().setGames(ClientFacade.getInstance().getGames());
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                updateUI();
            }
        });
    }

    public void setView(GameActivity view){
        this.view = view;
    }
}
