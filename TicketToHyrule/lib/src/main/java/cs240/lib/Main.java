package cs240.lib;

import cs240.lib.Model.City;
import cs240.lib.Model.CityPair;

public class Main {

    public static void main(String args[])
    {
        City castletown = new City("castletown");
        City hylia = new City("hylia");
        City lanLanRanch = new City ("lan lan ranch");
        City kakariko = new City ("kakariko");

        City samecastletown = new City("castletown");
        City fakehylia = new City("hilia");
        City fakelanLanRanch = new City ("lan  lan ranch");
        City fakekakariko = new City ("");

        CityPair castletownToHylia = new CityPair(castletown, hylia);
        CityPair lanlanToKakariko = new CityPair(lanLanRanch, kakariko);

        CityPair othercastletownToHylia = new CityPair(castletown, hylia);
        CityPair otherlanlanToKakariko = new CityPair(kakariko, lanLanRanch);

        cityTest(castletown, samecastletown, hylia, fakehylia, lanLanRanch, fakelanLanRanch, kakariko, fakekakariko);

        cityPairTest(castletownToHylia, othercastletownToHylia, lanlanToKakariko, otherlanlanToKakariko);

    }

    public static void cityPairTest(CityPair castletownToHylia, CityPair othercastletownToHylia,
                                    CityPair lanlanToKakariko, CityPair otherlanlanToKakariko ){
        if (castletownToHylia.equals(othercastletownToHylia)){ //test for same position equals
            System.out.println("City Pair Success");
        }
        if (lanlanToKakariko.equals(otherlanlanToKakariko)){ //test for opposite position equals
            System.out.println("City Pair Success");
        }
    }


    public static void cityTest(City castletown, City othercastletown, City hylia, City fakehylia,
                                City lanLanRanch, City fakelanLanRanch, City kakariko, City fakekakariko){
        if (castletown.equals(othercastletown)){ // test for successful equals
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
