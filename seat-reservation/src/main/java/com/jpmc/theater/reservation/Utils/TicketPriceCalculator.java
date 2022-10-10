package com.jpmc.theater.reservation.Utils;

import com.jpmc.theater.reservation.dto.Discount;
import com.jpmc.theater.reservation.dto.ShowTimeRange;
import com.jpmc.theater.reservation.enums.DiscountUnit;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TicketPriceCalculator {

    /**
     * calculateTicketPrice calculates the ticket price by reducing if any discount
     * @param specialCode, ticketPrice, showStartDateTime, sequenceOfTheDay
     * @return ticket price after discount
     */
    public static double calculateTicketPrice(String specialCode,double ticketPrice,LocalDateTime showStartDateTime, int sequenceOfTheDay) {
        if(showStartDateTime == null)
            return 0;
        return ticketPrice - getDiscount(specialCode,ticketPrice,showStartDateTime,sequenceOfTheDay);
    }

    /**
     * getDiscount calculates the discount by considering various discounting rules
     * @param specialCode, ticketPrice, showStartDateTime, sequenceOfTheDay
     * @return discount amount
     */
    private static double getDiscount(String specialCode, double ticketPrice, LocalDateTime showStartDateTime, int sequenceOfTheDay) {
        List<Double> discount = new ArrayList<>();

        // START: special discount improvement it can also be in dollar amount or in percentage
        Discount specialDiscount = InitialRuleSetup.getSpecialCodeDiscount(specialCode);


        if (null != specialDiscount) {
            if(DiscountUnit.PERCENTAGE.equals(specialDiscount.getUnit())) {
                discount.add(ticketPrice * (specialDiscount.getDiscount()/100));
            } else if (DiscountUnit.DOLLAR.equals(specialDiscount.getUnit())) {
                discount.add(specialDiscount.getDiscount());
            }
        }

        // START: special discount improvement it can also be in dollar amount or in percentage
        // START: new requirement point 2 from README file and improvement, it supports dollar amount as well as percentage
        Discount sequentialDiscount = InitialRuleSetup.getSequenceOfTheDayDiscount(sequenceOfTheDay);
        if (null != sequentialDiscount) {
            if(DiscountUnit.PERCENTAGE.equals(sequentialDiscount.getUnit())) {
                discount.add(ticketPrice * (sequentialDiscount.getDiscount()/100));
            } else if (DiscountUnit.DOLLAR.equals(sequentialDiscount.getUnit())) {
                discount.add(sequentialDiscount.getDiscount());
            }
        }
        // END: new requirement point 2 from README file and improvement, it supports dollar amount as well as percentage

        // START: new requirement point 1 from README file, it supports dollar amount as well as percentage
        LocalTime showStartTime = LocalTime.of(showStartDateTime.getHour(),showStartDateTime.getMinute());
        Set<ShowTimeRange> showStartTimeDiscountKey = InitialRuleSetup.getStartTimeShowRangeDiscountMap().keySet();

        List<Discount> discountList = new ArrayList<>(); // particular show can fall under multiple show start range for example show starting with time 13 hour will fall 11-16 and 12-14 show start range
        showStartTimeDiscountKey.forEach(time -> {          // so show starting with 13 hours will be calculated for both start range discount to get max out of it
            if((time.getStartTime().compareTo(showStartTime) == 0 || time.getStartTime().isBefore(showStartTime)) && time.getEndTime().isAfter(showStartTime)){
                discountList.add(InitialRuleSetup.getStartTimeShowRangeDiscountMap().get(time));
            }});
        for(Discount showStartDis : discountList){
            if(DiscountUnit.PERCENTAGE.equals(showStartDis.getUnit())) {
                discount.add(ticketPrice * (showStartDis.getDiscount()/100));
            } else if (DiscountUnit.DOLLAR.equals(showStartDis.getUnit())) {
                discount.add(showStartDis.getDiscount());
            }
        }
        // END: new requirement point 1 from README file

        // biggest discount wins
        return discount.isEmpty() ? 0 : discount.stream().reduce(Math::max).get();
    }
}
