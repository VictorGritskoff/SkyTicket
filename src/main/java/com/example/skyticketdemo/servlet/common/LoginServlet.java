package com.example.skyticketdemo.servlet.common;

import com.example.skyticketdemo.dto.UserDTO;
import com.example.skyticketdemo.exceptions.BadCredentialsException;
import com.example.skyticketdemo.mapper.UserMapper;
import com.example.skyticketdemo.repository.impl.UserRepositoryImpl;
import com.example.skyticketdemo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

@WebServlet("/public/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() {
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        userService = new UserService(new UserRepositoryImpl(), userMapper);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/common/login-register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            UserDTO userDto = userService.authenticate(email, password);
            System.out.println(userDto);

            HttpSession session = req.getSession();
            session.setAttribute("currentUser", userDto);
            session.setAttribute("role", userDto.getRole());

            switch (userDto.getRole()) {
                case ADMIN:
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard/flights");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/public");
            }
        } catch (BadCredentialsException e) {
            String errorMessage = "Неверный логин или пароль. Повторите попытку";
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("/WEB-INF/views/common/login-register.jsp").forward(req, resp);
        }
    }
}