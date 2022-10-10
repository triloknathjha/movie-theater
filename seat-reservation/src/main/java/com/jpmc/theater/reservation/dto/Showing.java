package com.jpmc.theater.reservation.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
public class Showing {
    private Movie movie;
    @NotNull(message = "{invalid.integer}")
    private Integer sequenceOfTheDay;

    private LocalDateTime showStartTime;

    public Showing() {
    }

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public void setSequenceOfTheDay(Integer sequenceOfTheDay) {
        this.sequenceOfTheDay = sequenceOfTheDay;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        if(showStartTime != null) {
            String time[] = showStartTime.split(",");
            this.showStartTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
        }
    }

    public double getDiscountedTicketPrice() {
      if(movie == null)
          return 0;
       return  movie.calculateTicketPrice(this); // it should return the payable ticket price per head, so I called calculateTicketPrice
    }

    @Override
    public String toString() {
        return "Showing{" +
                "movie=" + movie +
                ", sequenceOfTheDay=" + sequenceOfTheDay +
                ", showStartTime=" + showStartTime +
                '}';
    }
}
