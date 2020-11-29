package com.xiaolang.taxi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taxi1 {

    /** 内环==1、外环==0 */
    private int loop;
    /** 上车时间 */
    private LocalDateTime boardingTime;
    /** 运行距离 */
    private double distance;
    /** 行驶类型（白天=1、黑夜=0） */
    private int travelType;
    /** 起步价 */
    private double startPrice;


    public Taxi1(int loop, LocalDateTime boardingTime, double distance) {
        this.loop = loop;
        this.boardingTime = boardingTime;
        this.distance = distance;
    }

    public static boolean isDayType(LocalDateTime time) {
        LocalDateTime beginOfTheDay = time.with(LocalTime.MIN);
        LocalDateTime endOfTheDay = time.with(LocalTime.MAX);
        if ((time.isAfter(beginOfTheDay) && time.isBefore(beginOfTheDay.plusHours(6))) || (time.isAfter(endOfTheDay.plusHours(-1)) && time.isBefore(endOfTheDay))) {
            return false;
        }
        return true;
    }

    public double getStartPriceByBoardingTime(LocalDateTime time) {
        if (isDayType(time)) {
            return 14;
        }

        return 18;
    }

    public double getPrice() {
        double amountPrice = 0;

        // 白天：早晨 6 点 ~ 晚上 11 点
        if (this.getTravelType() == 1) {
            this.setStartPrice(14);
        } else if (this.getTravelType() == 0) {
            this.setStartPrice(18);
        }

        // 白天运行时段
        if (isDayType(this.getBoardingTime())) {
            if (this.getDistance() > 0 && this.getDistance() <= 3) {
                // 内外环出租车 3 公里以内的价格
                amountPrice = this.getStartPriceByBoardingTime(this.getBoardingTime());
            } else if (this.getDistance() > 3 && this.getDistance() <= 10) {
                // 内外环出租车 3 ~ 10 公里的价格
                amountPrice = this.getStartPriceByBoardingTime(this.getBoardingTime()) + (this.getDistance() - 3) * 2.5;
            } else if (this.getDistance() > 10 && this.getLoop() == 1) {
                // 内环出租车 10 公里之外的价格
                amountPrice = this.getStartPriceByBoardingTime(this.getBoardingTime()) + (10 - 3) * 2.5 + (this.getDistance() - 10) * 3.5;
            } else if (this.getDistance() > 10 && this.getLoop() == 0) {
                // 外环出租车 10 公里之外的价格
                amountPrice = this.getStartPriceByBoardingTime(this.getBoardingTime()) + (this.getDistance() - 3) * 2.5;
            }
        } else {
            if (this.getDistance() > 0 && this.getDistance() <= 3) {
                // 内外环出租车 3 公里以内的价格
                amountPrice = this.getStartPriceByBoardingTime(this.getBoardingTime());
            } else if (this.getDistance() > 3 && this.getDistance() <= 10) {
                // 内外环出租车 3 ~ 10 公里的价格
                amountPrice = this.getStartPriceByBoardingTime(this.getBoardingTime()) + (this.getDistance() - 3) * 3;
            } else if (this.getDistance() > 10 && this.getLoop() == 1) {
                // 内环出租车 10 公里之外的价格
                amountPrice = this.getStartPriceByBoardingTime(this.getBoardingTime()) + (10 - 3) * 3 + (this.getDistance() - 10) * 4.7;
            } else if (this.getDistance() > 10 && this.getLoop() == 0) {
                // 外环出租车 10 公里之外的价格
                amountPrice = this.getStartPriceByBoardingTime(this.getBoardingTime()) + (this.getDistance() - 3) * 3;
            }
        }

        return amountPrice;
    }

    public static void main(String[] args) {
        Taxi1 taxi1 = new Taxi1(1, LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 2.8);
        System.out.printf("内环车，早晨 6 点, 3 公里内计费：%s，期望值：14\n", taxi1.getPrice());

        Taxi1 taxi2 = new Taxi1(1, LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 8);
        System.out.printf("内环车，早晨 6 点, 8 公里计费：%s，期望值：26.5\n", taxi2.getPrice());

        Taxi1 taxi3 = new Taxi1(1, LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 15);
        System.out.printf("内环车，早晨 6 点, 15 公里计费：%s，期望值：49\n", taxi3.getPrice());

        Taxi1 taxi4 = new Taxi1(1, LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 2.8);
        System.out.printf("内环车，晚上 11 点, 3 公里内计费：%s，期望值：18\n", taxi4.getPrice());

        Taxi1 taxi5 = new Taxi1(1, LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 8);
        System.out.printf("内环车，晚上 11 点, 8 公里计费：%s，期望值：33\n", taxi5.getPrice());

        Taxi1 taxi6 = new Taxi1(1, LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 15);
        System.out.printf("内环车，晚上 11 点, 15 公里计费：%s，期望值：62.5\n", taxi6.getPrice());

        System.out.println();
        System.out.println("========================================");
        System.out.println();

        Taxi1 taxi7 = new Taxi1(0, LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 2.8);
        System.out.printf("外环车，早晨 6 点, 3 公里内计费：%s，期望值：14\n", taxi7.getPrice());

        Taxi1 taxi8 = new Taxi1(0, LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 8);
        System.out.printf("外环车，早晨 6 点, 8 公里计费：%s，期望值：26.5\n", taxi8.getPrice());

        Taxi1 taxi9 = new Taxi1(0, LocalDateTime.parse("2020-11-28 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 15);
        System.out.printf("外环车，早晨 6 点, 15 公里计费：%s，期望值：44\n", taxi9.getPrice());

        Taxi1 taxi10 = new Taxi1(0, LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 2.8);
        System.out.printf("外环车，晚上 11 点, 3 公里内计费：%s，期望值：18\n", taxi10.getPrice());

        Taxi1 taxi11 = new Taxi1(0, LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 8);
        System.out.printf("外环车，晚上 11 点, 8 公里计费：%s，期望值：33\n", taxi11.getPrice());

        Taxi1 taxi12 = new Taxi1(0, LocalDateTime.parse("2020-11-28 23:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 15);
        System.out.printf("外环车，晚上 11 点, 15 公里计费：%s，期望值：54\n", taxi12.getPrice());
    }

}
