package com.example.skyticketdemo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dashboard/flights")
public class FlightDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jspPage = "/WEB-INF/views/admin/dashboard-flights.jsp"; // Страница по умолчанию

        request.getRequestDispatcher(jspPage).forward(request, response);
    }
}
