package com.jpmc.theater.reservation.dto;

import com.jpmc.theater.reservation.enums.DiscountUnit;
import lombok.Data;

import java.io.Serializable;

@Data
public class Discount implements Serializable {
    private double discount;
    private DiscountUnit unit;

    public Discount() {
    }

    public Discount(double discount, DiscountUnit unit) {
        this.discount = discount;
        this.unit = unit;
    }

}
