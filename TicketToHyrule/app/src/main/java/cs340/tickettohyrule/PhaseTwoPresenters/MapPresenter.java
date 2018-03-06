package cs340.tickettohyrule.PhaseTwoPresenters;

import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ChatEntry;
import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.Game;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.cards.DestCardsList;
import cs240.lib.Model.cards.DestinationCardDeck;
import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.colors.TrainCardColor;
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
    public String runTest(){
        String testRun = "No test running";
        Game temp = ClientFacade.getInstance().getGameData();
        switch(testNumber){
            case 0:
                updatePlayerPoints(temp);
                testRun = "Updating Player Points";
                break;
            case 1:
                addTrainCards(temp);
                testRun = "Adding Train Cards";
                break;
            case 2:
                minusTrainCars(temp);
                testRun = "Removing Train Cars";
                break;
            case 3:
                addDestinationCards(temp);
                testRun = "Adding Destination Cards";
                break;
            case 4:
                updateTrainCards(temp);
                testRun = "Updating Train Cards";
                break;
            case 5:
                updateDestinationCards(temp);
                testRun = "Updating Destination Cards";
                break;
            case 6:
                updateTrainCardDeck(temp);
                testRun = "Updating Train Card Deck";
                break;
            case 7:
                updateDestinationCardDeck(temp);
                testRun = "Updating Destination Card Deck";
                break;
            case 8:
                claimRoute(temp);
                testRun = "Claiming Route";
                break;
            case 9:
                chat(temp);
                testRun = "Sending chat";
                break;
            case 10:
                addToGameHistory(temp);
                testRun = "Adding Game History Entry";
                break;
        }
        ClientFacade.getInstance().setGameData(temp);
        if (testNumber < 10) {
            testNumber++;
        }else if (testNumber == 10){
            testNumber = 0;
        }
        return testRun;
    }

    private void addToGameHistory(Game temp) {
        temp.getGameHistory().add("Test");
    }

    private void chat(Game temp) {
        temp.getChatHistory().add(new ChatEntry(temp.getPlayerArray().get(0).getPlayerName(), "This is a test"));
    }

    private void claimRoute(Game temp) {
        temp.getMap().getRoutes().get(0).claim(temp.getPlayerArray().get(0));
    }

    private void updateDestinationCardDeck(Game temp) {

    }

    private void updateTrainCardDeck(Game temp) {

    }

    private void updateDestinationCards(Game temp) {
        DestinationCardDeck deck = temp.getDestinationCardDeck();
        temp.getPlayerArray().get(1).addDestinationCard(deck.draw());
    }

    private void updateTrainCards(Game temp) {
        temp.getPlayerArray().get(1).addTrainCard(new TrainCard(TrainCardColor.GREEN));
    }

    private void addDestinationCards(Game temp) {
        DestinationCardDeck deck = temp.getDestinationCardDeck();
        temp.getPlayerArray().get(0).addDestinationCard(deck.draw());
    }
    private void minusTrainCars(Game temp) {
        temp.getPlayerArray().get(0).minusTrains(5);
    }
    private void addTrainCards(Game temp) {
        temp.getPlayerArray().get(0).addTrainCard(new TrainCard(TrainCardColor.BLUE));
    }
    private void updatePlayerPoints(Game temp){
        temp.getPlayerArray().get(0).addScore(5);
    }
}
