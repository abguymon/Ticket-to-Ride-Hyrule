import java.util.ArrayList;

import cs240.lib.Model.Game;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.colors.PlayerColor;
import cs240.lib.Model.gameParts.City;
import cs240.lib.Model.gameParts.GameMap;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.common.DestinationCardResolution;
import cs240.lib.common.LongestPathCalculator;

/**
 * Created by savannah.jane on 3/29/2018.
 */

public class TestLongestPath {
    public static void main(String[] args) {
        Game game = new Game("TestGame");
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(PlayerColor.GREEN, "p1"));
        players.add(new Player(PlayerColor.RED, "p2"));
        players.add(new Player(PlayerColor.PINK, "p3"));
        game.setPlayerArray(players);
        ArrayList<Route> routes = game.getMap().getRoutes();
        game.getPlayer("p2").addRoute(routes.get(33));
        game.getPlayer("p2").addRoute(routes.get(24));
        game.getPlayer("p2").addRoute(routes.get(29));
        //game.getPlayer("p1").addRoute(routes.get(32));
        game.getPlayer("p1").addRoute(routes.get(29));
        game.getPlayer("p1").addRoute(routes.get(55));
        game.getPlayer("p3").addRoute(routes.get(29));
        game.getPlayer("p1").addDestinationCard(new DestinationCard(12,  new City("Gerudo Fortress"), new City("Temple of Time")));
        ArrayList<String> playersWithLongestRoute = LongestPathCalculator.SINGLETON.calculate(game);
        int p1LongRoute = game.getPlayer("p1").getLongestPath();
        int p2LongRoute = game.getPlayer("p2").getLongestPath();
        int p3LongRoute = game.getPlayer("p3").getLongestPath();
        DestinationCardResolution.SINGLETON.resolveDestinationCards(game);
        //game.endGame();
        for (int i = 0; i < playersWithLongestRoute.size(); ++i) {
            System.out.println(playersWithLongestRoute.get(i) + "\n");
        }
    }

    private void testLongestRoute() {
        Game game = new Game("TestGame");
        ArrayList<Player> players = new ArrayList<>();
        players.add(null);
    }

    private void testDestinationCompletion() {

    }
}
