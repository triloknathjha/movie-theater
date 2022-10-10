package com.jpmc.theatre.reservation.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.reservation.Utils.InitialRuleSetup;
import com.jpmc.theater.reservation.dto.Customer;
import com.jpmc.theater.reservation.dto.Reservation;
import com.jpmc.theater.reservation.dto.Showing;
import com.jpmc.theater.reservation.dto.Shows;

import java.io.IOException;
import java.util.List;

public class TestUtil {

    private static List<Showing> schedule;

    public static Reservation createReservation(String customerName,String customerId, Integer sequenceOfTheDay, Integer audienceCount) {
        Customer customer = new Customer(customerName,customerId);
        Showing showing = new Showing();
        showing.setSequenceOfTheDay(sequenceOfTheDay);
        return new Reservation(customer,showing,audienceCount);
    }


    public static Shows listShowSchedule() {
        if(schedule == null){
            schedule = InitialRuleSetup.getShowingList();
        }
        return new Shows(schedule);
    }
/*
    public static List<Showing> listSchedule() {
        if(schedule == null){
            schedule = InitialRuleSetup.getShowingList();
        }
        return schedule;
    }*/

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsBytes(object);
    }

/*    public static String convertObjectToJsonString(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(object);
    }

    public static <T> Object convertJsonStringToObject(String jsonString, T objectType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.readValue(jsonString,objectType.getClass());
    }*/
}
