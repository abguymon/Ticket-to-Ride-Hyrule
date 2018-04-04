package cs240.lib.common.results;

/**
 * Created by David on 1/31/2018.
 */

public class SignInResult {
    private String authToken;
    private String errorMessage;

    public SignInResult(){}
    public SignInResult(String authToken, String errorMessage){
        this.authToken = authToken;
        this.errorMessage = errorMessage;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
