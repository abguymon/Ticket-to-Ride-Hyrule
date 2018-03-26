package cs240.lib.common;

import cs240.lib.Model.gameParts.Route;

/**
 * Created by savannah.jane on 3/25/2018.
 */

public class Edge {
    private boolean isVisited;
    private Route route;

    public Edge(Route route) {
        this.route = route;
        this.isVisited = false;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
