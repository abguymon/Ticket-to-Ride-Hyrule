package cs240.lib.common;

import java.util.ArrayList;

import cs240.lib.Model.Game;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;

/**
 * Created by savannah.jane on 3/26/2018.
 */

public class DestinationCardResolution {
    public static final DestinationCardResolution SINGLETON = new DestinationCardResolution();

    public void resolveDestinationCards(Game game) {
        for (int i = 0; i < game.getPlayerArray().size(); ++i) {
            resolvePlayerDestinationCards(game.getPlayerArray().get(i));
        }
    }

    private void resolvePlayerDestinationCards(Player player) {
        ArrayList<DestinationCard> destCards = player.getDestinationCards();
        for (int i = 0; i < destCards.size(); ++i) {
            if (isFulfilled(destCards.get(i), player)) {
                player.addPositiveDestinationPoints(destCards.get(i).getValue());
            }
            else {
                player.addNegativeDestinationPoints(destCards.get(i).getValue());
            }
        }
    }

    private boolean isFulfilled(DestinationCard card, Player player) {
        String startCity = card.getStartCity().getCityName();
        String endCity = card.getEndCity().getCityName();
        ArrayList<Route> playerRoutes = player.getClaimedRoutes();
        for (int i = 0; i < playerRoutes.size(); ++i) {
            Route route = playerRoutes.get(i);
            if (route.getCity1Name().equals(startCity) || route.getCity2Name().equals(startCity)) {
                ArrayList<Edge> edges = resetEdges(playerRoutes);
                return findDestinationPath(new Edge(route), edges,endCity);
            }
        }
        return false;
    }

    private boolean findDestinationPath(Edge root, ArrayList<Edge> edges, String endCity) {
        if (root.getRoute().getCity2Name().equals(endCity) || root.getRoute().getCity1Name().equals(endCity)) {
            return true;
        }
        for (int i = 0; i < edges.size(); ++i) {
            if (isAdjacent(root.getRoute(), edges.get(i).getRoute())) {
                root.setVisited(true);
                return findDestinationPath(edges.get(i), edges, endCity);
            }
        }
        return false;
    }

    private ArrayList<Edge> resetEdges(ArrayList<Route> routes) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < routes.size(); ++i) {
            edges.add(new Edge(routes.get(i)));
        }
        return edges;
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
