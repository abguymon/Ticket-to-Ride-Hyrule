package cs340.tickettohyrule.PhaseTwoPresenters;

import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.MapFragment;

/**
 * Created by eholm on 3/5/2018.
 */

public class MapPresenter implements Observer {
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private MapFragment view = null;
    private int testNumber = 0;

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

    public void setView(MapFragment view){
        this.view = view;
    }

    //test button functions
    public void runTest(){
        switch(testNumber){
            case 0:
                updatePlayerPoints();
                break;
            case 1:
                addTrainCards();
                break;
            case 2:
                addDestinationCards();
                break;
            case 3:
                updateTrainCards();
                break;
            case 4:
                updateDestinationCards();
                break;
            case 5:
                updateTrainCardDeck();
                break;
            case 6:
                updateDestinationCardDeck();
                break;
            case 7:
                claimRoute();
                break;
            case 8:
                chat();
                break;
            case 9:
                addToGameHistory();
                break;
        }
        if (testNumber > 9) {
            testNumber++;
        }else if (testNumber == 9){
            testNumber = 0;
        }
    }

    private void addToGameHistory() {

    }

    private void chat() {

    }

    private void claimRoute() {

    }

    private void updateDestinationCardDeck() {

    }

    private void updateTrainCardDeck() {

    }

    private void updateDestinationCards() {

    }

    private void updateTrainCards() {

    }

    private void addDestinationCards() {

    }
    private void addTrainCards() {

    }
    private void updatePlayerPoints(){

    }
}
