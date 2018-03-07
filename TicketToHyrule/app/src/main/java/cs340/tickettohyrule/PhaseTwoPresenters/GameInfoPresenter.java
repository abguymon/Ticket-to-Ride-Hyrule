package cs340.tickettohyrule.PhaseTwoPresenters;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.gameParts.Player;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.GameInfoFragment;

/**
 * Created by adam on 2/28/18.
 */

public class GameInfoPresenter implements Observer{
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private GameInfoFragment view = null;
    private ArrayList<DestinationCard> destinationCards = modelFacade.getGameData().getPlayer(modelFacade.getCurrentUser().getUsername()).getDestinationCards();
    private ArrayList<Player> players = modelFacade.getGameData().getPlayerArray();
    public void updateInfo(){

    }

    public ArrayList<DestinationCard> getDestinationCards(){
        return destinationCards;
    }
    public ArrayList<Player> getPlayers() {return players;}
    public TrainCard[] getFaceUpTrainCards()
    {return modelFacade.getGameData().getFaceUpTrainCards().getFaceUpCards();}
    public int getnumCardsInDDeck() {return modelFacade.getGameData().getDestinationCardDeck().getSize();}
    public int getNumCardsInTDeck() {return modelFacade.getGameData().getTrainCardDeck().getSize();}
    public ArrayList<TrainCard> getPlayerTCards() {return modelFacade.getCurrentPlayer().getTrainCards();}

    @Override
    public void update (Observable observable, Object o){
        CurrentUserSingleton.getInstance().getModelFacade().setGameData(ClientFacade.getInstance().getGameData());
        view.getActivity().runOnUiThread(new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
