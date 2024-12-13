package com.example.skyticketdemo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketDTO {
    private Long ticketID;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String seatNumber;
    private Double amount;
    private LocalDateTime bookingDate;
    private LocalDateTime departureTime;
}