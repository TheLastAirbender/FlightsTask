package org.skrapivnoy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
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
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

public class TicketService {
    public String averageFlightTime(List<Ticket> ticketsList) {
        double totalDurationSeconds = 0.0;

        for (Ticket ticket : ticketsList) {
            //todo как-то вынести в отдельный метод ??
            LocalDateTime departureDateTime = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime());
            LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime());
            arrivalDateTime = arrivalDateTime.plusHours(8);
//            averageHours += Duration.between(departureDateTime, arrivalDateTime).toHours();
//            System.out.println((int)Duration.between(departureDateTime, arrivalDateTime).toSeconds());
//            System.out.println(TimeConverter.secondsToHHmmString((int)Duration.between(departureDateTime, arrivalDateTime).toSeconds()));
            totalDurationSeconds += Duration.between(departureDateTime, arrivalDateTime).toSeconds();
        }
        double avgDuration = totalDurationSeconds / ticketsList.size();
//        return Double.toString(avgDuration);
        return TimeConverter.secondsToHHmmString((int)avgDuration);
    }

    public String percentileAverageFlightTime(List<Ticket> ticketsList, double percentile) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Ticket ticket : ticketsList){
            LocalDateTime departureDateTime = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime());
            LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime()).plusHours(8);
            double durationInSeconds = Duration.between(departureDateTime, arrivalDateTime).toSeconds();
            stats.addValue(durationInSeconds);
        }
        return TimeConverter.secondsToHHmmString((int) stats.getPercentile(90));
    }

}
