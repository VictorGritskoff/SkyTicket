package com.example.skyticketdemo.servlet;

import com.example.skyticketdemo.dto.AirportDTO;
import com.example.skyticketdemo.repository.impl.AirportRepositoryImpl;
import com.example.skyticketdemo.mapper.AirportMapper;
import com.example.skyticketdemo.service.AirportService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet({"/admin/dashboard/airport", "/admin/dashboard/airport/delete"})
public class AirportsDashboardServlet extends HttpServlet {

    private final AirportService airportService;

    public AirportsDashboardServlet() {
        AirportMapper airportMapper = Mappers.getMapper(AirportMapper.class);
        this.airportService = new AirportService(new AirportRepositoryImpl(), airportMapper);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<AirportDTO> airports = airportService.getAllAirports();
            request.setAttribute("airports", airports);
            request.setAttribute("errorMessage", "Ошибка при загрузке списка аэропортов.");

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard-airports.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong( request.getParameter("id"));
        airportService.deleteAirport(id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AirportDTO airportDTO = extractAirportFromRequest(req);
        if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            Long id = Long.parseLong(req.getParameter("id"));
            airportService.updateAirport(id, airportDTO);
        } else {
            airportService.createAirport(airportDTO);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard/airport");
    }

    private AirportDTO extractAirportFromRequest(HttpServletRequest req) {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setAirportName(req.getParameter("airportName"));
        airportDTO.setCity(req.getParameter("city"));
        airportDTO.setCountry(req.getParameter("country"));
        return airportDTO;
    }
}
