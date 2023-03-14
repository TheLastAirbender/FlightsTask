package org.skrapivnoy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.skrapivnoy.model.Ticket;
import org.skrapivnoy.model.Tickets;
import org.skrapivnoy.utils.TimeConverter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    public String averageFlightTime(List<Ticket> ticketsList) {
        double averageSeconds = 0.0;

        for (Ticket ticket : ticketsList) {
            // todo как-то вынести в отдельный метод ??
            LocalDateTime departureDateTime = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime());
            LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime());
            arrivalDateTime = arrivalDateTime.plusHours(8);
//            averageHours += Duration.between(departureDateTime, arrivalDateTime).toHours();
            averageSeconds += Duration.between(departureDateTime, arrivalDateTime).toSeconds();
        }
        double avgDuration = averageSeconds / ticketsList.size();
//        return Double.toString(avgDuration);
        return TimeConverter.secondsToHHmmString((int)avgDuration);
    }

    public String percentileAverageFlightTime(List<Ticket> ticketsList, double percentile) {
        Comparator<Ticket> byFlightTime = (t1, t2) -> {
            LocalDateTime t1Departure = LocalDateTime.of(t1.getDepartureDate(), t1.getDepartureTime());
            LocalDateTime t1Arrival = LocalDateTime.of(t1.getArrivalDate(), t1.getArrivalTime()).plusHours(8);
            long t1FlightTime = Duration.between(t1Departure, t1Arrival).toMillis();

            LocalDateTime t2Departure = LocalDateTime.of(t2.getDepartureDate(), t2.getDepartureTime());
            LocalDateTime t2Arrival = LocalDateTime.of(t2.getArrivalDate(), t2.getArrivalTime()).plusHours(8);
            long t2FlightTime = Duration.between(t2Departure, t2Arrival).toMillis();
            return Long.compare(t1FlightTime, t2FlightTime);
        };


        List<Ticket> sortedTickets = ticketsList.stream()
                .sorted(byFlightTime)
                .collect(Collectors.toList());

        int index = (int) Math.ceil(percentile / 100.0 * sortedTickets.size());
        Ticket ticket = sortedTickets.get(index - 1);
//        System.out.println("ticket: " + ticket);
        LocalDateTime ticketDeparture = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime());
        LocalDateTime ticketArrival = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime()).plusHours(8);
//        return Duration.between(ticketDeparture, ticketArrival).toMinutes();
        return TimeConverter.secondsToHHmmString((int)Duration.between(ticketDeparture, ticketArrival).toSeconds());
    }

}
