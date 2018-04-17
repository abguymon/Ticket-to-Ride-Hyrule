package cs240.lib.common;

import java.util.Map;
import java.util.TreeMap;

import cs240.lib.Model.states.DrawnFirstCard;
import cs240.lib.Model.states.IState;
import cs240.lib.Model.states.TurnEnded;
import cs240.lib.Model.states.TurnStarted;

/**
 * Created by savannah.jane on 4/16/2018.
 */

public class StateMap {
    public static final StateMap SINGLETON = new StateMap();
    private Map<Integer, IState> stateMap;

    private StateMap() {
        stateMap = new TreeMap<>();
        loadStateMap();
    }

    private void loadStateMap() {
        stateMap.put(0, new TurnEnded());
        stateMap.put(1, new TurnStarted());
        stateMap.put(2, new DrawnFirstCard());
    }
}
