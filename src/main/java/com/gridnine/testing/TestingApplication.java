package com.gridnine.testing;

import com.gridnine.testing.builders.FlightBuilder;
import com.gridnine.testing.services.FlightService;
import java.time.LocalDateTime;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {

                System.out.println("\nВведите минимальную дату вылета в формате 'ГГГГ-ММ-ДД чч:мм'");
                String departureDateMinStr = scanner.nextLine();
                LocalDateTime departureDateMin = FlightService.pars(departureDateMinStr);

                System.out.println("\nВведите максимальную дату вылета в формате 'ГГГГ-ММ-ДД чч:мм'");
                String departureDateMaxStr = scanner.nextLine();

                LocalDateTime departureDateMax = FlightService.pars(departureDateMaxStr);

                System.out.println("\nВведите минимальную дату прилёта в формате 'ГГГГ-ММ-ДД чч:мм'");
                String arrivalDateMinStr = scanner.nextLine();
                LocalDateTime arrivalDateMin = FlightService.pars(arrivalDateMinStr);

                System.out.println("\nВведите максимальную дату прилёта в формате 'ГГГГ-ММ-ДД чч:мм'");
                String arrivalDateMaxStr = scanner.nextLine();
                LocalDateTime arrivalDateMax = FlightService.pars(arrivalDateMaxStr);

                FlightBuilder.filter(departureDateMin, departureDateMax, arrivalDateMin, arrivalDateMax);
            } catch (Exception e) {
                continue;
            }


        }

    }

}
