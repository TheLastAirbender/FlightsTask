package org.skrapivnoy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.skrapivnoy.model.Ticket;
import org.skrapivnoy.model.Tickets;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TicketSerializer {
    private final ObjectMapper mapper;

    public TicketSerializer() {
        ObjectMapper mapper = new ObjectMapper();
        // без JavaTimeModule и snakecase нормально не хочет маппится почему-то
        mapper.registerModule(new JavaTimeModule());
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        this.mapper = mapper;
    }

    public List<Ticket> returnListFromJson(File file) {
        try {
            Tickets ticketsArray = mapper.readValue(file, Tickets.class);
//            безуспешные попытки запихать сразу в List
//            Ticket ticketsArray = mapper.readValue(file, Ticket.class); // не работает
//            ObjectReader reader = mapper.readerForListOf(Ticket.class);
//            ObjectReader reader = mapper.readerFor(new TypeReference<List<Ticket>>() {});
//            List<Ticket> ticketList = reader.readValue(file);
            return Arrays.asList(ticketsArray.getTickets());
//            return ticketList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
