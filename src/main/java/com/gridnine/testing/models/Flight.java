package com.gridnine.testing.models;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * Bean that represents a flight.
 */

@Data
public class Flight {

    private List<Segment> segments;

    public Flight(List<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                       .collect(Collectors.joining(" "));
    }
}
