package cs240.lib.Model.gameParts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
        routes.add(createRoute("Kakariko Village", "Lon Lon Ranch",3, TrainCardColor.GREY));
        routes.add(createRoute("Goron Village", "Lon Lon Ranch", 2, TrainCardColor.GREY));
        routes.add(createRoute("Goron Village", "Lon Lon Ranch", 2, TrainCardColor.GREY));
        routes.add(createRoute("Hyrule Castle", "Lon Lon Ranch", 3, TrainCardColor.YELLOW));
        routes.add(createRoute("Lon Lon Ranch", "Lord Jabu Jabu", 2, TrainCardColor.WHITE));
        routes.add(createRoute("Lon Lon Ranch", "Lord Jabu Jabu", 2, TrainCardColor.GREEN));
        routes.add(createRoute("Kakariko Village", "Temple of Light", 2 , TrainCardColor.GREEN));
        routes.add(createRoute("Kakariko Village", "Temple of Light", 2 , TrainCardColor.WHITE));
        routes.add(createRoute("Kakariko Village", "Tarrey Town", 4, TrainCardColor.BLUE));
        routes.add(createRoute("City in the Sky", "Kakariko Village", 4, TrainCardColor.WHITE));
        routes.add(createRoute("City in the Sky", "Lord Jabu Jabu", 2, TrainCardColor.GREY));
        routes.add(createRoute("Hateno Village", "Lord Jabu Jabu", 2, TrainCardColor.ORANGE));
        routes.add(createRoute("Hateno Village", "Lord Jabu Jabu", 2, TrainCardColor.BLACK));
        routes.add(createRoute("City in the Sky", "Hateno Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Hateno Village", "Ice Cavern", 2, TrainCardColor.GREY));
        routes.add(createRoute("Hateno Village", "Ice Cavern", 2, TrainCardColor.GREY));
        routes.add(createRoute("City in the Sky", "Ice Cavern", 3, TrainCardColor.BLUE));
        routes.add(createRoute("Ice Cavern", "Ordon Village", 3, TrainCardColor.RED));
        routes.add(createRoute("Goron City", "Ice Cavern", 5, TrainCardColor.BLACK));
        routes.add(createRoute("Fire Temple", "Goron City", 6, TrainCardColor.WHITE));
        routes.add(createRoute("Goron City", "Shadow Temple", 4, TrainCardColor.BLUE));
        routes.add(createRoute("Goron Village", "Hidden Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Goron Village", "Hidden Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Goron Village", "Hyrule Castle", 2, TrainCardColor.RED));
        routes.add(createRoute("Hidden Village", "Temple of Time", 2, TrainCardColor.GREY));
        routes.add(createRoute("Dragon Roost Island", "Hidden Village", 4, TrainCardColor.PINK));
        routes.add(createRoute("Dragon Roost Island", "Marine Research Laboratory", 2, TrainCardColor.GREY));
        routes.add(createRoute("Dragon Roost Island", "Spirit Temple", 6, TrainCardColor.RED));
        routes.add(createRoute("Dragon Roost Island", "Gerudo Fortress", 5, TrainCardColor.BLUE));
        routes.add(createRoute("Gerudo Fortress", "Hidden Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Gerudo Fortress", "Hidden Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Gerudo Fortress", "Spirit Temple", 1, TrainCardColor.GREY));
        routes.add(createRoute("Hyrule Castle", "Temple of Light", 3, TrainCardColor.BLACK));
        routes.add(createRoute("Hyrule Castle", "Temple of Time", 3, TrainCardColor.WHITE));
        routes.add(createRoute("Spirit Temple", "Marine Research Laboratory", 2, TrainCardColor.GREY));
        routes.add(createRoute("Spirit Temple", "Temple of Time", 4, TrainCardColor.YELLOW));
        routes.add(createRoute("Spirit Temple", "Temple of Time", 4, TrainCardColor.ORANGE));
        routes.add(createRoute("Lost Woods", "Spirit Temple", 3, TrainCardColor.GREEN));
        routes.add(createRoute("Clock Town", "Marine Research Laboratory", 6, TrainCardColor.GREEN));
        routes.add(createRoute("Marine Research Laboratory", "Rito Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Marine Research Laboratory", "Rito Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Clock Town", "Rito Village", 4, TrainCardColor.RED));
        routes.add(createRoute("Lost Woods", "Rito Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Lost Woods", "Rito Village", 2, TrainCardColor.GREY));
        routes.add(createRoute("Rito Village", "Zora's Domain", 3, TrainCardColor.BLUE));
        routes.add(createRoute("Lost Woods", "Temple of Light", 2, TrainCardColor.BLUE));
        routes.add(createRoute("Lost Woods", "Temple of Light", 2, TrainCardColor.PINK));
        routes.add(createRoute("Lost Woods", "Tarrey Town", 4, TrainCardColor.YELLOW));
        routes.add(createRoute("Great Deku Tree", "Lost Woods", 4, TrainCardColor.YELLOW));
        routes.add(createRoute("Death Mountain", "Lost Woods", 3, TrainCardColor.BLACK));
        routes.add(createRoute("Death Moutain", "Lost Woods", 3, TrainCardColor.ORANGE));
        routes.add(createRoute("City in the Sky", "Tarrey Town", 3, TrainCardColor.BLACK));
        routes.add(createRoute("City in the Sky", "Tarrey Town", 3, TrainCardColor.ORANGE));
        routes.add(createRoute("Ordon Village", "Tarrey Town", 2, TrainCardColor.GREY));
        routes.add(createRoute("Deku Palace", "Tarrey Town", 2, TrainCardColor.GREY));
        routes.add(createRoute("Deku Palace", "Tarrey Town", 2, TrainCardColor.GREY));
        routes.add(createRoute("Deku Palace", "Ordon Village", 6, TrainCardColor.PINK));
        routes.add(createRoute("Deku Palace", "Shadow Temple", 6, TrainCardColor.ORANGE));
        routes.add(createRoute("Deku Palace", "Tingle", 1, TrainCardColor.GREY));
        routes.add(createRoute("Great Deku Tree", "Tingle", 3, TrainCardColor.GREEN));
        routes.add(createRoute("Lurelin Village", "Tingle", 3, TrainCardColor.RED));
        routes.add(createRoute("Lurelin Village", "Tingle", 3, TrainCardColor.YELLOW));
        routes.add(createRoute("Lake Hylia", "Tingle", 4, TrainCardColor.PINK));
        routes.add(createRoute("Death Mountain", "Great Deku Tree", 1, TrainCardColor.GREY));
        routes.add(createRoute("Death Mountain", "Great Deku Tree", 1, TrainCardColor.GREY));
        routes.add(createRoute("Death Mountain", "Zora's Domain", 3, TrainCardColor.GREY));
        routes.add(createRoute("Clock Town", "Death Mountain", 5, TrainCardColor.WHITE));
        routes.add(createRoute("Clock Town", "Zora's Domain", 3, TrainCardColor.GREY));
        routes.add(createRoute("Fire Temple", "Shadow Temple", 3, TrainCardColor.BLACK));
        routes.add(createRoute("Fire Temple", "Tingle Island", 4, TrainCardColor.RED));
        routes.add(createRoute("Fire Temple", "Snowpeak Ruins", 3, TrainCardColor.GREEN));
        routes.add(createRoute("Snowpeak Ruins", "Tingle Island", 1, TrainCardColor.GREY));
        routes.add(createRoute("Snowpeak Ruins", "Tingle Island", 1, TrainCardColor.GREY));
        routes.add(createRoute("Lurelin Village", "Shadow Temple", 3, TrainCardColor.PINK));
        routes.add(createRoute("Shadow Temple", "Tingle Island", 6, TrainCardColor.YELLOW));
        routes.add(createRoute("Tingle Island", "Zora's Hall", 1, TrainCardColor.GREY));
        routes.add(createRoute("Tingle Island", "Zora's Hall", 1, TrainCardColor.GREY));
        routes.add(createRoute("Lake Hylia", "Lurelin Village", 3, TrainCardColor.ORANGE));
        routes.add(createRoute("Lurelin Village", "Zora's Hall", 6, TrainCardColor.BLUE));
        routes.add(createRoute("Water Temple", "Zora's Hall", 3, TrainCardColor.PINK));
        routes.add(createRoute("Water Temple", "Zora's Hall", 3, TrainCardColor.GREEN));
        routes.add(createRoute("Lake Hylia", "Water Temple", 5, TrainCardColor.ORANGE));
        routes.add(createRoute("Lake Hylia", "Water Temple", 5, TrainCardColor.WHITE));
        routes.add(createRoute("Forest Temple", "Lake Hylia", 3, TrainCardColor.GREY));
        routes.add(createRoute("Outset Island", "Water Temple", 3, TrainCardColor.PINK));
        routes.add(createRoute("Forest Temple", "Outset Island", 3, TrainCardColor.RED));
        routes.add(createRoute("Clock Town", "Forest Temple", 3, TrainCardColor.YELLOW));
        routes.add(createRoute("Clock Town", "Outset Island", 6, TrainCardColor.BLACK));
    }

    public Route createRoute(String city1_name, String city2_name, int length, TrainCardColor color) {
        City city1 = new City(city1_name);
        City city2 = new City(city2_name);
        CityPair cities = new CityPair(city1, city2);
        return new Route(cities, length, color);
    }
}
