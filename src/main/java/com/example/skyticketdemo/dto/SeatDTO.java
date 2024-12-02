package com.example.skyticketdemo.dto;

import lombok.Data;

@Data
public class SeatDTO {
    private Long seatID;
    private Long flightID;
    private String seatNumber;
    private String seatClass;
    private Boolean isBooked;
    private Double price;
}
