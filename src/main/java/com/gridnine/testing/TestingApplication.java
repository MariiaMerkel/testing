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
                System.out.println("\nВведите, тип сортировки: 1 - по датам, 2 - по пересадкам");
                Integer type = scanner.nextInt();
                String empty = scanner.nextLine();
                if (type == 1) {

                    System.out.println("\nВведите, если требуется, минимальную дату вылета в формате 'ГГГГ-ММ-ДД чч:мм', а иначе нажмите 'Enter'");
                    String arrivalDateMinStr = scanner.nextLine();
                    LocalDateTime arrivalDateMin = null;
                    if (!arrivalDateMinStr.isEmpty()) {
                        arrivalDateMin = FlightService.pars(arrivalDateMinStr);
                    }
                    System.out.println(FlightBuilder.filterByArrivalDateMin(arrivalDateMin));

                    System.out.println("\nВведите, если требуется, максимальную дату вылета в формате 'ГГГГ-ММ-ДД чч:мм', а иначе нажмите 'Enter'");
                    String arrivalDateMaxStr = scanner.nextLine();
                    LocalDateTime arrivalDateMax = null;
                    if (!arrivalDateMaxStr.isEmpty()) {
                        arrivalDateMax = FlightService.pars(arrivalDateMaxStr);
                    }
                    System.out.println(FlightBuilder.filterByArrivalDateMax(arrivalDateMax));

                    System.out.println("\nВведите, если требуется, минимальную дату прилёта в формате 'ГГГГ-ММ-ДД чч:мм', а иначе нажмите 'Enter'");
                    String departureDateMinStr = scanner.nextLine();
                    LocalDateTime departureDateMin = null;
                    if (!departureDateMinStr.isEmpty()) {
                        departureDateMin = FlightService.pars(departureDateMinStr);
                    }
                    System.out.println(FlightBuilder.filterByDepartureDateMin(departureDateMin));

                    System.out.println("\nВведите, если требуется, максимальную дату прилёта в формате 'ГГГГ-ММ-ДД чч:мм', а иначе нажмите 'Enter'");
                    String departureDateMaxStr = scanner.nextLine();
                    LocalDateTime departureDateMax = null;
                    if (!departureDateMaxStr.isEmpty()) {
                        departureDateMax = FlightService.pars(departureDateMaxStr);
                    }
                    System.out.println(FlightBuilder.filterByDepartureDateMax(departureDateMax));

                } else if (type == 2) {
                    System.out.println("\nВведите, минимальное количество часов пересадки");
                    Integer hours = scanner.nextInt();

                    System.out.println(FlightBuilder.filter(hours));
                } else {
                    System.out.println("Неверно введён тип сортировки");
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                continue;
            }
        }
    }
}
