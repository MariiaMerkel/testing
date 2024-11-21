package com.gridnine.testing.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Bean that represents a flight segment.
 */
public class Segment {

    private final LocalDateTime arrivalDate;

    private final LocalDateTime departureDate;

    public Segment(LocalDateTime arrivalDate, LocalDateTime departureDate) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + arrivalDate.format(fmt) + '|' + departureDate.format(fmt)
                + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(arrivalDate, segment.arrivalDate) && Objects.equals(departureDate, segment.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalDate, departureDate);
    }
}
