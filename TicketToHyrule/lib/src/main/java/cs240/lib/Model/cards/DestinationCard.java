package cs240.lib.Model.cards;

import cs240.lib.Model.gameParts.City;

/**
 * Created by David on 2/21/2018.
 */

public class DestinationCard {
    private final int value;
    private final City startCity;
    private final City endCity;

    public DestinationCard(int value, City startCity, City endCity) {
        this.value = value;
        this.startCity = startCity;
        this.endCity = endCity;
    }

    public int getValue() {
        return value;
    }

    public City getStartCity() {
        return startCity;
    }

    public City getEndCity() {
        return endCity;
    }
}

//DESTINATION CARDS
/*
Outset Island -- Lord Jabu Jabu (21)
Marine research lab -- Deku palace (8)
Goron City -- Temple of Light (8)
Lord Jabu Jabu -- Gerudo Fortress (6)
Temple of Time -- Zora's Hall (17)
Snowpeak Ruins -- Hateno Village (20)
Deku Palace -- Clock Tower (10)
Dragon Roost Island -- Ordon Village (10)
Zora's Hall -- Forest Temple (11)
Lord Jabu Jabu -- Rito Village (11)
Lurelin Village -- Fire temple (7)
Fire temple -- Forest Temple (13)
Outset Island -- Dragon Roost Island (20)
Spirit Temple -- Goron City (11)
Water Temple -- Gerudo Fortress (17)
Lost Woods -- Marine Research Lab (5)
Tarrey Town -- Outset Island (16)
City in the Sky -- Death Mountain (11)
Zora's Domain -- Tarrey Town (9)
Snow Peak Ruins -- Lake Hylia (13)
Hateno Village -- Dragon Roost Island (12)
Tarrey Town -- Spirit Temple (7)
Ice Cavern -- Hyrule Castle (9)
Tingle Island -- Lord Jabu Jabu (22)
Death Mountain -- Clock Town (4)
Shadow Temple -- Outset Island (8)
Deku Place -- Marine Research Lab (12)
Ice Caver -- Temple of Time (13)
Deku Place -- Zora's Domain (9)
Tingle Island -- Outset Island (9)
*/
