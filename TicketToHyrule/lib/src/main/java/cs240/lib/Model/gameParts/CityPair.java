package cs240.lib.Model.gameParts;

/**
 * Created by David on 2/21/2018.
 */

public class CityPair{
    private City city1;
    private City city2;

    public CityPair(City city1, City city2){
        this.city1 = city1;
        this.city2 = city2;
    }




    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}

        CityPair cityPair = (CityPair) o;
        return equalsHelper(cityPair);
    }

    private boolean equalsHelper(CityPair cityPair) {
        if ((cityPair.getCity1().equals(this.city1) && cityPair.getCity2().equals(this.city2))
                || cityPair.getCity2().equals(this.city1) && cityPair.getCity1().equals(this.city2)){
            return true;
        }else{
            return false;
        }
    }
}
