package cs340.tickettohyrule.PhaseTwoPresenters;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.GameInfoFragment;

/**
 * Created by adam on 2/28/18.
 */

public class GameInfoPresenter implements Observer{
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private GameInfoFragment view = null;
    private ArrayList<DestinationCard> destinationCards = modelFacade.getGameData().getPlayer(modelFacade.getCurrentUser().getUsername()).getDestinationCards();
    public void updateInfo(){

    }

    public ArrayList<DestinationCard> getDestinationCards(){
        return destinationCards;
    }

    @Override
    public void update (Observable observable, Object o){
        CurrentUserSingleton.getInstance().getModelFacade().setGameData(ClientFacade.getInstance().getGameData());
        view.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                view.updateUI();
            }
        });
    }

    public void setView(GameInfoFragment view){
        this.view = view;
    }
}
