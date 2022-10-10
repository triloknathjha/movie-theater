package com.jpmc.theatre.reservation.junit.service;

import com.jpmc.theater.reservation.dto.Reservation;
import com.jpmc.theater.reservation.dto.Shows;
import com.jpmc.theater.reservation.exception.InvalidInputException;
import com.jpmc.theater.reservation.service.SeatReservationServiceImpl;
import com.jpmc.theatre.reservation.utils.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jpmc.theatre.reservation.utils.TestConstant.TEST_CUSTOMER_ID;
import static com.jpmc.theatre.reservation.utils.TestConstant.TEST_CUSTOMER_NAME;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SeatReservationServiceImplTest {

    @InjectMocks
    private SeatReservationServiceImpl seatReservationService;

    // Method to test service show schedule in json format successfully
    @Test
    public void testListShowScheduleJsonSuccess() {
        Shows shows = TestUtil.listShowSchedule();
        Shows showsResponse = seatReservationService.listShowSchedule();
        assertEquals(shows, showsResponse);
    }

    // Method to test service show schedule in text format successfully
    @Test
    public void testListShowScheduleTextSuccess() {
        String shows = TestUtil.listShowScheduleInTextFormat();
        String showsResponse = seatReservationService.listShowScheduleInTextFormat();
        assertEquals(shows, showsResponse);
    }

    // Method to test service seat reservation successfully
    @Test
    public void testSeatReservationSuccess() throws InvalidInputException {
       Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,1,2);
       Reservation reservationResponse = seatReservationService.reserve(reservation);
       assertEquals(reservation.getCustomer().getName(), reservationResponse.getCustomer().getName());
    }

    // Method to test service seat reservation with invalid SequenceOfTheDay
    @Test(expected = InvalidInputException.class)
    public void testSeatReservationFailureDueToInvalidSequenceOfTheDay() throws InvalidInputException {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,100,2);
        seatReservationService.reserve(reservation);
    }
}
