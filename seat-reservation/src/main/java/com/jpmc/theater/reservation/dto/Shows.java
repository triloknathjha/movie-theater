package com.jpmc.theater.reservation.dto;

import lombok.Data;

import java.util.List;

@Data
public class Shows {
    private List<Showing> shows;

    public Shows(List<Showing> shows) {
        this.shows = shows;
    }
}
