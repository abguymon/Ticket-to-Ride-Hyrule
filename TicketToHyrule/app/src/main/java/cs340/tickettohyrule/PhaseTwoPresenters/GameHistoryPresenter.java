package cs340.tickettohyrule.PhaseTwoPresenters;

import android.os.AsyncTask;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.GameHistoryFragment;
import cs340.tickettohyrule.GameActivity;

/**
 * Created by adam on 2/28/18.
 */

public class GameHistoryPresenter implements Observer{
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    private GameHistoryFragment view = null;

    public void updateHistory(){
        getHistoryAsync sendMessageAsync = new getHistoryAsync();
        sendMessageAsync.execute();
    }

    public List<String> getHistory()
    {
        return modelFacade.getGameData().getGameHistory();
    }

    @Override
    public void update (Observable observable, Object o){
        CurrentUserSingleton.getInstance().getModelFacade().setGames(ClientFacade.getInstance().getGames());
        updateHistory();
        view.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                view.updateUI();
            }
        });
    }

    public void setView(GameHistoryFragment view){
        this.view = view;
    }

    private class getHistoryAsync extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params){
            return modelFacade.getGameHistory(modelFacade.getGameData().getGameName());
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                //DO WE NEED TO DO ANYTHING??
            }
            else{
                view.toast(message);
            }
        }
    }
}
