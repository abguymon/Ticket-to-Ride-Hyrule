package cs340.tickettohyrule.PhaseTwoPresenters;

import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ChatEntry;
import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.Game;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.cards.DestCardsList;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.DestinationCardDeck;
import cs240.lib.Model.cards.FaceUpTrainCards;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.cards.TrainCardDeck;
import cs240.lib.Model.colors.TrainCardColor;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.MapFragment;

/**
 * Created by eholm on 3/5/2018.
 */

public class MapPresenter implements Observer {
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private ClientFacade clientFacade = ClientFacade.getInstance();
    private MapFragment view = null;
    private int testNumber = 0;
    private boolean claimed = false;

    public void updateHistory(){

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

    public void setView(MapFragment view){
        this.view = view;
    }

    //test button functions
    public String runTest(){
        String testRun = "No test running";
        Game temp = ClientFacade.getInstance().getGameData();
        switch(testNumber){
            case 0:
                clientFacade.drawTrainCard(modelFacade.getCurrentPlayer().getPlayerName());
                testRun = "Adding Train Cards";
                break;
            case 1:
                clientFacade.drawDestinationCard(modelFacade.getCurrentPlayer().getPlayerName());
                testRun = "Adding Destination Cards";
                break;
            case 2:
                clientFacade.drawDestinationCard(modelFacade.getGameData().getPlayerArray().get(1).getPlayerName());
                testRun = "Updating Destination Cards";
                break;
            case 3:
                clientFacade.drawTrainCard(modelFacade.getGameData().getPlayerArray().get(1).getPlayerName());
                testRun = "Updating Train Card Deck";
                break;
            case 4:
                clientFacade.claimRoute(modelFacade.getCurrentPlayer(), 0);
                claimed = true;
                testRun = "Claiming Route";
                break;
            case 5:
                clientFacade.sendMessage(modelFacade.getGameData().getPlayerArray().get(1).getPlayerName(), "This is a student");
                testRun = "Sending chat";
                break;
            case 6:
                addToGameHistory(temp);
                clientFacade.addToGameHistory("test");
                testRun = "Adding Game History Entry";
                break;
        }
        ClientFacade.getInstance().setGameData(temp);
        if (testNumber < 6) {
            testNumber++;
        }else if (testNumber == 7){
            testNumber = 0;
        }
        return testRun;
    }

    public boolean isClaimed(){
        return claimed;
    }

    private void addToGameHistory(Game temp) {
        temp.getGameHistory().add("Test");
    }

}
