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

    private final String PAGE = "/WEB-INF/views/admin/dashboard-airports.jsp";
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

        request.getRequestDispatcher(PAGE).forward(request, response);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AirportDTO airportDTO = extractAirportFromRequest(req);
        if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            Long id = Long.parseLong(req.getParameter("id"));
            airportService.updateAirport(id, airportDTO);
        } else {
            airportService.createAirport(airportDTO);
        }
        resp.sendRedirect(req.getContextPath() + "/dashboard/airport");
    }

    private AirportDTO extractAirportFromRequest(HttpServletRequest req) {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setAirportName(req.getParameter("airportName"));
        airportDTO.setCity(req.getParameter("city"));
        airportDTO.setCountry(req.getParameter("country"));
        return airportDTO;
    }
}
