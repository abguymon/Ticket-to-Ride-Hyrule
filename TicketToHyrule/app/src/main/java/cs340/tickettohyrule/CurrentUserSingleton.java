package cs340.tickettohyrule;

/**
 * Created by eholm on 2/7/2018.
 */

public class CurrentUserSingleton {
    private String userName;
    private String authToken;

    private static CurrentUserSingleton instance;
    public CurrentUserSingleton() {}

    public static CurrentUserSingleton getInstance(){
        if(instance == null){
            instance = new CurrentUserSingleton();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
