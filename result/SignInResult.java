package result;

public class SignInResult {
    private String authToken;
    private String errorMessage;

    public SignInResult() {}

    public SignInResult(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {return authToken;}
    public void setAuthToken(String authToken) {this.authToken = authToken;}

    public String getErrorMessage() {return errorMessage;}
    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}
}
