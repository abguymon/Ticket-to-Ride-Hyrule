package cs240.lib.Model;

/**
 * Created by David on 2/21/2018.
 */

public class City implements Comparable<City> {
    private String cityName;

    public City(){}
    public City(String cityName){
        if (cityName != null) {
            this.cityName = cityName;
        }else{

        }
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        if (cityName != null) {
            this.cityName = cityName;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City)o;
        return equalsHelper(city);
    }

    private boolean equalsHelper(City city) {
        if (city.getCityName().equals(this.cityName)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(City city) {
        if (this.cityName.compareTo(city.getCityName()) == 1){
            return 1;
        }else if (this.cityName.compareTo(city.getCityName()) == -1){
            return -1;
        }else{
            return 0;
        }
    }
}
