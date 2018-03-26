package cs240.lib.Model.gameParts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import cs240.lib.Model.Game;

/**
 * Created by savannah.jane on 3/25/2018.
 */

public class RouteAdjacencyList {
    private Map<City, ArrayList<Route>> list;
    private ArrayList<Route> routes;
    private ArrayList<City> cities;

    public RouteAdjacencyList() {
        list = new TreeMap<>();
        routes = RouteList.SINGLETON.getRoutes();
        cities = CitiesList.SINGLETON.getCities();
        createList();
    }

    private Map<City, ArrayList<Route>> getList() {return list;}

    private void createList() {
        for (int i = 0; i < cities.size(); ++i) {
            ArrayList<Route> adjacentRoutes = findAdjacentRoutes(cities.get(i).getCityName());
            list.put(cities.get(i), adjacentRoutes);
        }
    }

    private ArrayList<Route> findAdjacentRoutes(String cityName) {
        ArrayList<Route> adjacentRoutes = new ArrayList<>();
        for (int i = 0; i < routes.size(); ++i) {
            String city1Name = routes.get(i).getCity1Name();
            String city2Name = routes.get(i).getCity2Name();
            if (cityName.equals(city1Name) || cityName.equals(city2Name)) {
                adjacentRoutes.add(routes.get(i));
            }
        }
        return adjacentRoutes;
    }

    public void updateList(Game game) {
        cities = game.getMap().getCities();
        routes = game.getMap().getRoutes();
        list.clear();
        createList();
    }
}
