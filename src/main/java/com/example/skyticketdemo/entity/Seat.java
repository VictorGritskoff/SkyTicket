package com.example.skyticketdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Seats", uniqueConstraints = @UniqueConstraint(columnNames = {"FlightID", "SeatNumber"}))
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatID;

    @ManyToOne
    @JoinColumn(name = "FlightID", referencedColumnName = "FlightID")
    private Flight flight;

    @Column(nullable = false, length = 10)
    private String seatNumber;

    @Column(nullable = false, length = 20)
    private String seatClass;

    @Column(nullable = false)
    private Boolean isBooked = false;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double price;

}
