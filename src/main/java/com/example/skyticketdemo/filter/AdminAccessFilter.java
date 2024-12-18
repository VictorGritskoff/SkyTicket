package com.example.skyticketdemo.filter;

import com.example.skyticketdemo.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && Role.ADMIN.equals(session.getAttribute("role"))) {
            chain.doFilter(request, response);
        } else {
            handleUnauthorizedAccess(httpRequest, httpResponse);
        }
    }

    private void handleUnauthorizedAccess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("message", "Error! You must be an admin to access this page.");
        request.getRequestDispatcher("/public/login").forward(request, response);
    }
}

