package com.example.skyticketdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;

    @ManyToOne
    @JoinColumn(name = "TicketID", referencedColumnName = "TicketID")
    private Ticket ticket;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(length = 50)
    private String paymentMethod;

}
