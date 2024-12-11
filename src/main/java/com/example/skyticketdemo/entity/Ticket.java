package com.example.skyticketdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "FlightID", referencedColumnName = "FlightID")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "SeatID", referencedColumnName = "SeatID")
    private Seat seat;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

}