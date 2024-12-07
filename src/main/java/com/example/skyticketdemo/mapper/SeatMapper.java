package com.example.skyticketdemo.mapper;

import com.example.skyticketdemo.dto.SeatDTO;
import com.example.skyticketdemo.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SeatMapper {

    @Mapping(source = "flight.flightID", target = "flightID")
    SeatDTO toDto(Seat seat);

    @Mapping(target = "seatID", ignore = true)
    @Mapping(source = "flightID", target = "flight.flightID")
    Seat toEntity(SeatDTO seatDTO);
}

