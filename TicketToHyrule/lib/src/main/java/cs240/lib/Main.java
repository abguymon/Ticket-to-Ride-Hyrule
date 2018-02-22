package cs240.lib;

import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.City;
import cs240.lib.Model.gameParts.CityPair;
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
        castletowntoHylia.setSecondaryColor(TrainCardColor.ORANGE);
        System.out.println(castletowntoHylia.toString());

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
