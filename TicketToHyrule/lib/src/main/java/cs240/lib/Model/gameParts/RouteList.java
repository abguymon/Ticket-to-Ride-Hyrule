package cs240.lib.Model.gameParts;

import java.util.HashSet;

/**
 * Created by savannah.jane on 3/2/2018.
 */

public class RouteList {
    public static RouteList SINGLETON = new RouteList();

    private HashSet<Route> routes;
    private RouteList() {
        routes = new HashSet<>();
        createRoutes();
    }
    public HashSet<Route> getRoutes(){return routes;}
    private void createRoutes() {
        //Hard Code Routes Here
    }
}
