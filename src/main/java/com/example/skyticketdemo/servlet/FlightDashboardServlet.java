package com.example.skyticketdemo.servlet;

import com.example.skyticketdemo.dto.AirlineDTO;
import com.example.skyticketdemo.dto.AirportDTO;
import com.example.skyticketdemo.dto.FlightDTO;
import com.example.skyticketdemo.mapper.FlightMapper;
import com.example.skyticketdemo.mapper.SeatMapper;
import com.example.skyticketdemo.repository.impl.AirlineRepositoryImpl;
import com.example.skyticketdemo.repository.impl.AirportBaseRepositoryImpl;
import com.example.skyticketdemo.repository.impl.FlightRepositoryImpl;
import com.example.skyticketdemo.repository.impl.SeatRepositoryImpl;
import com.example.skyticketdemo.repository.interfac.SeatRepository;
import com.example.skyticketdemo.service.FlightService;
import com.example.skyticketdemo.service.SeatService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

@WebServlet({"/dashboard/flights", "/dashboard/flights/delete", "/dashboard/seats/add"})
public class FlightDashboardServlet extends HttpServlet {

    private final FlightService flightService;
    private final AirportBaseRepositoryImpl airportRepository;
    private final AirlineRepositoryImpl airlineRepository;
    private final SeatService seatService;

    public FlightDashboardServlet() {
        FlightRepositoryImpl flightRepository = new FlightRepositoryImpl();
        this.airlineRepository = new AirlineRepositoryImpl();
        this.airportRepository = new AirportBaseRepositoryImpl();
        FlightMapper flightMapper = Mappers.getMapper(FlightMapper.class);
        this.flightService = new FlightService(flightRepository, airportRepository,
                airlineRepository, flightMapper);

        SeatRepository seatRepository = new SeatRepositoryImpl();
        SeatMapper seatMapper = Mappers.getMapper(SeatMapper.class);
        this.seatService = new SeatService(seatRepository, seatMapper);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightDTO> flights = flightService.getAllFlights();
        request.setAttribute("flights", flights);
        request.setAttribute("airports", airportRepository.findAll());
        request.setAttribute("airlines", airlineRepository.findAll());

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard-flights.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/dashboard/seats/add".equals(path)) {
            addSeats(request, response);
        } else {
            FlightDTO flightDTO = extractFlightFromRequest(request);
            flightService.createFlight(flightDTO);
            response.sendRedirect(request.getContextPath() + "/dashboard/flights");
        }
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

    private void addSeats(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long flightID = Long.parseLong(request.getParameter("flightID"));
        seatService.addSeats(flightID, request);
        response.sendRedirect(request.getContextPath() + "/dashboard/flights");
    }
}
