package com.example.skyticketdemo.servlet;

import com.example.skyticketdemo.entity.Flight;
import com.example.skyticketdemo.repository.impl.FlightRepositoryImpl;
import com.example.skyticketdemo.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/searchFlights")
public class FlightSearchServlet extends HttpServlet {

    private FlightService flightService;

    @Override
    public void init() throws ServletException {
        this.flightService = new FlightService(new FlightRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String departureCountry = request.getParameter("departureCountry");
        String arrivalCountry = request.getParameter("arrivalCountry");
        String ticketsParam = request.getParameter("tickets");
        String departureDateParam = request.getParameter("departureDate");

        Integer tickets = null;
        LocalDate departureDate = null;

        try {
            if (ticketsParam != null && !ticketsParam.isEmpty()) {
                tickets = Integer.parseInt(ticketsParam);
            }
            if (departureDateParam != null && !departureDateParam.isEmpty()) {
                departureDate = LocalDate.parse(departureDateParam);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Неверный формат данных!");
            request.getRequestDispatcher("/WEB-INF/views/user/main.jsp").forward(request, response);
            return;
        }
        try {
            List<Flight> flights = flightService.findFlights(departureCountry, arrivalCountry, departureDate, tickets);

            if (flights.isEmpty()) {
                request.getRequestDispatcher("/WEB-INF/views/user/no_results_found.jsp").forward(request, response);
            } else {
                request.setAttribute("flights", flights);
                request.getRequestDispatcher("/WEB-INF/views/user/flight_results.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/user/main.jsp").forward(request, response);
        }
    }

}
