package com.jpmc.theater.reservation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
public class Customer {

    @NotBlank(message = "{invalid.string}")
    private String name;

    private String id;

    public Customer() {
    }
    public Customer(String name, String id) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "name: " + name;
    }
}