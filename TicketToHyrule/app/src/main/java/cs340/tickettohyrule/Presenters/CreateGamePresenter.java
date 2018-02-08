package cs340.tickettohyrule.Presenters;

import cs240.lib.Model.ModelFacade;

/**
 * Created by eholm on 2/7/2018.
 */

public class CreateGamePresenter {
    ModelFacade modelFacade = ModelFacade.getInstance();

    public String createGame(String userName, String gameName, int maxPlayers)
    {
       return modelFacade.createGame(userName, gameName, maxPlayers);
    }
}
