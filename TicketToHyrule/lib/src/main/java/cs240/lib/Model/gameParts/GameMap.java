package cs240.lib.Model.gameParts;

import java.util.ArrayList;

/**
 * Created by David on 2/21/2018.
 */

public class GameMap {
    private ArrayList<Route> routes;
    private ArrayList<City> cities;

    public GameMap(ArrayList<Route> routes, ArrayList<City> cities) {
        this.routes = routes;
        this.cities = cities;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}
