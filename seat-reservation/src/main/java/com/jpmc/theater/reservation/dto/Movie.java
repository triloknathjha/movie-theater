package com.jpmc.theater.reservation.dto;

import com.jpmc.theater.reservation.Utils.TicketPriceCalculator;
import lombok.Data;

import java.util.Objects;

@Data
public class Movie {
    private String title;
    private String description;
    private int runningTime;
    private double ticketPrice;
    private String specialCode;

    public Movie() {
    }

    public Movie(String title, String description, int runningTime, double ticketPrice, String specialCode) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    /**
     * calculateTicketPrice calculates the ticket price by reducing if any discount
     * @param showing
     * @return ticket price after discount
     */
    public double calculateTicketPrice(Showing showing) {
        return TicketPriceCalculator.calculateTicketPrice(specialCode, ticketPrice, showing.getShowStartTime(), showing.getSequenceOfTheDay());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", runningTime=" + runningTime +
                ", ticketPrice=" + ticketPrice +
                ", specialCode=" + specialCode +
                '}';
    }
}