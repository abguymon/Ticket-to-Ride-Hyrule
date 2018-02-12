package cs340.tickettohyrule.Presenters;

import cs240.lib.Model.ModelFacade;
import cs240.lib.communicator.ClientCommunicator;
import cs340.tickettohyrule.CurrentUserSingleton;

/**
 * Created by eholm on 2/7/2018.
 */

public class SignInPresenter {
    ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
    public String login(String userName, String password, String host, String port)
    {
        ClientCommunicator.SINGLETON.setServerHost(host);
        ClientCommunicator.SINGLETON.setServerPort(port);
        return modelFacade.login(userName,password);
    }
    public String register(String userName, String password, String host, String port)
    {
        ClientCommunicator.SINGLETON.setServerHost(host);
        ClientCommunicator.SINGLETON.setServerPort(port);
        return modelFacade.register(userName,password);

    }
}
