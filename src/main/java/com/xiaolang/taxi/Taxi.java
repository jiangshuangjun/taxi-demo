package com.xiaolang.taxi;


import com.xiaolang.taxi.config.TaxiPlaceType;

public class Taxi {

    private TaxiPlaceType placeType;

    public Taxi(TaxiPlaceType placeType) {
        this.placeType = placeType;
    }

    public TaxiPlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(TaxiPlaceType placeType) {
        this.placeType = placeType;
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "placeType=" + placeType +
                '}';
    }

}
