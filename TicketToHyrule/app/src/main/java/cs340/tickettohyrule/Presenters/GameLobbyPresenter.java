package cs340.tickettohyrule.Presenters;

/**
 * Created by eholm on 2/7/2018.
 */

public class GameLobbyPresenter {
    ModelFacade modelFacade = ModelFacade.getInstance();

    public String joinGame(String userName, String gameName)
    {
        return modelFacade.joinGame(userName, gameName);
    }

    public String leaveGame(String userName, String gameName)
    {
        return modelFacade.leaveGame(userName, gameName);
    }
}
