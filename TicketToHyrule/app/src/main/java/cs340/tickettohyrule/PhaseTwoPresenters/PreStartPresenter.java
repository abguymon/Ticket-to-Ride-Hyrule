package cs340.tickettohyrule.PhaseTwoPresenters;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.PreStartFragment;

/**
 * Created by adam on 2/28/18.
 */

public class PreStartPresenter implements Observer{
    ArrayList<DestinationCard> destinationCards; //THIS WILL EQUAL SOMETHING LIKE modelFacade.getGameData.getDestinationCards();
    ArrayList<DestinationCard> removedDestinationCards;
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private PreStartFragment view = null;

    public void submit(){
        if(removedDestinationCards.size() <= 1){
            //SEND INFO TO SERVER
            SubmitDestinationCardAsync submitDestinationCardAsync = new SubmitDestinationCardAsync();
            submitDestinationCardAsync.execute();
        }
        else{
            view.toast("Keep at least two cards");
        }
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

    public void setView(PreStartFragment view){
        this.view = view;
    }


    private class SubmitDestinationCardAsync extends AsyncTask<Void, Void, String> {
        ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
        @Override
        protected String doInBackground(Void... params){
            DestinationCard card = null;
            if(removedDestinationCards.size() > 0){
                card = removedDestinationCards.get(0);
            }
            String gameName = ""; //THIS WILL END UP EQUALING SOMETHING LIKE modelFacade.getGameData.getGameName();
            String message = modelFacade.submitDestinationCard(gameName, card);

            return message;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                inGameSingleton.setInGame(true);
                Toast.makeText(getActivity(), "Successfully joined game", Toast.LENGTH_SHORT).show();
                createButton.setEnabled(false);
                joinButton.setEnabled(false);
                inGameSingleton.setGameImIn(currentGame);
            }
            else{
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public ArrayList<DestinationCard> getDestinationCards(){return destinationCards;}
    public ArrayList<DestinationCard> getRemovedDestinationCards(){return removedDestinationCards;}
}
