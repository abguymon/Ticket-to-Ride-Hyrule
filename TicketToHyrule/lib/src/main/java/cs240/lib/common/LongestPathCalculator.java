package cs240.lib.common;

import cs240.lib.Model.Game;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.RouteAdjacencyList;

/**
 * Created by savannah.jane on 3/25/2018.
 */

public class LongestPathCalculator {
    public static final LongestPathCalculator SINGLETON = new LongestPathCalculator();


    public void calculate(Game game) {
        RouteAdjacencyList adjacencyList = new RouteAdjacencyList();
        adjacencyList.updateList(game);


    }
}
