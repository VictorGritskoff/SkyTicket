package com.example.skyticketdemo.mapper;

import com.example.skyticketdemo.dto.FlightDTO;
import com.example.skyticketdemo.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface FlightMapper {
    @Mapping(source = "departureAirport", target = "departureAirport")
    @Mapping(source = "arrivalAirport", target = "arrivalAirport")
    @Mapping(source = "airline", target = "airline")
    @Mapping(source = "departureTime", target = "departureTime", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "arrivalTime", target = "arrivalTime", dateFormat = "yyyy-MM-dd HH:mm")
    FlightDTO toDto(Flight flight);

    @Mapping(target = "flightID", ignore = true)
    @Mapping(source = "departureAirport", target = "departureAirport")
    @Mapping(source = "arrivalAirport", target = "arrivalAirport")
    @Mapping(source = "airline", target = "airline")
    @Mapping(source = "departureTime", target = "departureTime", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "arrivalTime", target = "arrivalTime", dateFormat = "yyyy-MM-dd HH:mm")
    Flight toEntity(FlightDTO flightDTO);

}
