package com.xiaolang.taxi.strategy;


import com.xiaolang.taxi.config.TaxiChargeType;

/**
 * 出租车计费策略接口
 */
public interface ITaxiChargeStrategy {

    double getStartingTaxiFare(TaxiChargeType chargeType, double distance);

    double getThreeToTenKilometreTaxiFare(TaxiChargeType chargeType, double distance);

    double getOverTenKilometreTaxiFare(TaxiChargeType chargeType, double distance);

}
