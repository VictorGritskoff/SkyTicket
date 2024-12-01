package com.example.skyticketdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightID;

    @Column(nullable = false, length = 50)
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "DepartureAirport", referencedColumnName = "AirportID")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "ArrivalAirport", referencedColumnName = "AirportID")
    private Airport arrivalAirport;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "Airline", referencedColumnName = "AirlineID")
    private Airline airline;

}