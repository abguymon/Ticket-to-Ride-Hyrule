package cs240.lib.common.results;

/**
 * Created by David on 3/19/2018.
 */

public class ClaimRouteResult {
    private String errorMessage;
    private boolean isFinalRound;

    public ClaimRouteResult(boolean isFinalRound){this.isFinalRound = isFinalRound;}
    public ClaimRouteResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
