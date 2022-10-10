package com.jpmc.theater.reservation.controller;

import com.jpmc.theater.reservation.dto.Reservation;
import com.jpmc.theater.reservation.service.SeatReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeatReservationController {

    @Autowired
    SeatReservationService seatReservationService;

    // Get method for showing movies schedule
    @GetMapping(path = "/shows/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listShowSchedule() {
        return ResponseEntity.ok().body(seatReservationService.listShowSchedule());
    }

    // Post method for seat reservation
    @PostMapping(path = "/seats/reserve", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity seatReserve(@Valid @RequestBody Reservation reservation) throws Exception {
         return ResponseEntity.ok().body(seatReservationService.reserve(reservation));
    }
}
