package cs240.lib;

import java.util.ArrayList;

import cs240.lib.Model.cards.TrainCard;
import cs240.lib.Model.colors.PlayerColor;
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.City;
import cs240.lib.Model.gameParts.CityPair;
import cs240.lib.Model.gameParts.Player;
import cs240.lib.Model.gameParts.Route;

public class Main {

    public static void main(String args[])
    {

        //cityTest();
        //cityPairTest();
        routeTest();
    }

    private static void routeTest() {
        City castletown = new City("castletown");
        City hylia = new City("hylia");
        CityPair castletownAndHylia = new CityPair(castletown, hylia);
        Route castletowntoHylia = new Route(castletownAndHylia, 4, TrainCardColor.BLUE);
        System.out.println(castletowntoHylia.toString());

        Player link = new Player(PlayerColor.GREEN);
        ArrayList<TrainCard> linksTrainCards = new ArrayList<>();
        TrainCard blueCard = new TrainCard(TrainCardColor.BLUE);
        TrainCard greenCard = new TrainCard(TrainCardColor.GREEN);
        linksTrainCards.add(blueCard); linksTrainCards.add(greenCard);
        linksTrainCards.add(blueCard); linksTrainCards.add(blueCard);
        linksTrainCards.add(blueCard); linksTrainCards.add(blueCard);
        link.setTrainCards(linksTrainCards);
        castletowntoHylia.claim(link);
        if(castletowntoHylia.isClaimed()){
            System.out.println("Trains remaining for Link: " + link.getTrainsRemaining());
        }
        System.out.print("Link's cards:");
        for (int i = 0; i < link.getTrainCards().size(); ++i){
            System.out.print(" " + link.getTrainCards().get(i).getColor());
        }
        System.out.println();

        Player gannon = new Player(PlayerColor.BLACK);
        castletowntoHylia.claim(gannon);
        System.out.println("Trains remaining for Gannon: " + gannon.getTrainsRemaining());

        System.out.println("Color of route owner: " + castletowntoHylia.getOwner().getColor());


    }

    public static void cityPairTest(){
        City castletown = new City("castletown");
        City hylia = new City("hylia");
        City lanLanRanch = new City ("lan lan ranch");
        City kakariko = new City ("kakariko");

        CityPair castletownToHylia = new CityPair(castletown, hylia);
        CityPair lanlanToKakariko = new CityPair(lanLanRanch, kakariko);

        CityPair othercastletownToHylia = new CityPair(castletown, hylia);
        CityPair otherlanlanToKakariko = new CityPair(kakariko, lanLanRanch);

        if (castletownToHylia.equals(othercastletownToHylia)){ //test for same position equals
            System.out.println("City Pair Success");
        }
        if (lanlanToKakariko.equals(otherlanlanToKakariko)){ //test for opposite position equals
            System.out.println("City Pair Success");
        }
    }


    public static void cityTest(){
        City castletown = new City("castletown");
        City hylia = new City("hylia");
        City lanLanRanch = new City ("lan lan ranch");
        City kakariko = new City ("kakariko");

        City samecastletown = new City("castletown");
        City fakehylia = new City("hilia");
        City fakelanLanRanch = new City ("lan  lan ranch");
        City fakekakariko = new City ("");

        if (castletown.equals(samecastletown)){ // test for successful equals
            System.out.println("City Success");
        }else{
            System.out.println("City Failure");
        }
        if (!hylia.equals(fakehylia)){ // test for failure equals, misspelling
            System.out.println("City Success");
        }else{
            System.out.println("City Failure");
        }
        if (!lanLanRanch.equals(fakelanLanRanch)){ //test for failure equals, extra space
            System.out.println("City Success");
        }else{
            System.out.println("City Failure");
        }
        if (!kakariko.equals(fakekakariko)){ //test for failed equals, empty string
            System.out.println("City Success");
        }else{
            System.out.println("City Failure");
        }
    }

}
