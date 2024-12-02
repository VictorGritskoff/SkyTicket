package com.example.skyticketdemo.dto;

import lombok.Data;

@Data
public class FlightDTO {
    private Long flightID;
    private String flightNumber;
    private AirportDTO departureAirport;
    private AirportDTO arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private AirlineDTO airline;
}