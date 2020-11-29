package com.xiaolang.taxi.config;

public enum TaxiDistanceChargeFlag {

    THREE(3),
    TEN(10);

    private double maxDistance;

    TaxiDistanceChargeFlag(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public double getMaxDistance() {
        return maxDistance;
    }
}
