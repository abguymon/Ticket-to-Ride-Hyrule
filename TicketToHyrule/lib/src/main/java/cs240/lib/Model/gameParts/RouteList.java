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
        routes.add(createRoute(0,"Kakariko Village", "Lon Lon Ranch",3, TrainCardColor.GREY));
        routes.add(createRoute(1, "Goron Village", "Lon Lon Ranch", 2, TrainCardColor.GREY));
        routes.add(createRoute(2, "Goron Village", "Lon Lon Ranch", 2, TrainCardColor.GREY));
        routes.add(createRoute(3, "Hyrule Castle", "Lon Lon Ranch", 3, TrainCardColor.YELLOW));
        routes.add(createRoute(4, "Lon Lon Ranch", "Lord Jabu Jabu", 2, TrainCardColor.WHITE));
        routes.add(createRoute(5,"Lon Lon Ranch", "Lord Jabu Jabu", 2, TrainCardColor.GREEN));
        routes.add(createRoute(6,"Kakariko Village", "Temple of Light", 2 , TrainCardColor.GREEN));
        routes.add(createRoute(7,"Kakariko Village", "Temple of Light", 2 , TrainCardColor.WHITE));
        routes.add(createRoute(8,"Kakariko Village", "Tarrey Town", 4, TrainCardColor.BLUE));
        routes.add(createRoute(9,"City in the Sky", "Kakariko Village", 4, TrainCardColor.WHITE));
        routes.add(createRoute(10,"City in the Sky", "Lord Jabu Jabu", 2, TrainCardColor.GREY));
        routes.add(createRoute(11,"Hateno Village", "Lord Jabu Jabu", 2, TrainCardColor.ORANGE));
        routes.add(createRoute(12,"Hateno Village", "Lord Jabu Jabu", 2, TrainCardColor.BLACK));
        routes.add(createRoute(13,"City in the Sky", "Hateno Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(14,"Hateno Village", "Ice Cavern", 2, TrainCardColor.GREY));
        routes.add(createRoute(15,"Hateno Village", "Ice Cavern", 2, TrainCardColor.GREY));
        routes.add(createRoute(16,"City in the Sky", "Ice Cavern", 3, TrainCardColor.BLUE));
        routes.add(createRoute(17,"Ice Cavern", "Ordon Village", 3, TrainCardColor.RED));
        routes.add(createRoute(18,"Goron City", "Ice Cavern", 5, TrainCardColor.BLACK));
        routes.add(createRoute(19,"Fire Temple", "Goron City", 6, TrainCardColor.WHITE));
        routes.add(createRoute(20,"Goron City", "Shadow Temple", 4, TrainCardColor.BLUE));
        routes.add(createRoute(21,"Goron Village", "Hidden Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(22,"Goron Village", "Hidden Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(23,"Goron Village", "Hyrule Castle", 2, TrainCardColor.RED));
        routes.add(createRoute(24,"Hidden Village", "Temple of Time", 2, TrainCardColor.GREY));
        routes.add(createRoute(25,"Dragon Roost Island", "Hidden Village", 4, TrainCardColor.PINK));
        routes.add(createRoute(26,"Dragon Roost Island", "Marine Research Laboratory", 2, TrainCardColor.GREY));
        routes.add(createRoute(27,"Dragon Roost Island", "Spirit Temple", 6, TrainCardColor.RED));
        routes.add(createRoute(28,"Dragon Roost Island", "Gerudo Fortress", 5, TrainCardColor.BLUE));
        routes.add(createRoute(29,"Gerudo Fortress", "Hidden Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(30,"Gerudo Fortress", "Hidden Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(31,"Gerudo Fortress", "Spirit Temple", 1, TrainCardColor.GREY));
        routes.add(createRoute(32,"Hyrule Castle", "Temple of Light", 3, TrainCardColor.BLACK));
        routes.add(createRoute(33,"Hyrule Castle", "Temple of Time", 3, TrainCardColor.WHITE));
        routes.add(createRoute(34,"Spirit Temple", "Marine Research Laboratory", 2, TrainCardColor.GREY));
        routes.add(createRoute(35,"Spirit Temple", "Temple of Time", 4, TrainCardColor.YELLOW));
        routes.add(createRoute(36,"Spirit Temple", "Temple of Time", 4, TrainCardColor.ORANGE));
        routes.add(createRoute(37,"Lost Woods", "Spirit Temple", 3, TrainCardColor.GREEN));
        routes.add(createRoute(38,"Clock Town", "Marine Research Laboratory", 6, TrainCardColor.GREEN));
        routes.add(createRoute(39,"Marine Research Laboratory", "Rito Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(40,"Marine Research Laboratory", "Rito Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(41,"Clock Town", "Rito Village", 4, TrainCardColor.RED));
        routes.add(createRoute(42,"Lost Woods", "Rito Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(43,"Lost Woods", "Rito Village", 2, TrainCardColor.GREY));
        routes.add(createRoute(44,"Rito Village", "Zora's Domain", 3, TrainCardColor.BLUE));
        routes.add(createRoute(45,"Lost Woods", "Temple of Light", 2, TrainCardColor.BLUE));
        routes.add(createRoute(46,"Lost Woods", "Temple of Light", 2, TrainCardColor.PINK));
        routes.add(createRoute(47,"Lost Woods", "Tarrey Town", 4, TrainCardColor.YELLOW));
        routes.add(createRoute(48,"Great Deku Tree", "Lost Woods", 4, TrainCardColor.YELLOW));
        routes.add(createRoute(49,"Death Mountain", "Lost Woods", 3, TrainCardColor.BLACK));
        routes.add(createRoute(50,"Death Moutain", "Lost Woods", 3, TrainCardColor.ORANGE));
        routes.add(createRoute(51,"City in the Sky", "Tarrey Town", 3, TrainCardColor.BLACK));
        routes.add(createRoute(52,"City in the Sky", "Tarrey Town", 3, TrainCardColor.ORANGE));
        routes.add(createRoute(53,"Ordon Village", "Tarrey Town", 2, TrainCardColor.GREY));
        routes.add(createRoute(54,"Deku Palace", "Tarrey Town", 2, TrainCardColor.GREY));
        routes.add(createRoute(55,"Deku Palace", "Tarrey Town", 2, TrainCardColor.GREY));
        routes.add(createRoute(56,"Deku Palace", "Ordon Village", 6, TrainCardColor.PINK));
        routes.add(createRoute(57,"Deku Palace", "Shadow Temple", 6, TrainCardColor.ORANGE));
        routes.add(createRoute(58,"Deku Palace", "Tingle", 1, TrainCardColor.GREY));
        routes.add(createRoute(59,"Great Deku Tree", "Tingle", 3, TrainCardColor.GREEN));
        routes.add(createRoute(60,"Lurelin Village", "Tingle", 3, TrainCardColor.RED));
        routes.add(createRoute(61,"Lurelin Village", "Tingle", 3, TrainCardColor.YELLOW));
        routes.add(createRoute(62,"Lake Hylia", "Tingle", 4, TrainCardColor.PINK));
        routes.add(createRoute(63,"Death Mountain", "Great Deku Tree", 1, TrainCardColor.GREY));
        routes.add(createRoute(64,"Death Mountain", "Great Deku Tree", 1, TrainCardColor.GREY));
        routes.add(createRoute(65,"Death Mountain", "Zora's Domain", 3, TrainCardColor.GREY));
        routes.add(createRoute(66,"Clock Town", "Death Mountain", 5, TrainCardColor.WHITE));
        routes.add(createRoute(67,"Clock Town", "Zora's Domain", 3, TrainCardColor.GREY));
        routes.add(createRoute(68,"Fire Temple", "Shadow Temple", 3, TrainCardColor.BLACK));
        routes.add(createRoute(69,"Fire Temple", "Tingle Island", 4, TrainCardColor.RED));
        routes.add(createRoute(70,"Fire Temple", "Snowpeak Ruins", 3, TrainCardColor.GREEN));
        routes.add(createRoute(71,"Snowpeak Ruins", "Tingle Island", 1, TrainCardColor.GREY));
        routes.add(createRoute(72,"Snowpeak Ruins", "Tingle Island", 1, TrainCardColor.GREY));
        routes.add(createRoute(73,"Lurelin Village", "Shadow Temple", 3, TrainCardColor.PINK));
        routes.add(createRoute(74,"Shadow Temple", "Tingle Island", 6, TrainCardColor.YELLOW));
        routes.add(createRoute(75,"Tingle Island", "Zora's Hall", 1, TrainCardColor.GREY));
        routes.add(createRoute(76,"Tingle Island", "Zora's Hall", 1, TrainCardColor.GREY));
        routes.add(createRoute(77,"Lake Hylia", "Lurelin Village", 3, TrainCardColor.ORANGE));
        routes.add(createRoute(78,"Lurelin Village", "Zora's Hall", 6, TrainCardColor.BLUE));
        routes.add(createRoute(79,"Water Temple", "Zora's Hall", 3, TrainCardColor.PINK));
        routes.add(createRoute(80,"Water Temple", "Zora's Hall", 3, TrainCardColor.GREEN));
        routes.add(createRoute(81,"Lake Hylia", "Water Temple", 5, TrainCardColor.ORANGE));
        routes.add(createRoute(82,"Lake Hylia", "Water Temple", 5, TrainCardColor.WHITE));
        routes.add(createRoute(83,"Forest Temple", "Lake Hylia", 3, TrainCardColor.GREY));
        routes.add(createRoute(84,"Outset Island", "Water Temple", 3, TrainCardColor.PINK));
        routes.add(createRoute(85,"Forest Temple", "Outset Island", 3, TrainCardColor.RED));
        routes.add(createRoute(86,"Clock Town", "Forest Temple", 3, TrainCardColor.YELLOW));
        routes.add(createRoute(87,"Clock Town", "Outset Island", 6, TrainCardColor.BLACK));
    }

    public Route createRoute(int id, String city1_name, String city2_name, int length, TrainCardColor color) {
        City city1 = new City(city1_name);
        City city2 = new City(city2_name);
        CityPair cities = new CityPair(city1, city2);
        return new Route(id, cities, length, color);
    }

    public boolean isDoubleRoute(Route route) {
        for (int i = 0; i < routes.size(); ++i) {
            String loopCity1 = routes.get(i).getCity1Name();
            String loopCity2 = routes.get(i).getCity2Name();
            int loopId = routes.get(i).getId();
            if (route.getCity1Name().equals(loopCity1) && route.getCity2Name().equals(loopCity2) && loopId != route.getId()) {
                return true;
            }
        }
        return false;
    }
}
