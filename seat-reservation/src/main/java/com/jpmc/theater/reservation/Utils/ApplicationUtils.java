package com.jpmc.theater.reservation.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.reservation.deserializer.ShowTimeKeyDeserializer;
import com.jpmc.theater.reservation.dto.ShowTimeRange;

public class ApplicationUtils {
   private static ObjectMapper objectMapper;

    public static long getTimeDifference(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    public static ObjectMapper getObjectMapper(){
        if(null == objectMapper) {
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addKeyDeserializer(ShowTimeRange.class, new ShowTimeKeyDeserializer());
            objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.registerModule(simpleModule);
        }
        return objectMapper;
    }

}
