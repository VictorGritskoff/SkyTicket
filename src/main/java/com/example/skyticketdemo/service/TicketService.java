package com.example.skyticketdemo.service;


import com.example.skyticketdemo.dto.TicketDTO;
import com.example.skyticketdemo.entity.Ticket;
import com.example.skyticketdemo.mapper.TicketMapper;
import com.example.skyticketdemo.repository.impl.TicketRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class TicketService {
    private final TicketRepositoryImpl ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepositoryImpl ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    public List<TicketDTO> findTicketsByUser(Long userID) {
        log.info("Fetching tickets for userID: {}", userID);
        List<Ticket> tickets = ticketRepository.findByUser_UserID(userID);

        return ticketMapper.toDTOList(tickets);
    }

    public List<LocalDateTime> findDepartureTimesByUser(Long userID) {
        log.info("Fetching departure times for userID: {}", userID);
        return ticketRepository.findDepartureTimesByUser(userID);
    }
}
