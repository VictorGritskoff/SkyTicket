package com.example.skyticketdemo.mapper;

import com.example.skyticketdemo.dto.TicketDTO;
import com.example.skyticketdemo.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TicketMapper {

    @Mapping(source = "ticketID", target = "ticketID")
    @Mapping(source = "flight.flightNumber", target = "flightNumber")
    @Mapping(source = "flight.departureAirport.airportName", target = "departureAirport")
    @Mapping(source = "flight.arrivalAirport.airportName", target = "arrivalAirport")
    @Mapping(source = "seat.seatNumber", target = "seatNumber")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "bookingDate", target = "bookingDate")
    @Mapping(source = "flight.departureTime", target = "departureTime")
    TicketDTO toDto(Ticket ticket);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "seat", ignore = true)
    Ticket toEntity(TicketDTO ticketDTO);

    default List<TicketDTO> toDTOList(List<Ticket> tickets) {
        return tickets.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
