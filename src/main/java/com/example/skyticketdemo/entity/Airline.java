package com.example.skyticketdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Airlines")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineID;

    @Column(nullable = false, length = 255)
    private String airlineName;

    @Column(nullable = false, length = 255)
    private String country;

}