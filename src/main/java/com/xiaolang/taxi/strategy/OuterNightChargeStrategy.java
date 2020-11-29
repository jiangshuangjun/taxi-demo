package com.xiaolang.taxi.strategy;


import com.xiaolang.taxi.config.TaxiChargeType;
import com.xiaolang.taxi.config.TaxiDistanceChargeFlag;
import com.xiaolang.taxi.config.TaxiUnitPrice;

import static com.xiaolang.taxi.config.TaxiDistanceChargeFlag.THREE;
import static com.xiaolang.taxi.config.TaxiDistanceChargeFlag.TEN;

public class OuterNightChargeStrategy implements ITaxiChargeStrategy {

    @Override
    public double getStartingTaxiFare(TaxiChargeType chargeType, double distance) {
        return TaxiUnitPrice.OUTER_NIGHT_STARTING.getUnitPrice();
    }

    @Override
    public double getThreeToTenKilometreTaxiFare(TaxiChargeType chargeType, double distance) {
        double price = 0;
        if (distance > THREE.getMaxDistance() && distance <= TEN.getMaxDistance()) {
            price = (distance - TaxiDistanceChargeFlag.THREE.getMaxDistance()) * TaxiUnitPrice.OUTER_NIGHT_BETWEEN_THREE_AND_TEN.getUnitPrice();
        } else if (distance > TEN.getMaxDistance()) {
            price = (TaxiDistanceChargeFlag.TEN.getMaxDistance() - TaxiDistanceChargeFlag.THREE.getMaxDistance()) * TaxiUnitPrice.OUTER_NIGHT_BETWEEN_THREE_AND_TEN.getUnitPrice();
        }
        return price;
    }

    @Override
    public double getOverTenKilometreTaxiFare(TaxiChargeType chargeType, double distance) {
        if (distance > TEN.getMaxDistance()) {
            return (distance - TaxiDistanceChargeFlag.TEN.getMaxDistance()) * TaxiUnitPrice.OUTER_NIGHT_OVER_TEN.getUnitPrice();
        }
        return 0;
    }

}
