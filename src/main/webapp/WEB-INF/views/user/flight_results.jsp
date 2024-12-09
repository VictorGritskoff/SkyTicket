<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 07.12.2024
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Бронирование</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main_page.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/common/main_page_header.jsp"/>
<jsp:include page="/WEB-INF/views/common/flight_booking_modal.jsp"/>
<div class="flights__container">
    <h2>Доступные рейсы</h2>
    <c:if test="${empty flights}">
        <p>Рейсы не найдены.</p>
    </c:if>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>Номер рейса</th>
            <th>Откуда</th>
            <th>Куда</th>
            <th>Время вылета</th>
            <th>Время прибытия</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="flight" items="${flights}">
            <tr>
                <td>${flight.flightNumber}</td>
                <td>${flight.departureAirport.city} (${flight.departureAirport.country})</td>
                <td>${flight.arrivalAirport.city} (${flight.arrivalAirport.country})</td>
                <td>${flight.departureTime}</td>
                <td>${flight.arrivalTime}</td>
                <td><button class="btn btn-submit btn-sm" onclick="showBookingModal(${flight.flightID})">Забронировать</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/views/common/main_page_footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>
