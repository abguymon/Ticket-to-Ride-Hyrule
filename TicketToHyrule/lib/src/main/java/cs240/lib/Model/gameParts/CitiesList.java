package cs240.lib.Model.gameParts;

import java.util.HashSet;

/**
 * Created by David on 2/27/2018.
 */

public class CitiesList {
    public static CitiesList SINGLETON = new CitiesList();

    private HashSet<City> cities;

    private CitiesList(){
        cities = new HashSet<>();
        createCities();
        try {
            if (cities.size() != 36) {
                throw new SetupException("Cities do not match set up number");
            }
        } catch (SetupException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void createCities() {
        City outsetIsland = new City("Outset Island"); cities.add(outsetIsland);
        City lordJabuJabu = new City("Lord Jabu Jabu"); cities.add(lordJabuJabu);
        City marineResearchLab = new City("Marine Research Lab"); cities.add(marineResearchLab);
        City dekuPalace = new City("Deku Palace"); cities.add(dekuPalace);
        City goronCity = new City("Goron City"); cities.add(goronCity);
        City templeOfLight = new City("Temple of Light"); cities.add(templeOfLight);
        City gerudoFortress = new City("Gerudo Fortress"); cities.add(gerudoFortress);
        City templeOfTime = new City("Temple of Time"); cities.add(templeOfTime);
        City zorasHall = new City("Zora's Hall"); cities.add(zorasHall);
        City snowpeakRuins = new City("Snowpeak Ruins"); cities.add(snowpeakRuins);
        City hatenoVillage = new City("Hateno Village"); cities.add(hatenoVillage);
        City clockTown = new City("Clock Town"); cities.add(clockTown);
        City dragonRoostIsland = new City("Dragon Roost Island"); cities.add(dragonRoostIsland);
        City ordonVillage = new City("Ordon Village"); cities.add(ordonVillage);
        City forestTemple = new City("Forest Temple"); cities.add(forestTemple);
        City ritoVillage = new City("Rito Village"); cities.add(ritoVillage);
        City lurelinVillage = new City("Lurelin Village"); cities.add(lurelinVillage);
        City fireTemple = new City("Fire Temple"); cities.add(fireTemple);
        City spiritTemple = new City("Spirit Temple"); cities.add(spiritTemple);
        City waterTemple = new City("Water Temple"); cities.add(waterTemple);
        City lostWoods = new City("Lost Woods"); cities.add(lostWoods);
        City tarreyTown = new City("Tarrey Town"); cities.add(tarreyTown);
        City cityInTheSky = new City("City in the Sky"); cities.add(cityInTheSky);
        City deathMountain = new City("Death Mountain"); cities.add(deathMountain);
        City zorasDomain = new City("Zora's Domain"); cities.add(zorasDomain);
        City lakeHylia = new City("Lake Hylia"); cities.add(lakeHylia);
        City iceCavern = new City("Ice Cavern"); cities.add(iceCavern);
        City hyruleCastle = new City("Hyrule Castle"); cities.add(hyruleCastle);
        City tingleIsland = new City("Tingle Island"); cities.add(tingleIsland);
        City shadowTemple = new City("Shadow Temple"); cities.add(shadowTemple);
        City greatDekuTree = new City("Great Deku Tree"); cities.add(greatDekuTree);
        City tingle = new City("Tingle"); cities.add(tingle); //this makes me uncomfortable
        City kakarikoVillage = new City("Kakariko Village"); cities.add(kakarikoVillage);
        City hiddenVillage = new City("Hidden Village"); cities.add(hiddenVillage);
        City goronVillage = new City("Goron Village"); cities.add(goronVillage);
        City lonLonRanch = new City("Lon Lon Ranch"); cities.add(lonLonRanch);

    }

    public HashSet<City> getCities() {
        return cities;
    }
}
