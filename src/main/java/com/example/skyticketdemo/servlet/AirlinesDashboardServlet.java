package com.example.skyticketdemo.servlet;

import com.example.skyticketdemo.dto.AirlineDTO;
import com.example.skyticketdemo.dto.AirportDTO;
import com.example.skyticketdemo.mapper.AirlineMapper;
import com.example.skyticketdemo.repository.impl.AirlineRepositoryImpl;
import com.example.skyticketdemo.service.AirlineService;
import jakarta.persistence.EntityNotFoundException;
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
@WebServlet({"/dashboard/airlines", "/dashboard/airlines/delete"})
public class AirlinesDashboardServlet extends HttpServlet {

    private final String PAGE = "/WEB-INF/views/admin/dashboard-airlines.jsp";
    private final AirlineService airlineService;

    public AirlinesDashboardServlet() {
        AirlineMapper airlineMapper = Mappers.getMapper(AirlineMapper.class);
        this.airlineService = new AirlineService(new AirlineRepositoryImpl(), airlineMapper);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<AirlineDTO> airlines = airlineService.getAllAirlines();
            System.out.println(airlines);
            request.setAttribute("airlines", airlines);
        } catch (Exception e) {
            log.error("Ошибка при загрузке списка авиалиний: ", e);
            request.setAttribute("errorMessage", "Ошибка при загрузке списка авиалиний.");
        }

        request.getRequestDispatcher(PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String airlineName = request.getParameter("airlineName");
        String country = request.getParameter("country");
        String idParam = request.getParameter("id");

        AirlineDTO airlineDTO = new AirlineDTO();
        airlineDTO.setAirlineName(airlineName);
        airlineDTO.setCountry(country);

        try {
            if (idParam != null && !idParam.isEmpty()) {
                Long id = Long.parseLong(idParam);
                airlineService.updateAirline(id, airlineDTO);
            } else {
                airlineService.createAirline(airlineDTO);
            }
            response.sendRedirect(request.getContextPath() + "/dashboard/airlines");
        } catch (Exception e) {
            log.error("Ошибка при сохранении или обновлении авиалинии: ", e);
            request.setAttribute("errorMessage", "Ошибка при сохранении или обновлении авиалинии. Пожалуйста, попробуйте снова.");
            request.getRequestDispatcher(PAGE).forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        airlineService.deleteAirline(id);
    }
}
