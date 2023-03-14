package org.skrapivnoy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime departureTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate arrivalDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime arrivalTime;
    private String carrier;
    private int stops;
    private int price;

}
