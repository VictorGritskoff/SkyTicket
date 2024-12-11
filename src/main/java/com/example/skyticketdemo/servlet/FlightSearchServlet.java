package com.example.skyticketdemo.servlet;

import com.example.skyticketdemo.entity.Flight;
import com.example.skyticketdemo.repository.impl.FlightRepositoryImpl;
import com.example.skyticketdemo.repository.impl.SeatRepositoryImpl;
import com.example.skyticketdemo.service.FlightService;
import com.example.skyticketdemo.service.SeatService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/public/searchFlights")
public class FlightSearchServlet extends HttpServlet {

    private FlightService flightService;
    private SeatService seatService;


    @Override
    public void init() throws ServletException {
        this.flightService = new FlightService(new FlightRepositoryImpl());
        this.seatService = new SeatService(new SeatRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String departureCountry = request.getParameter("departureCountry");
            String arrivalCountry = request.getParameter("arrivalCountry");
            Integer tickets = parseIntegerParameter(request.getParameter("tickets"));
            LocalDate departureDate = parseDateParameter(request.getParameter("departureDate"));

            List<Flight> flights = flightService.findFlights(departureCountry, arrivalCountry, departureDate, tickets);
            if (flights.isEmpty()) {
                forwardToPage(request, response, "/WEB-INF/views/user/no_results_found.jsp");
                return;
            }
            for (Flight flight : flights) {
                Double minPrice = seatService.getSeatPriceForFlightId(flight.getFlightID());
                flight.setMinPrice(minPrice);
            }
            request.setAttribute("flights", flights);
            forwardToPage(request, response, "/WEB-INF/views/user/flight_results.jsp");

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            forwardToPage(request, response, "/WEB-INF/views/user/main.jsp");
        }
    }

    private Integer parseIntegerParameter(String param) {
        try {
            return param != null && !param.isEmpty() ? Integer.parseInt(param) : null;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат количества билетов!");
        }
    }

    private LocalDate parseDateParameter(String param) {
        try {
            return param != null && !param.isEmpty() ? LocalDate.parse(param) : null;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты!");
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

}
