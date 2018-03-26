package cs340.tickettohyrule.PhaseTwoPresenters;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
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
import cs240.lib.Model.gameParts.CityPair;
import cs240.lib.Model.gameParts.Route;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.MapFragment;
import cs340.tickettohyrule.GameActivity;

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
        modelFacade.setGameData(ClientFacade.getInstance().getGameData());
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

    public boolean claimRoute(Route route){
        modelFacade.claimRoute(route,modelFacade.getCurrentPlayer(),modelFacade.getGame(),);
//        modelFacade.getGameData().getRouteById(routeId);
//        ClaimRouteAsync claimRouteAsync = new ClaimRouteAsync();
//        claimRouteAsync.execute(route);
        return true;
    }

    public List<String> getColors(){
        return new ArrayList<>();
    }

    public void selectColor(String color){

    }

    private class ClaimRouteAsync extends AsyncTask<Route, Void, String> {
        @Override
        protected String doInBackground(Route... route){
  //          String result = modelFacade.claimRoute(route[0], modelFacade.getCurrentPlayer().getPlayerName() ,modelFacade.getGameData().getGameName());
            return "";
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                //DO WE DO ANYTHING IN HERE? I DONT THINK SO.. MAYBE JUST TOAST SUCCESS??
            }
            else{
                view.toast(message);
            }
        }
    }

}
