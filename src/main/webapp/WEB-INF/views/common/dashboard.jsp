<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 30.11.2024
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="l-navbar" id="nav-bar">
    <nav class="nav">
        <div>
            <a href="#" class="nav__logo">
                <i class='bx bx-layer nav__logo-icon'></i>
                <span class="nav__logo-name">SkyTicket</span>
            </a>

            <div class="nav__list">
                <a href="${pageContext.request.contextPath}/admin/dashboard/flights" class="nav__link ${isFlightsActive ? 'active' : ''}">
                    <i class="bi bi-calendar-check"></i>
                    <span class="nav__name">Рейсы</span>
                </a>
                <a href="${pageContext.request.contextPath}/admin/dashboard/airport" class="nav__link ${isAirportActive ? 'active' : ''}">
                    <i class="bi bi-buildings"></i>
                    <span class="nav__name">Аэропорты</span>
                </a>
                <a href="${pageContext.request.contextPath}/admin/dashboard/airlines" class="nav__link ${isAirlinesActive ? 'active' : ''}">
                    <i class="bi bi-airplane"></i>
                    <span class="nav__name">Авиалинии</span>
                </a>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/public/logout" method="post" style="display: inline;" id="logoutForm">
            <a href="#" class="nav__link" onclick="document.getElementById('logoutForm').submit();">
                <i class="bi bi-x-octagon"></i>
                <span class="nav__name">Выход</span>
            </a>
        </form>

    </nav>
</div>
