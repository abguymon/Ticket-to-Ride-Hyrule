package cs340.tickettohyrule.Model;

public class Login {
    private String username;
    private String authToken;

    public Login() {
        username = "";
        authToken = "";
    }

    public Login(String username, String authToken) {
        this.username = username;
        this.authToken = authToken;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getAuthToken() {return authToken;}
    public void setAuthToken(String authToken) {this.authToken = authToken;}
}