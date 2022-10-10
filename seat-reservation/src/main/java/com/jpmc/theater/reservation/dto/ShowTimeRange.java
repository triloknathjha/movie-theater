package com.jpmc.theater.reservation.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@Data
public class ShowTimeRange implements Serializable {
    private LocalTime startTime;
    private LocalTime endTime;

    public ShowTimeRange() {
    }

    public ShowTimeRange(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }


    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowTimeRange that = (ShowTimeRange) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public String toString() {
        return "ShowTimeRangeDto{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
