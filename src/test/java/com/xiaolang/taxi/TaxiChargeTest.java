package com.xiaolang.taxi;

import com.xiaolang.taxi.config.TaxiPlaceType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class TaxiChargeTest {

    private Taxi taxi;
    private TaxiCharge taxiCharge;
    private LocalDateTime aboardTime;
    private double distance;

    @Test
    public void testOuterDayStarting() {
        taxi = new Taxi(TaxiPlaceType.OUTER);
        aboardTime = LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 2.8;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(14L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testOuterDayBetweenThreeAndTen() {
        taxi = new Taxi(TaxiPlaceType.OUTER);
        aboardTime = LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 8;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(26.5, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testOuterDayOverTen() {
        taxi = new Taxi(TaxiPlaceType.OUTER);
        aboardTime = LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 15;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(44L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testOuterNightStarting() {
        taxi = new Taxi(TaxiPlaceType.OUTER);
        aboardTime = LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 2.8;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(18L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testOuterNightBetweenThreeAndTen() {
        taxi = new Taxi(TaxiPlaceType.OUTER);
        aboardTime = LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 8;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(33L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testOuterNightOverTen() {
        taxi = new Taxi(TaxiPlaceType.OUTER);
        aboardTime = LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 15;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(54L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testInnerDayStarting() {
        taxi = new Taxi(TaxiPlaceType.INNER);
        aboardTime = LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 2.8;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(14L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testInnerDayBetweenThreeAndTen() {
        taxi = new Taxi(TaxiPlaceType.INNER);
        aboardTime = LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 8;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(26.5, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testInnerDayOverTen() {
        taxi = new Taxi(TaxiPlaceType.INNER);
        aboardTime = LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 15;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(49L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testInnerNightStarting() {
        taxi = new Taxi(TaxiPlaceType.INNER);
        aboardTime = LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 2.8;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(18L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testInnerNightBetweenThreeAndTen() {
        taxi = new Taxi(TaxiPlaceType.INNER);
        aboardTime = LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 8;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(33L, taxiCharge.getAmountPrice(), 0.0);
    }

    @Test
    public void testInnerNightOverTen() {
        taxi = new Taxi(TaxiPlaceType.INNER);
        aboardTime = LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        distance = 15;
        taxiCharge = new TaxiCharge(taxi, aboardTime, distance);
        assertEquals(62.5, taxiCharge.getAmountPrice(), 0.0);
    }

}