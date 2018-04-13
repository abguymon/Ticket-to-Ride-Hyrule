package cs340.tickettohyrule.PhaseOnePresenters;

import java.util.ArrayList;
import java.util.Queue;

import cs240.lib.Model.LobbyGame;
import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.CurrentUserSingleton;

/**
 * Created by eholm on 2/7/2018.
 */

public class GameLobbyPresenter {
    ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();

    public String joinGame(String userName, String gameName)
    {
        return modelFacade.joinGame(userName, gameName);
    }

    public String leaveGame(String userName, String gameName)
    {
        return modelFacade.leaveGame(userName, gameName);
    }
    public Object getGame(String gameName){
        return modelFacade.getGameData(gameName);
    }

}
