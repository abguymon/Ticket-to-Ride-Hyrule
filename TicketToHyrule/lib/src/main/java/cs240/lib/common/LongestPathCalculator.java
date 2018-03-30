package cs240.lib.common;

import java.util.ArrayList;

import cs240.lib.Model.Game;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;

/**
 * Created by savannah.jane on 3/25/2018.
 */

public class LongestPathCalculator {
    public static final LongestPathCalculator SINGLETON = new LongestPathCalculator();

    public ArrayList<String> calculate(Game game) {
        ArrayList<String> playersWithLongestRoute = new ArrayList<>();
        ArrayList<Player> playerArray = game.getPlayerArray();
        for (int i = 0; i < playerArray.size(); ++i) {
            playerArray.get(i).setLongestPath(getPlayerLongestRoute(playerArray.get(i)));
        }
        int longestPath = getLongestPath(playerArray);
        for (int i = 0; i < playerArray.size(); ++i) {
            if (playerArray.get(i).getLongestPath() == longestPath) {
                playersWithLongestRoute.add(playerArray.get(i).getPlayerName());
            }
        }
        return playersWithLongestRoute;
    }

    public int getLongestPath(ArrayList<Player> players) {
        int longestPath = 0;
        for (int i = 0; i < players.size(); ++i) {
            if (players.get(i).getLongestPath() > longestPath) {
                longestPath = players.get(i).getLongestPath();
            }
        }
        return longestPath;
    }

    private int getPlayerLongestRoute(Player player) {
        ArrayList<Route> playerRoutes = player.getClaimedRoutes();
        int longestRoute = 0;
        for (int i = 0; i < playerRoutes.size(); ++i) {
            ArrayList<Edge> edges = resetEdges(playerRoutes);
            int routeLength = getRouteLengthr(new Edge(playerRoutes.get(i)), edges, 0);
            if (routeLength > longestRoute) {
                longestRoute = routeLength;
                player.setLongestPath(longestRoute);
            }
        }
        return longestRoute;
    }

    private ArrayList<Edge> resetEdges(ArrayList<Route> routes) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < routes.size(); ++i) {
            edges.add(new Edge(routes.get(i)));
        }
        return edges;
    }

    private int getRouteLengthr(Edge root, ArrayList<Edge> edges, int length) {
        if (!hasAdjacentRoute(root, edges)) {
            length += root.getRoute().getLength();
        }
        //length += root.getRoute().getLength();
        for (int i = 0; i < edges.size(); ++i) {
            if (isAdjacent(root.getRoute(), edges.get(i).getRoute())) {
                if (!edges.get(i).isVisited()) {
                    root.setVisited(true);
                    length += root.getRoute().getLength();
                   return getRouteLengthr(edges.get(i), edges, length);
                   // length -= root.getRoute().getLength();
                }
            }
        }
        return length;
    }

    private boolean hasAdjacentRoute(Edge route, ArrayList<Edge> edges) {
        for (int i = 0; i < edges.size(); ++i) {
            if (isAdjacent(route.getRoute(), edges.get(i).getRoute())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdjacent(Route route1, Route route2) {
        if (route1.equals(route2)) {
            return false;
        }
       if (route1.getCity1Name().equals(route2.getCity1Name()) || route1.getCity1Name().equals(route2.getCity2Name())) {
           return true;
       }
       else if (route1.getCity2Name().equals(route2.getCity1Name()) || route1.getCity2Name().equals(route2.getCity2Name())) {
           return true;
       }
       return false;
    }
}
