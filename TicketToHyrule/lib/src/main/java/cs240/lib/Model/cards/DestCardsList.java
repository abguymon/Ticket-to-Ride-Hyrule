package cs240.lib.Model.cards;

import java.util.ArrayList;

import cs240.lib.Model.gameParts.City;
import cs240.lib.Model.gameParts.CitiesList;
import cs240.lib.Model.gameParts.SetupException;

/**
 * Created by David on 2/27/2018.
 */

public class DestCardsList {
    public static DestCardsList SINGLETON = new DestCardsList();

    private ArrayList<DestinationCard> destinationCards;

    private DestCardsList(){
        createDestinationCards();
        try {
            if (destinationCards.size() != 30) {
                throw new SetupException("Destination Cards size does not match setup number");
            }
        }catch(SetupException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void createDestinationCards() {
        destinationCards = new ArrayList<>();
        City outsetIsland = new City("Outset Island");
        City lordJabuJabu = new City("Lord Jabu Jabu");
        City marineResearchLab = new City("Marine Research Lab");
        City dekuPalace = new City("Deku Palace");
        City goronCity = new City("Goron City");
        City templeOfLight = new City("Temple of Light");
        City gerudoFortress = new City("Gerudo Fortress");
        City templeOfTime = new City("Temple of Time");
        City zorasHall = new City("Zora's Hall");
        City snowpeakRuins = new City("Snowpeak Ruins");
        City hatenoVillage = new City("Hateno Village");
        City clockTown = new City("Clock Town");
        City dragonRoostIsland = new City("Dragon Roost Island");
        City ordonVillage = new City("Ordon Village");
        City forestTemple = new City("Forest Temple");
        City ritoVillage = new City("Rito Village");
        City lurelinVillage = new City("Lurelin Village");
        City fireTemple = new City("Fire Temple");
        City spiritTemple = new City("Spirit Temple");
        City waterTemple = new City("Water Temple");
        City lostWoods = new City("Lost Woods");
        City tarreyTown = new City("Tarrey Town");
        City cityInTheSky = new City("City in the Sky");
        City deathMountain = new City("Death Mountain");
        City zorasDomain = new City("Zora's Domain");
        City lakeHylia = new City("Lake Hylia");
        City iceCavern = new City("Ice Cavern");
        City hyruleCastle = new City("Hyrule Castle");
        City tingleIsland = new City("Tingle Island");
        City shadowTemple = new City("Shadow Temple");

        DestinationCard cardOne = new DestinationCard(21, outsetIsland, lordJabuJabu);
        DestinationCard card2 = new DestinationCard(8, marineResearchLab, dekuPalace);
        DestinationCard card3 = new DestinationCard(8, goronCity, templeOfLight);
        DestinationCard card4 = new DestinationCard(6, lordJabuJabu, gerudoFortress);
        DestinationCard card5 = new DestinationCard(17, templeOfTime, zorasHall);
        DestinationCard card6 = new DestinationCard(20, snowpeakRuins, hatenoVillage);
        DestinationCard card7 = new DestinationCard(10, dekuPalace, clockTown);
        DestinationCard card8 = new DestinationCard(10, dragonRoostIsland, ordonVillage);
        DestinationCard card9 = new DestinationCard(11, zorasHall, forestTemple);
        DestinationCard card10 = new DestinationCard(11, lordJabuJabu, ritoVillage);
        DestinationCard card11 = new DestinationCard(7, lurelinVillage, fireTemple);
        DestinationCard card12 = new DestinationCard(13, fireTemple, forestTemple);
        DestinationCard card13 = new DestinationCard(20, outsetIsland, dragonRoostIsland);
        DestinationCard card14 = new DestinationCard(11, spiritTemple, goronCity);
        DestinationCard card15 = new DestinationCard(17, waterTemple, gerudoFortress);
        DestinationCard card16 = new DestinationCard(5, lostWoods, marineResearchLab);
        DestinationCard card17 = new DestinationCard(16, tarreyTown, outsetIsland);
        DestinationCard card18 = new DestinationCard(11, cityInTheSky, deathMountain);
        DestinationCard card19 = new DestinationCard(9, zorasDomain, tarreyTown);
        DestinationCard card20 = new DestinationCard(13, snowpeakRuins, lakeHylia);
        DestinationCard card21 = new DestinationCard(12, hatenoVillage, dragonRoostIsland);
        DestinationCard card22 = new DestinationCard(7, tarreyTown, spiritTemple);
        DestinationCard card23 = new DestinationCard(9, iceCavern, hyruleCastle);
        DestinationCard card24 = new DestinationCard(22, tingleIsland, lordJabuJabu);
        DestinationCard card25 = new DestinationCard(4, deathMountain, clockTown);
        DestinationCard card26 = new DestinationCard(8, shadowTemple, outsetIsland);
        DestinationCard card27 = new DestinationCard(12, dekuPalace, marineResearchLab);
        DestinationCard card28 = new DestinationCard(13, iceCavern, templeOfTime);
        DestinationCard card29 = new DestinationCard(9, dekuPalace, zorasDomain);
        DestinationCard card30 = new DestinationCard(9, tingleIsland, outsetIsland);

        destinationCards.add(cardOne);
        destinationCards.add(card2);
        destinationCards.add(card3);
        destinationCards.add(card4);
        destinationCards.add(card5);
        destinationCards.add(card6);
        destinationCards.add(card7);
        destinationCards.add(card8);
        destinationCards.add(card9);
        destinationCards.add(card10);

        destinationCards.add(card11);
        destinationCards.add(card12);
        destinationCards.add(card13);
        destinationCards.add(card14);
        destinationCards.add(card15);
        destinationCards.add(card16);
        destinationCards.add(card17);
        destinationCards.add(card18);
        destinationCards.add(card19);
        destinationCards.add(card20);

        destinationCards.add(card21);
        destinationCards.add(card22);
        destinationCards.add(card23);
        destinationCards.add(card24);
        destinationCards.add(card25);
        destinationCards.add(card26);
        destinationCards.add(card27);
        destinationCards.add(card28);
        destinationCards.add(card29);
        destinationCards.add(card30);
    }

    public ArrayList<DestinationCard> getDestinationCards() {
        return destinationCards;
    }
}

//DESTINATION CARDS
/*
1 Outset Island -- Lord Jabu Jabu (21)
2 Marine research lab -- Deku palace (8)
3 Goron City -- Temple of Light (8)
4 Lord Jabu Jabu -- Gerudo Fortress (6)
5 Temple of Time -- Zora's Hall (17)
6 Snowpeak Ruins -- Hateno Village (20)
7 Deku Palace -- Clock Tower (10)
8 Dragon Roost Island -- Ordon Village (10)
9 Zora's Hall -- Forest Temple (11)
10 Lord Jabu Jabu -- Rito Village (11)
11 Lurelin Village -- Fire temple (7)
12 Fire temple -- Forest Temple (13)
13 Outset Island -- Dragon Roost Island (20)
14 Spirit Temple -- Goron City (11)
15 Water Temple -- Gerudo Fortress (17)
16 Lost Woods -- Marine Research Lab (5)
17 Tarrey Town -- Outset Island (16)
18 City in the Sky -- Death Mountain (11)
19 Zora's Domain -- Tarrey Town (9)
20 Snow Peak Ruins -- Lake Hylia (13)
21 Hateno Village -- Dragon Roost Island (12)
22 Tarrey Town -- Spirit Temple (7)
23 Ice Cavern -- Hyrule Castle (9)
24 Tingle Island -- Lord Jabu Jabu (22)
25 Death Mountain -- Clock Town (4)
26 Shadow Temple -- Outset Island (8)
27 Deku Place -- Marine Research Lab (12)
28 Ice Cavern -- Temple of Time (13)
29 Deku Place -- Zora's Domain (9)
30 Tingle Island -- Outset Island (9)
*/
