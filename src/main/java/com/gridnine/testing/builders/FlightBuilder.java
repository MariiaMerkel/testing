package com.gridnine.testing.builders;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import com.gridnine.testing.services.FlightService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlightBuilder {

    public static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                //A normal multi segment flight
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                             threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                //A flight departing in the past
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                //A flight that departs before it arrives
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                //A flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                             threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                             threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                             threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }

    public static List<Flight> filter(LocalDateTime departureDateMin, LocalDateTime departureDateMax, LocalDateTime arrivalDateMin, LocalDateTime arrivalDateMax) {

        List<Flight> flights = createFlights();
        return flights.stream().filter(f -> (
                f.getSegments().get(0).getDepartureDate().isAfter(departureDateMin) &&
                f.getSegments().get(0).getDepartureDate().isBefore(departureDateMax) &&
                f.getSegments().get(0).getDepartureDate().isAfter(arrivalDateMin) &&
                f.getSegments().get(0).getDepartureDate().isBefore(arrivalDateMax))).collect(Collectors.toList());
    }
}
