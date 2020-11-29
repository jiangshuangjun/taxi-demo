package com.xiaolang.taxi.strategy;


import com.xiaolang.taxi.config.TaxiChargeType;
import com.xiaolang.taxi.config.TaxiDistanceChargeFlag;
import com.xiaolang.taxi.config.TaxiUnitPrice;

public class OuterNightChargeStrategy implements ITaxiChargeStrategy {

    @Override
    public double getStartingTaxiFare(TaxiChargeType chargeType, double distance) {
        return TaxiUnitPrice.OUTER_NIGHT_STARTING.getUnitPrice();
    }

    @Override
    public double getThreeToTenKilometreTaxiFare(TaxiChargeType chargeType, double distance) {
        double price = 0;
        if (distance > 3 && distance <= 10) {
            price = (distance - TaxiDistanceChargeFlag.THREE.getMaxDistance()) * TaxiUnitPrice.OUTER_NIGHT_BETWEEN_THREE_AND_TEN.getUnitPrice();
        } else if (distance > 10) {
            price = (TaxiDistanceChargeFlag.TEN.getMaxDistance() - TaxiDistanceChargeFlag.THREE.getMaxDistance()) * TaxiUnitPrice.OUTER_NIGHT_BETWEEN_THREE_AND_TEN.getUnitPrice();
        }
        return price;
    }

    @Override
    public double getOverTenKilometreTaxiFare(TaxiChargeType chargeType, double distance) {
        if (distance > 10) {
            return (distance - TaxiDistanceChargeFlag.TEN.getMaxDistance()) * TaxiUnitPrice.OUTER_NIGHT_OVER_TEN.getUnitPrice();
        }
        return 0;
    }

}
