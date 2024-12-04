package com.example.skyticketdemo.mapper;

import com.example.skyticketdemo.dto.AirlineDTO;
import com.example.skyticketdemo.entity.Airline;
import com.example.skyticketdemo.entity.Airport;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AirlineMapper {

    @Mapping(source = "airlineID", target = "airlineID")
    @Mapping(source = "airlineName", target = "airlineName")
    @Mapping(source = "country", target = "country")
    AirlineDTO toDto(Airline airline);

    @Mapping(target = "airlineID", ignore = true)
    Airline toEntity(AirlineDTO airlineDTO);
}
