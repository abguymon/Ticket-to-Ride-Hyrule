package cs340.tickettohyrule.PhaseOnePresenters;

import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.CurrentUserSingleton;

/**
 * Created by eholm on 2/7/2018.
 */

public class CreateGamePresenter {
    ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();

    public String createGame(String userName, String gameName, int maxPlayers)
    {
       return modelFacade.createGame(userName, gameName, maxPlayers);
    }
}
