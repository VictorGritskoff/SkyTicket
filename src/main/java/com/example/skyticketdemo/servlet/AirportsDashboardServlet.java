package com.example.skyticketdemo.servlet;

import com.example.skyticketdemo.dto.AirportDTO;
import com.example.skyticketdemo.repository.impl.AirportRepositoryImpl;
import com.example.skyticketdemo.mapper.AirportMapper;
import com.example.skyticketdemo.service.AirportService;
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
@WebServlet({"/dashboard/airport", "/dashboard/airport/delete"})
public class AirportsDashboardServlet extends HttpServlet {

    private final AirportService airportService;

    public AirportsDashboardServlet() {
        AirportMapper airportMapper = Mappers.getMapper(AirportMapper.class);
        this.airportService = new AirportService(new AirportRepositoryImpl(), airportMapper);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<AirportDTO> airports = airportService.getAllAirports();
            request.setAttribute("airports", airports);
        } catch (Exception e) {
            log.error("Ошибка при загрузке списка аэропортов: ", e);
            request.setAttribute("errorMessage", "Ошибка при загрузке списка аэропортов.");
        }
        String jspPage = "/WEB-INF/views/admin/dashboard-airports.jsp";
        request.getRequestDispatcher(jspPage).forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            log.error("Параметр id не передан для удаления аэропорта.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID is missing.");
            return;
        }
        try {
            Long id = Long.parseLong(idParam);
            airportService.deleteAirport(id);
            log.info("Аэропорт с id {} успешно удалён.", id);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Airport deleted successfully.");
        } catch (NumberFormatException e) {
            log.error("Неверный формат id: {}", idParam, e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid ID format.");
        } catch (EntityNotFoundException e) {
            log.error("Не удалось найти аэропорт для удаления: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Airport not found.");
        } catch (Exception e) {
            log.error("Ошибка при удалении аэропорта: ", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error deleting airport.");
        }
    }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String airportName = request.getParameter("airportName");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String idParam = request.getParameter("id");

            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setAirportName(airportName);
            airportDTO.setCity(city);
            airportDTO.setCountry(country);

            try {
                if (idParam != null && !idParam.isEmpty()) {
                    Long id = Long.parseLong(idParam);
                    airportService.updateAirport(id, airportDTO);
                } else {
                    airportService.createAirport(airportDTO);
                }
                response.sendRedirect(request.getContextPath() + "/dashboard/airport");
            } catch (Exception e) {
                log.error("Ошибка при сохранении или обновлении аэропорта: ", e);
                request.setAttribute("errorMessage", "Ошибка при сохранении или обновлении аэропорта. Пожалуйста, попробуйте снова.");
                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard-airports.jsp").forward(request, response);
            }
        }
}
