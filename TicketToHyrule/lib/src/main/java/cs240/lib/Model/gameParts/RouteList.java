package cs240.lib.Model.gameParts;

import java.util.ArrayList;
import java.util.HashSet;

import cs240.lib.Model.colors.TrainCardColor;

/**
 * Created by savannah.jane on 3/2/2018.
 */

public class RouteList {
    public static RouteList SINGLETON = new RouteList();

    private ArrayList<Route> routes;
    private RouteList() {
        routes = new ArrayList<>();
        createRoutes();
    }
    public ArrayList<Route> getRoutes(){return routes;}
    private void createRoutes() {
        City kakarikoVillage = new City("Kakariko Village");
        City lonlonRanch = new City("Lon Lon Ranch");
        City goronVillage = new City("Goron Village");
        City lordJabuJabu = new City("Lord Jabu Jabu");
        City hyruleCastle = new City ("Hyrule Castle");
        CityPair kakariko_lonlonRanch = new CityPair(kakarikoVillage, lonlonRanch);
        CityPair goronVillage_lonlonRanch = new CityPair(goronVillage, lonlonRanch);
        CityPair lonlonRanch_lordJabuJabu = new CityPair(lonlonRanch, lordJabuJabu);
        CityPair hyruleCastle_lonlonRanch = new CityPair(hyruleCastle, lonlonRanch);
        Route kakariko_lonlon = new Route(kakariko_lonlonRanch, 4, TrainCardColor.RED); routes.add(kakariko_lonlon);
        Route goron_lonlon = new Route(goronVillage_lonlonRanch, 5, TrainCardColor.YELLOW);
        routes.add(goron_lonlon); routes.add(goron_lonlon);
        Route lonlon_lordjabujabu = new Route(lonlonRanch_lordJabuJabu, 6, TrainCardColor.BLUE);
        routes.add(lonlon_lordjabujabu); routes.add(lonlon_lordjabujabu);
        Route hyrulecastle_lonlon = new Route(hyruleCastle_lonlonRanch, 5, TrainCardColor.GREEN); routes.add(hyrulecastle_lonlon);
    }
}
