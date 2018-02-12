package cs340.tickettohyrule;

import cs240.lib.Model.ModelFacade;

/**
 * Created by eholm on 2/7/2018.
 */

public class CurrentUserSingleton {
    private ModelFacade modelFacade = new ModelFacade();
    private String userName;
    private String password;

    private static CurrentUserSingleton instance;
    public CurrentUserSingleton() {}

    public static CurrentUserSingleton getInstance(){
        if(instance == null){
            instance = new CurrentUserSingleton();
        }
        return instance;
    }

    public ModelFacade getModelFacade(){
        return modelFacade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
