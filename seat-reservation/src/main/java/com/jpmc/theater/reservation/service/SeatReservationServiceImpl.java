package com.jpmc.theater.reservation.service;

import com.jpmc.theater.reservation.Utils.ApplicationUtils;
import com.jpmc.theater.reservation.Utils.InitialRuleSetup;
import com.jpmc.theater.reservation.dto.Reservation;
import com.jpmc.theater.reservation.dto.Showing;
import com.jpmc.theater.reservation.dto.Shows;
import com.jpmc.theater.reservation.exception.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import static com.jpmc.theater.reservation.constant.Constants.ERROR_INVALID_SEQUENCE_OF_DAY;

@Service
public class SeatReservationServiceImpl implements  SeatReservationService{

    private List<Showing> schedule;
    private  final Logger logger = LogManager.getLogger(getClass());

    @PostConstruct
    public void populateSchedule() {
        // Populates the showing with movie from MovieShowingFeed.json file
        schedule = InitialRuleSetup.getShowingList();
    }

    /**
     * reserve method is used to reserve the show booking
     * @return reservation
     */
    public Reservation reserve(Reservation reservation) throws InvalidInputException {
        long time = System.currentTimeMillis();
        int sequence =  reservation.getShowing().getSequenceOfTheDay();
        logger.debug("Starts: reserving");
        Optional<Showing> showing= listSchedule().stream().filter(show -> (show.getSequenceOfTheDay() == sequence)).findFirst();
        if(!showing.isPresent()){
            logger.info("No matching show sequence found for sequence :{}",sequence);
            throw new InvalidInputException(ERROR_INVALID_SEQUENCE_OF_DAY);
        }
        logger.debug("Ends: reserving in {} ms", ApplicationUtils.getTimeDifference(time));
        return new Reservation(reservation.getCustomer(), showing.get(), reservation.getAudienceCount());
    }

    /**
     * listShowSchedule method is used to reserve the show booking
     * @return Shows
     */
    public Shows listShowSchedule() {
        return new Shows(listSchedule());
    }

    /**
     * listShowScheduleInTextFormat method is used to show the schedule in text format
     * @return String
     */

    public String listShowScheduleInTextFormat(){
        StringBuilder scheduleText= new StringBuilder();
        for(Showing show:listSchedule()){
            scheduleText.append(MessageFormat.format("{0}: {1} {2} {3} ${4} {5}", show.getSequenceOfTheDay(), show.getShowStartTime(), show.getMovie().getTitle(), show.getMovie().getRunningTime(), show.getDiscountedTicketPrice(), System.lineSeparator()));
        }
        return scheduleText.toString();
    }


    private List<Showing> listSchedule() {
        if(schedule == null){
            schedule = InitialRuleSetup.getShowingList();
        }
        return schedule;
    }

}
