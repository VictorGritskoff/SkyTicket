package com.example.skyticketdemo.servlet.common;

import com.example.skyticketdemo.dto.TicketDTO;
import com.example.skyticketdemo.dto.UserDTO;
import com.example.skyticketdemo.mapper.TicketMapper;
import com.example.skyticketdemo.mapper.UserMapper;
import com.example.skyticketdemo.repository.impl.TicketRepositoryImpl;
import com.example.skyticketdemo.repository.impl.UserRepositoryImpl;
import com.example.skyticketdemo.service.TicketService;
import com.example.skyticketdemo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/user/profile")
public class ProfileServlet extends HttpServlet {

    private final TicketService ticketService;
    private final UserService userService;

    public ProfileServlet() {
        TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);
        this.ticketService = new TicketService(new TicketRepositoryImpl(), ticketMapper);

        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        this.userService = new UserService(new UserRepositoryImpl(), userMapper);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO currentUser = (UserDTO) session.getAttribute("currentUser");

        request.setAttribute("currentUser", currentUser);
        Long userID = currentUser.getUserID();

        List<TicketDTO> ticketDTOs = ticketService.findTicketsByUser(userID);
        request.setAttribute("tickets", ticketDTOs);

        List<LocalDateTime> departureTimes = ticketService.findDepartureTimesByUser(userID);
        request.setAttribute("departureTimes", departureTimes);

        request.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateUser(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/user/profile");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            throw new IllegalArgumentException("ID пользователя не задан.");
        }

        Long id = Long.parseLong(idParam);

        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой.");
        }
        if (email == null || email.trim().isEmpty() || !email.matches(".+@.+\\..+")) {
            throw new IllegalArgumentException("Некорректный email.");
        }

        UserDTO userDTO = UserDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        userService.updateUser(id, userDTO);
    }
}
