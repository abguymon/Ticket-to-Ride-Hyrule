package cs340.tickettohyrule.PhaseTwoPresenters;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.gameParts.Player;
import cs340.tickettohyrule.CurrentUserSingleton;
import cs340.tickettohyrule.Fragments.MapFragment;

/**
 * Created by adam on 3/20/18.
 */

public class EndGamePresenter implements Observer{
        private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
        private ClientFacade clientFacade = ClientFacade.getInstance();
        private MapFragment view = null;
        private int testNumber = 0;
        private boolean claimed = false;

        public List<Player> getPlayers(){
            return modelFacade.getGameData().getPlayerArray();
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
}
