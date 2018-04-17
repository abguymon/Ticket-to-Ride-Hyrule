package cs240.lib.common.results;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;

import cs240.lib.common.Command;

/**
 * Created by chees on 2/9/2018.
 */

public class PollerResult {
    Queue<Command> myCommands;
    private String errorMessage;
    private int commandHistory = -1;

    public PollerResult() {}

    public PollerResult(Queue<Command> newCommands) {
        this.myCommands = newCommands;
    }
    public PollerResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Queue<Command> getCommands() {
        return myCommands;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCommandHistory() {
        return commandHistory;
    }

    public void setCommandHistory(int commandHistory) {
        this.commandHistory = commandHistory;
    }
}
