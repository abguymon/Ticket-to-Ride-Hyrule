package cs240.lib.common.results;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cs240.lib.common.Command;

/**
 * Created by chees on 2/9/2018.
 */

public class PollerResult {
    ArrayList<Command> myCommands;
    private String errorMessage;

    public PollerResult() {}

    public PollerResult(ArrayList<Command> newCommands) {
        this.myCommands = newCommands;
    }
    public PollerResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<Command> getCommands() {
        return myCommands;
    }

    public void setCommands(ArrayList<Command> newCommands) {
        this.myCommands = newCommands;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
