package com.jpmc.theatre.reservation.junit.controller;

import com.jpmc.theater.reservation.controller.SeatReservationController;
import com.jpmc.theater.reservation.dto.Reservation;
import com.jpmc.theater.reservation.enums.ScheduleFormat;
import com.jpmc.theater.reservation.exception.ErrorCode;
import com.jpmc.theater.reservation.service.SeatReservationService;
import com.jpmc.theatre.reservation.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.MessageFormat;

import static com.jpmc.theatre.reservation.utils.TestConstant.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class SeatReservationControllerTest extends BaseControllerTestUtils {
    private MockMvc mockMvc;
    @InjectMocks
    private SeatReservationController seatReservationController;
    @Mock
    private SeatReservationService seatReservationService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = getCustomizedMockMvc(MockMvcBuilders.standaloneSetup(seatReservationController));
    }

    // Method to test get show schedule in json format successfully
    @Test
    public void testShowScheduleJsonSuccess() throws Exception {
        when(seatReservationService.listShowSchedule()).thenReturn(TestUtil.listShowSchedule());
        mockMvc.perform(
                    get(SHOW_SCHEDULE_URL)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        verify(seatReservationService, times(1)).listShowSchedule();
        verifyNoMoreInteractions(seatReservationService);

    }

    // Method to test get show schedule in text format successfully
    @Test
    public void testShowScheduleTextSuccess() throws Exception {
        when(seatReservationService.listShowScheduleInTextFormat()).thenReturn(TestUtil.listShowScheduleInTextFormat());
        mockMvc.perform(
                        get(SHOW_SCHEDULE_URL).queryParam(FORMAT, ScheduleFormat.text.name())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        verify(seatReservationService, times(1)).listShowScheduleInTextFormat();
        verifyNoMoreInteractions(seatReservationService);

    }

    // Method to test seat reservation successfully with sequenceOfTheDay 1 to cover various discount rule
    @Test
    public void testSeatReservationSuccessWithSequenceOfTheDay1() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,1,2);
        when(seatReservationService.reserve(any())).thenReturn(reservation);

        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isOk())
                .andReturn();
        verify(seatReservationService, times(1)).reserve(any());
        verifyNoMoreInteractions(seatReservationService);
    }

    // Method to test seat reservation successfully with  sequenceOfTheDay 2 to cover various discount rule
    @Test
    public void testSeatReservationSuccessWithSequenceOfTheDay2() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,2,2);
        when(seatReservationService.reserve(any())).thenReturn(reservation);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isOk())
                .andReturn();
        verify(seatReservationService, times(1)).reserve(any());
        verifyNoMoreInteractions(seatReservationService);
    }

    // Method to test seat reservation successfully with sequenceOfTheDay 3 to cover various discount rule
    @Test
    public void testSeatReservationSuccessWithSequenceOfTheDay3() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,3,2);
        when(seatReservationService.reserve(any())).thenReturn(reservation);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isOk())
                .andReturn();
        verify(seatReservationService, times(1)).reserve(any());
        verifyNoMoreInteractions(seatReservationService);
    }

    // Method to test seat reservation successfully with sequenceOfTheDay 4 to cover various discount rule
    @Test
    public void testSeatReservationSuccessWithSequenceOfTheDay4() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,3,2);
        when(seatReservationService.reserve(any())).thenReturn(reservation);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isOk())
                .andReturn();
        verify(seatReservationService, times(1)).reserve(any());
        verifyNoMoreInteractions(seatReservationService);
    }

    // Method to test seat reservation successfully with sequenceOfTheDay 5
    @Test
    public void testSeatReservationSuccessWithSequenceOfTheDay5() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,5,2);
        when(seatReservationService.reserve(any())).thenReturn(reservation);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isOk())
                .andReturn();
        verify(seatReservationService, times(1)).reserve(any());
        verifyNoMoreInteractions(seatReservationService);
    }

    // Method to test seat reservation successfully with sequenceOfTheDay 6
    @Test
    public void testSeatReservationSuccessWithSequenceOfTheDay6() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,6,2);
        when(seatReservationService.reserve(any())).thenReturn(reservation);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isOk())
                .andReturn();
        verify(seatReservationService, times(1)).reserve(any());
        verifyNoMoreInteractions(seatReservationService);
    }

    // Method to test seat reservation successfully with sequenceOfTheDay 7
    @Test
    public void testSeatReservationSuccessWithSequenceOfTheDay7() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,7,2);
        when(seatReservationService.reserve(any())).thenReturn(reservation);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isOk())
                .andReturn();
        verify(seatReservationService, times(1)).reserve(any());
        verifyNoMoreInteractions(seatReservationService);
    }

    // Method to test seat reservation with bad request due to missing Customer name
    @Test
    public void testSeatReservationBadRequestDueToMissingCustomerName() throws Exception {
        Reservation reservation = TestUtil.createReservation(null,TEST_CUSTOMER_ID,1,2);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].code", is(ErrorCode.MISSING_FIELD_VALUE.getCode())))
                .andExpect(jsonPath("$.[0].message", is(MessageFormat.format(ErrorCode.MISSING_FIELD_VALUE.getMessage(), "customer.name"))))
                .andReturn();
        verifyNoInteractions(seatReservationService);
    }

    // Method to test seat reservation with bad request due to missing SequenceOfTheDay
    @Test
    public void testSeatReservationBadRequestDueToMissingSequenceOfTheDay() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,null,2);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].code", is(ErrorCode.MISSING_FIELD_VALUE.getCode())))
                .andExpect(jsonPath("$.[0].message", is(MessageFormat.format(ErrorCode.MISSING_FIELD_VALUE.getMessage(), "showing.sequenceOfTheDay"))))
                .andReturn();
        verifyNoInteractions(seatReservationService);
    }

    // Method to test seat reservation with bad request due to missing AudienceCount
    @Test
    public void testSeatReservationBadRequestDueToMissingAudienceCount() throws Exception {
        Reservation reservation = TestUtil.createReservation(TEST_CUSTOMER_NAME,TEST_CUSTOMER_ID,1,null);
        mockMvc.perform(
                        post(SEAT_RESERVATION_URL)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(TestUtil.convertObjectToJsonBytes(reservation))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].code", is(ErrorCode.MISSING_FIELD_VALUE.getCode())))
                .andExpect(jsonPath("$.[0].message", is(MessageFormat.format(ErrorCode.MISSING_FIELD_VALUE.getMessage(), "audienceCount"))))
                .andReturn();
        verifyNoInteractions(seatReservationService);
    }

}