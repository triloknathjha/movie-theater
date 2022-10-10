package com.jpmc.theater.reservation.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Valid
public class Reservation {
    @Valid
    private Customer customer;
    @Valid
    private Showing showing;
    @NotNull(message = "{invalid.integer}")
    private Integer audienceCount;

    private double totalTicketPrice;

    public Reservation() {
    }

    public Reservation(Customer customer, Showing showing, Integer audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer.toString() +
                ", showing=" + showing.toString() +
                ", audienceCount=" + audienceCount +
                ", totalTicketPrice=" + getTotalTicketPrice() +
                '}';
    }

    public double getTotalTicketPrice(){
        if(audienceCount == null)
            return 0;
        return showing.getDiscountedTicketPrice() * audienceCount;
    }

}