package cs340.tickettohyrule.Presenters;

/**
 * Created by eholm on 2/7/2018.
 */

public class SignInPresenter {
    ModelFacade modelFacade = ModelFacade.getInstance();
    public String login(String userName, String password)
    {
        return modelFacade.login(userName,password);
    }
    public String register(String userName, String password)
    {
        return modelFacade.register(userName,password);

    }
}
