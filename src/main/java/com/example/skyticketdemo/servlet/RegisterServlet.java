package com.example.skyticketdemo.servlet;

import com.example.skyticketdemo.dto.UserCreateDTO;
import com.example.skyticketdemo.mapper.UserMapperImpl;
import com.example.skyticketdemo.repository.impl.UserRepositoryImpl;
import com.example.skyticketdemo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/public/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService;

    public RegisterServlet() {
        this.userService = new UserService(new UserRepositoryImpl(), new UserMapperImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/common/login-register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserCreateDTO userDto = UserCreateDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();

        try {
            userService.register(userDto);
            resp.sendRedirect(req.getContextPath() + "/public/login");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/common/register.jsp").forward(req, resp);
        }
    }
}
