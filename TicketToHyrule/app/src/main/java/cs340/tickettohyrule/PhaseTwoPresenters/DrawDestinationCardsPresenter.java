package cs340.tickettohyrule.PhaseTwoPresenters;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.states.TurnEnded;
import cs240.lib.Model.states.TurnStarted;
import cs240.lib.common.results.GetGameResult;
import cs240.lib.communicator.ClientCommunicator;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.PreStartFragment;
import cs340.tickettohyrule.GameActivity;

/**
 * Created by adam on 3/20/18.
 */

public class DrawDestinationCardsPresenter implements Observer{
    ArrayList<DestinationCard> destinationCards; //THIS WILL EQUAL SOMETHING LIKE modelFacade.getGameData.getDestinationCards();
    ArrayList<DestinationCard> removedDestinationCards = new ArrayList<>();
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private PreStartFragment view = null;

    public DrawDestinationCardsPresenter(){
        destinationCards = new ArrayList<>();
        modelFacade.setGameData(ClientFacade.getInstance().getGameData());
        modelFacade.setCurrentPlayer(modelFacade.getGameData().getPlayer(modelFacade.getCurrentUser().getUsername()));
        destinationCards = modelFacade.getCurrentPlayer().getDrawnDestinationCards();
    }

    public Player getPlayer()
    {
        return modelFacade.getCurrentPlayer();
    }

    public void submit(){
        if(removedDestinationCards.size() <= 2){
//            SEND INFO TO SERVER
            SubmitDestinationCardAsync submitDestinationCardAsync = new SubmitDestinationCardAsync();
            submitDestinationCardAsync.execute();
        }
        else{
            view.toast("Keep at least one cards");
        }
    }

    @Override
    public void update (Observable observable, Object o){
        modelFacade.setGameData(ClientFacade.getInstance().getGameData());
    }

    public void setView(PreStartFragment view){
        this.view = view;
    }


    private class SubmitDestinationCardAsync extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params){

            String gameName = modelFacade.getGameData().getGameName(); //THIS WILL END UP EQUALING SOMETHING LIKE modelFacade.getGameData.getGameName();
            String message = modelFacade.chooseDestinationCards(getPlayer().getPlayerName(), gameName, removedDestinationCards);


            return message;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                syncGame();
                ((GameActivity)view.getActivity()).moveToMap();
            }
            else{
                view.toast(message);
            }
        }
    }
    public ArrayList<DestinationCard> getDestinationCards(){return destinationCards;}
    public ArrayList<DestinationCard> getRemovedDestinationCards(){return removedDestinationCards;}

    public void syncGame(){new syncAsync().execute();}

    private class syncAsync extends AsyncTask<Void, Void, GetGameResult> {
        @Override
        protected GetGameResult doInBackground(Void... card){
            return modelFacade.sync();
        }
        @Override protected void onPostExecute(GetGameResult message){
            modelFacade.setCurrentPlayer(modelFacade.getGameData().getPlayer(modelFacade.getCurrentUser().getUsername()));
            destinationCards = modelFacade.getCurrentPlayer().getDrawnDestinationCards();
            view.setText();
        }
    }
}
