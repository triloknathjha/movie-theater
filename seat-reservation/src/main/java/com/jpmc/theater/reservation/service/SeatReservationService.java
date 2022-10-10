package com.jpmc.theater.reservation.service;

import com.jpmc.theater.reservation.dto.Reservation;
import com.jpmc.theater.reservation.dto.Shows;

public interface SeatReservationService {
    Reservation reserve(Reservation reservation) throws Exception;
    Shows listShowSchedule();
}
