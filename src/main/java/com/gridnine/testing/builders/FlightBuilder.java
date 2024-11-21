package com.gridnine.testing.builders;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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

    public static ArrayList<Flight> filterByArrivalDateMin(LocalDateTime arrivalDateMin) {

        List<Flight> flights = createFlights();
        ArrayList<Flight> result = new ArrayList<>(flights);

        if (arrivalDateMin != null) {

            for (int i = 0; i < flights.size(); i++) {
                int index = i;
                List<Segment> segments = flights.get(i).getSegments();
                segments.forEach(s -> {
                    LocalDateTime arrival = s.getArrivalDate();
                    if (s.getArrivalDate().isBefore(arrivalDateMin)) {
                        result.remove(flights.get(index));
                    }
                });
            }
        }
        return result;
    }

    public static ArrayList<Flight> filterByArrivalDateMax(LocalDateTime arrivalDateMax) {
        List<Flight> flights = createFlights();
        ArrayList<Flight> result = new ArrayList<>(flights);
        if (arrivalDateMax != null) {

            for (int i = 0; i < flights.size(); i++) {
                int index = i;
                flights.get(i).getSegments().forEach(s -> {
                    if (s.getArrivalDate().isAfter(arrivalDateMax)) {
                        result.remove(flights.get(index));
                    }
                });
            }
        }
        return result;
    }

    public static ArrayList<Flight> filterByDepartureDateMin(LocalDateTime departureDateMin) {
        List<Flight> flights = createFlights();
        ArrayList<Flight> result = new ArrayList<>(flights);

        if (departureDateMin != null) {

            for (int i = 0; i < flights.size(); i++) {
                int index = i;
                flights.get(i).getSegments().forEach(s -> {
                    if (s.getDepartureDate().isBefore(departureDateMin)) {
                        result.remove(flights.get(index));
                    }
                });
            }
        }
        return result;
    }

    public static ArrayList<Flight> filterByDepartureDateMax(LocalDateTime departureDateMax) {
        List<Flight> flights = createFlights();
        ArrayList<Flight> result = new ArrayList<>(flights);

        if (departureDateMax != null) {

            for (int i = 0; i < flights.size(); i++) {
                int index = i;
                flights.get(i).getSegments().forEach(s -> {
                    if (s.getDepartureDate().isAfter(departureDateMax)) {
                        result.remove(flights.get(index));
                    }
                });
            }
        }
        return result;
    }

    public static List<Flight> filter(Integer hours) {
        List<Flight> flights = createFlights();
        flights = flights.stream().filter(f -> (
                f.getSegments().size() > 1)).collect(Collectors.toList());
        ArrayList<Flight> result = new ArrayList<>(flights);
        for (int i = 0; i < flights.size(); i++) {
            int index = i;
            AtomicInteger transferHours = new AtomicInteger();
            flights.get(i).getSegments().forEach(s -> {
                transferHours.addAndGet(Duration.between(s.getArrivalDate().toLocalTime(), s.getDepartureDate().toLocalTime()).toHoursPart());
            });
            if (hours < transferHours.get()) {
                result.remove(flights.get(index));
            }
        }
        return flights;
    }
}
