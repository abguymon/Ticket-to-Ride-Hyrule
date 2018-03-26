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
    private Route route = null;

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

    public void selectRoute(Route route)
    {
        this.route = route;
    }

    public boolean claimRoute(){
            modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                    modelFacade.getGameData().getGameName(),route.getColor());

//        modelFacade.getGameData().getRouteById(routeId);
//        ClaimRouteAsync claimRouteAsync = new ClaimRouteAsync();
//        claimRouteAsync.execute(route);
        return true;
    }

    public boolean claimGreyRoute(String color)
    {
        switch (color)
        {
            case "BLUE":
                modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                        modelFacade.getGameData().getGameName(),TrainCardColor.BLUE);
                break;
            case "BLACK":
                modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                        modelFacade.getGameData().getGameName(),TrainCardColor.BLACK);
                break;
            case "WHITE":
                modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                        modelFacade.getGameData().getGameName(),TrainCardColor.WHITE);
                break;
            case "YELLOW":
                modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                        modelFacade.getGameData().getGameName(),TrainCardColor.YELLOW);
                break;
            case "ORANGE":
                modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                        modelFacade.getGameData().getGameName(),TrainCardColor.ORANGE);
                break;
            case "RED":
                modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                        modelFacade.getGameData().getGameName(),TrainCardColor.RED);
                break;
            case "PINK":
                modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                        modelFacade.getGameData().getGameName(),TrainCardColor.PINK);
                break;
            case "GREEN":
                modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                        modelFacade.getGameData().getGameName(),TrainCardColor.GREEN);
                break;
                default:
                    return false;
        }

        return true;
    }

    public List<String> getColors(){
        ArrayList<String> mColors = new ArrayList<>();

        if(!route.equals(null))
        {
            ArrayList<TrainCard> playerTC = route.getOwner().getTrainCards();
            int loco = 0;
            int red = 0;
            int green = 0;
            int orange = 0;
            int blue = 0;
            int black = 0;
            int yellow = 0;
            int pink = 0;
            int white = 0;
            for(TrainCard t :playerTC)
            {
                switch (t.getColor().toString())
                {
                    case "GREEN":
                        green++;
                        break;
                    case "RED":
                        red++;
                        break;
                    case "BLACK":
                        black++;
                        break;
                    case "YELLOW":
                        yellow++;
                        break;
                    case "PINK":
                        pink++;
                        break;
                    case "ORANGE":
                        orange++;
                        break;
                    case "WHITE":
                        white++;
                        break;
                    case "BLUE":
                        blue++;
                        break;
                    case "WILD":
                        loco++;
                        break;
                    default:
                        loco++;
                        break;
                }
            }
            if((red+loco) >= route.getLength())
            {
                mColors.add("RED");
            }
            if((blue+loco) >= route.getLength())
            {
                mColors.add("BLUE");
            }
            if((black+loco) >= route.getLength())
            {
                mColors.add("BLACK");
            }
            if((yellow+loco) >= route.getLength())
            {
                mColors.add("YELLOW");
            }
            if((green+loco) >= route.getLength())
            {
                mColors.add("GREEN");
            }
            if((orange+loco) >= route.getLength())
            {
                mColors.add("ORANGE");
            }
            if((white+loco) >= route.getLength())
            {
                mColors.add("WHITE");
            }
            if((pink+loco) >= route.getLength())
            {
                mColors.add("PINK");
            }

            return mColors;
        }
        else
        {
            return new ArrayList<>();
        }
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
