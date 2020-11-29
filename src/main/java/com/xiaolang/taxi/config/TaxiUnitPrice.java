package com.xiaolang.taxi.config;

public enum TaxiUnitPrice {

    OUTER_DAY_STARTING(14),
    OUTER_DAY_BETWEEN_THREE_AND_TEN(2.5),
    OUTER_DAY_OVER_TEN(2.5),
    INNER_DAY_STARTING(14),
    INNER_DAY_BETWEEN_THREE_AND_TEN(2.5),
    INNER_DAY_OVER_TEN(3.5),

    OUTER_NIGHT_STARTING(18),
    OUTER_NIGHT_BETWEEN_THREE_AND_TEN(3),
    OUTER_NIGHT_OVER_TEN(3),
    INNER_NIGHT_STARTING(18),
    INNER_NIGHT_BETWEEN_THREE_AND_TEN(3),
    INNER_NIGHT_OVER_TEN(4.7);

    private double unitPrice;

    TaxiUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

}
