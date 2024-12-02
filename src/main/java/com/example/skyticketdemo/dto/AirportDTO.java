package com.example.skyticketdemo.dto;

import lombok.Data;

@Data
public class AirportDTO {
    private Long airportID;
    private String airportName;
    private String city;
    private String country;
}

