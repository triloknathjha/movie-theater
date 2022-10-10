package com.jpmc.theater.reservation.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jpmc.theater.reservation.dto.Discount;
import com.jpmc.theater.reservation.dto.ShowTimeRange;
import com.jpmc.theater.reservation.dto.Showing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jpmc.theater.reservation.constant.Constants.*;

public class InitialRuleSetup {

    /* These discount rule maps should get populated from user entered data either from database or from file because these can change anytime
    for now I am reading these from file placed under resources folder*/

    private static Map<String, Discount> movieSpecialCodeDiscountMap = new HashMap<>();
    private static Map<Integer, Discount> movieSequenceOfTheDayDiscountMap = new HashMap<>();
    private static Map<ShowTimeRange, Discount> movieStartTimeShowRangeDiscountMap = new HashMap<>();
    private static List<Showing> showingList = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(InitialRuleSetup.class);

    // Reading special discounts from file
    public static Discount getSpecialCodeDiscount(String code){
        if(movieSpecialCodeDiscountMap.isEmpty()){
            try (InputStream inputStream = getFileInputStream(SPECIAL_DISCOUNT_FILE_PATH)) {
                    logger.info("Reading file :{}", SPECIAL_DISCOUNT_FILE_PATH);
                    movieSpecialCodeDiscountMap = ApplicationUtils.getObjectMapper().readValue(inputStream, new TypeReference<Map<String, Discount>>() {});
            } catch (IOException iOException) {
                logger.error("File: {} can not be read due to Error {}", SPECIAL_DISCOUNT_FILE_PATH, iOException.getMessage());
            }
        }
        return movieSpecialCodeDiscountMap.get(code);
    }

    // Reading sequence of the day discounts from file
    public static Discount getSequenceOfTheDayDiscount(Integer sequenceOfTheDay){
        if(movieSequenceOfTheDayDiscountMap.isEmpty()){
            try (InputStream inputStream = getFileInputStream(SEQUENCE_OF_DAY_DISCOUNT_FILE_PATH)) {
                logger.info("Reading file :{}", SEQUENCE_OF_DAY_DISCOUNT_FILE_PATH);
                movieSequenceOfTheDayDiscountMap = ApplicationUtils.getObjectMapper().readValue(inputStream, new TypeReference<Map<Integer, Discount>>(){});
            } catch (IOException iOException) {
                logger.error("File: {} can not be read due to Error {}", SEQUENCE_OF_DAY_DISCOUNT_FILE_PATH, iOException.getMessage());
            }
        }
        return movieSequenceOfTheDayDiscountMap.get(sequenceOfTheDay);
    }

    // Reading show start time discounts from file
    public static Map<ShowTimeRange, Discount> getStartTimeShowRangeDiscountMap(){
        if(movieStartTimeShowRangeDiscountMap.isEmpty()){
            try (InputStream inputStream = getFileInputStream(START_TIME_SHOW_RANGE_DISCOUNT_FILE_PATH)) {
                logger.info("Reading file :{}", START_TIME_SHOW_RANGE_DISCOUNT_FILE_PATH);
                movieStartTimeShowRangeDiscountMap = ApplicationUtils.getObjectMapper().readValue(inputStream, new TypeReference<Map<ShowTimeRange, Discount>>() {});
            } catch (IOException iOException) {
                logger.error("File: {} can not be read due to Error {}", START_TIME_SHOW_RANGE_DISCOUNT_FILE_PATH, iOException.getMessage());
            }
        }
        return movieStartTimeShowRangeDiscountMap;
    }

    // Reading movies show details from file
    public static List<Showing> getShowingList(){
        if(showingList.isEmpty()){
            try (InputStream inputStream = getFileInputStream(SHOWING_LIST_FILE_PATH)) {
                logger.info("Reading file :{}", SHOWING_LIST_FILE_PATH);
                showingList = ApplicationUtils.getObjectMapper().readValue(inputStream, new TypeReference<List<Showing>>() {});
            } catch (IOException iOException) {
                logger.error("File: {} can not be read due to Error {}", SHOWING_LIST_FILE_PATH, iOException.getMessage());
            }
        }
        return showingList;
    }
    private static InputStream getFileInputStream(String fileName) {
        return InitialRuleSetup.class.getResourceAsStream(fileName);
    }
}
