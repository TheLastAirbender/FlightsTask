package org.skrapivnoy;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.skrapivnoy.model.Ticket;
import org.skrapivnoy.service.TicketSerializer;
import org.skrapivnoy.service.TicketService;

import java.io.File;
import java.util.List;

public class AppRunner {

    public static void main(String[] args) {

        File ticketsFile = new File("tickets.json");

        TicketSerializer ticketSerializer = new TicketSerializer();
        TicketService ticketService = new TicketService();

        List<Ticket> tickets = ticketSerializer.returnListFromJson(ticketsFile);

        String averageFlightTime = ticketService.averageFlightTime(tickets);
        String percentileFlightTime = ticketService.percentileAverageFlightTime(tickets, 90.0);

        System.out.println("average: " + averageFlightTime);
        System.out.println("90% percentile: " +  percentileFlightTime);

    }
}