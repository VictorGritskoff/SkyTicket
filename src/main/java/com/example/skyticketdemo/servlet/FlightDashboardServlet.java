package com.example.skyticketdemo.servlet;

import com.example.skyticketdemo.dto.AirlineDTO;
import com.example.skyticketdemo.dto.AirportDTO;
import com.example.skyticketdemo.dto.FlightDTO;
import com.example.skyticketdemo.mapper.AirportMapper;
import com.example.skyticketdemo.mapper.FlightMapper;
import com.example.skyticketdemo.repository.impl.AirlineRepositoryImpl;
import com.example.skyticketdemo.repository.impl.AirportRepositoryImpl;
import com.example.skyticketdemo.repository.impl.FlightRepositoryImpl;
import com.example.skyticketdemo.repository.interfac.AirlineRepository;
import com.example.skyticketdemo.repository.interfac.AirportRepository;
import com.example.skyticketdemo.repository.interfac.FlightRepository;
import com.example.skyticketdemo.service.AirlineService;
import com.example.skyticketdemo.service.AirportService;
import com.example.skyticketdemo.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

@WebServlet({"/dashboard/flights", "/dashboard/flights/delete"})
public class FlightDashboardServlet extends HttpServlet {

    private final String PAGE = "/WEB-INF/views/admin/dashboard-flights.jsp";

    private final FlightService flightService;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;

    public FlightDashboardServlet() {
        FlightRepository flightRepository = new FlightRepositoryImpl();
        this.airlineRepository = new AirlineRepositoryImpl();
        this.airportRepository = new AirportRepositoryImpl();
        FlightMapper flightMapper = Mappers.getMapper(FlightMapper.class);
        this.flightService = new FlightService(flightRepository, airportRepository,
                                               airlineRepository, flightMapper);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightDTO> flights = flightService.getAllFlights();
        request.setAttribute("flights", flights);
        request.setAttribute("airports", airportRepository.findAll());
        request.setAttribute("airlines", airlineRepository.findAll());

        request.getRequestDispatcher(PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlightDTO flightDTO = extractFlightFromRequest(request);
        flightService.createFlight(flightDTO);
        response.sendRedirect(request.getContextPath() + "/dashboard/flights");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong( request.getParameter("id"));
        flightService.deleteFlight(id);
    }

    private FlightDTO extractFlightFromRequest(HttpServletRequest request) {
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.setFlightNumber(request.getParameter("flightNumber"));

        AirportDTO departureAirport = new AirportDTO();
        departureAirport.setAirportID(Long.parseLong(request.getParameter("departureAirport")));
        flightDTO.setDepartureAirport(departureAirport);

        AirportDTO arrivalAirport = new AirportDTO();
        arrivalAirport.setAirportID(Long.parseLong(request.getParameter("arrivalAirport")));
        flightDTO.setArrivalAirport(arrivalAirport);

        flightDTO.setDepartureTime(request.getParameter("departureTime"));

        flightDTO.setArrivalTime(request.getParameter("arrivalTime"));

        AirlineDTO airline = new AirlineDTO();
        airline.setAirlineID(Long.parseLong(request.getParameter("airline")));
        flightDTO.setAirline(airline);

        return flightDTO;
    }
}
