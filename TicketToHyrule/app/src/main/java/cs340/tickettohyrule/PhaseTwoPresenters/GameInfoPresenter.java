package cs340.tickettohyrule.PhaseTwoPresenters;

import android.os.AsyncTask;
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
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.Player;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.GameInfoFragment;
import cs340.tickettohyrule.GameActivity;
import cs340.tickettohyrule.R;

import static cs240.lib.Model.colors.TrainCardColor.WILD;

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

    public void drawFaceUpTrainCard(int id){

        DrawFaceUpTrainCardAsync drawFaceUpTrainCardAsync = new DrawFaceUpTrainCardAsync();
        switch(id){
            case(R.id.tCardOne):
                drawFaceUpTrainCardAsync.execute(0);
                break;
            case(R.id.tCardTwo):
                drawFaceUpTrainCardAsync.execute(1);
                break;
            case(R.id.tCardThree):
                drawFaceUpTrainCardAsync.execute(2);
                break;
            case(R.id.tCardFour):
                drawFaceUpTrainCardAsync.execute(3);
                break;
            case(R.id.tCardFive):
                drawFaceUpTrainCardAsync.execute(4);
                break;
        }
    }

    public void drawTrainCard(){
        DrawTrainCardAsync drawTrainCardAsync = new DrawTrainCardAsync();
        drawTrainCardAsync.execute();
    }
    public void drawDestinationCards(){
        DrawDestinationCardsAsync drawDestinationCardsAsync = new DrawDestinationCardsAsync();
        drawDestinationCardsAsync.execute();
    }


    public void setPlayerTrainCards()
    {
        ArrayList<TrainCard> playerTC = getPlayerTCards();
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
        view.setPlayerTrainCards(loco, red, green, orange, blue, black, yellow, pink, white);
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

    private class DrawFaceUpTrainCardAsync extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... card){
            String result;
            if(modelFacade.getGameData().getFaceUpTrainCards().getFaceUpCards()[card[0]].getColor() == TrainCardColor.WILD) {
                result = modelFacade.drawLocomotive(card[0], modelFacade.getCurrentPlayer().getPlayerName(), modelFacade.getGameData().getGameName());
            }
            else {result = modelFacade.drawFaceUpTrainCard(card[0], modelFacade.getCurrentPlayer().getPlayerName() ,modelFacade.getGameData().getGameName());}
            return result;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                view.toast("card drawn!");
            }
            else{
                view.toast(message);
            }
        }
    }

    private class DrawTrainCardAsync extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... card){
            String result = modelFacade.drawTrainCard(modelFacade.getCurrentPlayer().getPlayerName() ,modelFacade.getGameData().getGameName());
            return result;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                view.toast("card drawn!");
            }
            else{
                view.toast(message);
            }
        }
    }
    private class DrawDestinationCardsAsync extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... card){
            String result = modelFacade.drawDestinationCards(modelFacade.getCurrentPlayer().getPlayerName() ,modelFacade.getGameData().getGameName());
            return result;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                //MOVE TO SUBMIT DESTINATION CARDS PAGE WITH INTENT TO ATTACH THE NEW PRESENTER
                ((GameActivity) view.getActivity()).moveToDrawDestinationCards();
            }
            else{
                view.toast(message);
            }
        }
    }
}
