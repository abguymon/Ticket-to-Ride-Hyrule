package cs340.tickettohyrule.PhaseTwoPresenters;

import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.CurrentUserSingleton;

/**
 * Created by adam on 2/28/18.
 */

public class GameChatPresenter {
    private ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    public void sendMessage(String message){

        return modelFacade.sendMessage(message);
    }
}
