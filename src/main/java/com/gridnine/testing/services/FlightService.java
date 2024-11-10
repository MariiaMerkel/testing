package com.gridnine.testing.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightService {

    public static LocalDateTime pars(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
            return dateTime;
        } catch (RuntimeException e) {
            System.out.println("Неверный формат даты");
            throw new RuntimeException();
        }
    }
}
