package cs340.tickettohyrule.PhaseTwoPresenters;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

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
import cs240.lib.Model.gameParts.Player;
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
    private Player currentPlayer = modelFacade.getCurrentPlayer();

    public void updateHistory(){

    }
    public ModelFacade getModelFacade(){
        return modelFacade;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void update (Observable observable, Object o){
        modelFacade.updateGameData(ClientFacade.getInstance().getGameData());
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
        ArrayList<TrainCard> playerTC = modelFacade.getCurrentPlayer().getTrainCards();
        int numCards = 0;

        for(TrainCard t :playerTC)
        {
            if (t.getColor().toString().equals(route.getColor().toString()) || t.getColor().toString().equals("WILD"))
            {
                numCards++;
            }
        }

        if(numCards >= route.getLength())
        {
            ClaimRouteAsync claimRouteAsync = new ClaimRouteAsync();
            claimRouteAsync.execute(route);
            return true;
        }
        else
        {
            return false;
        }
            //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
              //      modelFacade.getGameData().getGameName(),route.getColor());

//        modelFacade.getGameData().getRouteById(routeId);
//        ClaimRouteAsync claimRouteAsync = new ClaimRouteAsync();
//        claimRouteAsync.execute(route);
    }

    public ArrayList<Route> getRoutes()
    {
        ArrayList<Route> routes = modelFacade.getGameData().getRoutes();
        return routes;
    }

    public boolean claimGreyRoute(String color)
    {
        switch (color)
        {
            case "BLUE":
                route.setColor(TrainCardColor.BLUE);
                //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                  //      modelFacade.getGameData().getGameName(),TrainCardColor.BLUE);
                break;
            case "BLACK":
                route.setColor(TrainCardColor.BLACK);
                //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                  //      modelFacade.getGameData().getGameName(),TrainCardColor.BLACK);
                break;
            case "WHITE":
                route.setColor(TrainCardColor.WHITE);
                //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                  //      modelFacade.getGameData().getGameName(),TrainCardColor.WHITE);
                break;
            case "YELLOW":
                route.setColor(TrainCardColor.YELLOW);
                //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                  //      modelFacade.getGameData().getGameName(),TrainCardColor.YELLOW);
                break;
            case "ORANGE":
                route.setColor(TrainCardColor.ORANGE);
                //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                  //      modelFacade.getGameData().getGameName(),TrainCardColor.ORANGE);
                break;
            case "RED":
                route.setColor(TrainCardColor.RED);
                //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                  //      modelFacade.getGameData().getGameName(),TrainCardColor.RED);
                break;
            case "PINK":
                route.setColor(TrainCardColor.PINK);
                //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                  //      modelFacade.getGameData().getGameName(),TrainCardColor.PINK);
                break;
            case "GREEN":
                route.setColor(TrainCardColor.GREEN);
                //modelFacade.claimRoute(route, modelFacade.getCurrentPlayer().getPlayerName(),
                  //      modelFacade.getGameData().getGameName(),TrainCardColor.GREEN);
                break;
                default:
                    return false;
        }
        ClaimRouteAsync claimRouteAsync = new ClaimRouteAsync();
        claimRouteAsync.execute(route);
        //route.setOwner(getCurrentPlayer());
        return true;
    }

    public List<String> getColors(){
        ArrayList<String> mColors = new ArrayList<>();

        if(route != null)
        {
            ArrayList<TrainCard> playerTC = modelFacade.getCurrentPlayer().getTrainCards();
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

    public void toastPlayerTurn(){
        String playerName = modelFacade.getCurrentPlayer().getPlayerName();
        view.toast(playerName + "'s Turn");
    }

    private class ClaimRouteAsync extends AsyncTask<Route, Void, String> {
        @Override
        protected String doInBackground(Route... route){
            String result = modelFacade.claimRoute(route[0],
                    modelFacade.getGameData().getGameName(),route[0].getColor());
            return result;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                syncAsync test = new syncAsync();
                test.execute();
                view.toast("Route Claimed");
                //DO WE DO ANYTHING IN HERE? I DON'T THINK SO.. MAYBE JUST TOAST SUCCESS??
            }
            else{
                view.toast(message);
            }
        }
    }
    private class syncAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... card){
            modelFacade.sync();
            return null;
        }
        @Override protected void onPostExecute(Void message){
            view.updateMap();
        }
    }
}
