package com.xiaolang.taxi.strategy;


import com.xiaolang.taxi.config.TaxiChargeType;

import java.util.HashMap;
import java.util.Map;


public class TaxiChargeStrategyFactory {

    private static final Map<TaxiChargeType, ITaxiChargeStrategy> strategies = new HashMap<>();

    static {
        strategies.put(TaxiChargeType.OUTER_DAY, new OuterDayChargeStrategy());
        strategies.put(TaxiChargeType.OUTER_NIGHT, new OuterNightChargeStrategy());
        strategies.put(TaxiChargeType.INNER_DAY, new InnerDayChargeStrategy());
        strategies.put(TaxiChargeType.INNER_NIGHT, new InnerNightChargeStrategy());
    }

    public static ITaxiChargeStrategy getTaxiChargeStrategy(TaxiChargeType chargeType) {
        return strategies.get(chargeType);
    }

}
