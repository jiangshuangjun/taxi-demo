package com.xiaolang.taxi;


import com.xiaolang.taxi.config.TaxiChargeType;
import com.xiaolang.taxi.config.TaxiPlaceType;
import com.xiaolang.taxi.strategy.ITaxiChargeStrategy;
import com.xiaolang.taxi.strategy.TaxiChargeStrategyFactory;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TaxiCharge {

    private Taxi taxi;
    private LocalDateTime aboardTime;
    private double distance;
    private TaxiChargeType chargeType;
    private ITaxiChargeStrategy taxiChargeStrategy;

    public TaxiCharge(Taxi taxi, LocalDateTime aboardTime, double distance) {
        this.taxi = taxi;
        this.aboardTime = aboardTime;
        this.distance = distance;
        TaxiChargeType chargeType = this.getTaxiChargeType(taxi, aboardTime);
        this.chargeType = chargeType;
        this.taxiChargeStrategy = TaxiChargeStrategyFactory.getTaxiChargeStrategy(chargeType);
    }

    public final double getAmountPrice() {
        if (distance < 0) {
            throw new IllegalArgumentException("The distance must > 0");
        }
        if (distance == 0) {
            return 0;
        }

        double amountPrice = 0;

        // 1. 计算起步价
        amountPrice += this.taxiChargeStrategy.getStartingTaxiFare(this.chargeType, distance);

        // 2. 计算 3 ~ 10 公里（包含 10 公里）的费用
        amountPrice += this.taxiChargeStrategy.getThreeToTenKilometreTaxiFare(this.chargeType, distance);

        // 3. 计算 10 公里以外的费用
        amountPrice += this.taxiChargeStrategy.getOverTenKilometreTaxiFare(this.chargeType, distance);

        return amountPrice;
    }

    private TaxiChargeType getTaxiChargeType(Taxi taxi, LocalDateTime time) {
        LocalDateTime beginOfTheDay = time.with(LocalTime.MIN);
        LocalDateTime endOfTheDay = time.with(LocalTime.MAX);
        boolean isNight = (time.isAfter(beginOfTheDay) && time.isBefore(beginOfTheDay.plusHours(6))) || (time.isAfter(endOfTheDay.plusHours(-1)) && time.isBefore(endOfTheDay));
        boolean isOuter = taxi.getPlaceType().equals(TaxiPlaceType.OUTER);
        if (isNight && isOuter) {
            return TaxiChargeType.OUTER_NIGHT;
        } else if (!isNight && isOuter) {
            return TaxiChargeType.OUTER_DAY;
        } else if (isNight) {
            return TaxiChargeType.INNER_NIGHT;
        } else {
            return TaxiChargeType.INNER_DAY;
        }
    }

}
