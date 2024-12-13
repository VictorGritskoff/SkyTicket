package com.example.skyticketdemo.servlet.user;

import com.example.skyticketdemo.dto.UserDTO;
import com.example.skyticketdemo.repository.impl.SeatRepositoryImpl;
import com.example.skyticketdemo.service.SeatService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/bookSeats")
public class BookSeatsServlet extends HttpServlet {

    private SeatService seatService;

    @Override
    public void init() throws ServletException {
        this.seatService = new SeatService(new SeatRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightIdParam = request.getParameter("flightId");
        String seatCountParam = request.getParameter("seatCount");

        HttpSession httpSession = request.getSession();
        UserDTO userDto = (UserDTO) httpSession.getAttribute("currentUser");


        try {
            Long flightId = Long.parseLong(flightIdParam);
            int seatCount = Integer.parseInt(seatCountParam);

            boolean success = seatService.bookSeats(flightId, seatCount, userDto.getUserID());

            if (success) {
                request.setAttribute("message", "Места успешно забронированы!");
            } else {
                request.setAttribute("error", "Недостаточно доступных мест на рейсе.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Неверные данные.");
        } catch (Exception e) {
            request.setAttribute("error", "Ошибка при бронировании мест: " + e.getMessage());
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/user/main.jsp").forward(request, response);
    }

}

