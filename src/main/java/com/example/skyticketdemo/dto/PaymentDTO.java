package com.example.skyticketdemo.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long paymentID;
    private Long ticketID;
    private Double amount;
    private String paymentDate;
    private String paymentMethod;
}
