package com.example.skyticketdemo.mapper;

import com.example.skyticketdemo.dto.AirportDTO;
import com.example.skyticketdemo.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AirportMapper {

    @Mapping(source = "airportID", target = "airportID")
    @Mapping(source = "airportName", target = "airportName")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "country", target = "country")
    AirportDTO toDto(Airport airport);

    @Mapping(target = "airportID", ignore = true)
    Airport toEntity(AirportDTO airportDTO);
}
