package cs240.lib.common.results;

/**
 * Created by savannah.jane on 2/28/2018.
 */

public class SubmitResult {
    private boolean isSuccessful;
    private String errorMessage;

    public SubmitResult(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public SubmitResult(String errorResult) {
        this.errorMessage = errorResult;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
