package com.example.skyticket;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Подготовка данных
        String message = "Hello from Servlet!";
        int number = 42;

        // Передача данных в JSP через request
        request.setAttribute("message", message);
        request.setAttribute("number", number);

        // Перенаправление на JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/first.jsp");
        dispatcher.forward(request, response);
    }
}