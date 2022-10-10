package com.jpmc.theatre.reservation.junit.controller;

import com.jpmc.theater.reservation.exception.GlobalControllerExceptionHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;


public class BaseControllerTestUtils {
    protected MockMvc getCustomizedMockMvc(StandaloneMockMvcBuilder standaloneMockMvcBuilder){
            return standaloneMockMvcBuilder.setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();
    }

}