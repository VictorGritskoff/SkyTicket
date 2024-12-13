<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 29.11.2024
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- ===== BOX ICONS ===== -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <!-- ===== CSS ===== -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <title>Управление рейсами</title>
</head>
<body id="body-pd">

<jsp:include page="/WEB-INF/views/common/dashboard.jsp"/>
<jsp:include page="/WEB-INF/views/common/flight/addFlightModal.jsp"/>
<jsp:include page="/WEB-INF/views/common/flight/addSeatsModal.jsp"/>
<main class="container mt-5 main-content">
    <h1>Список рейсов</h1>
    <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addFlightModal">
        Добавить рейс
    </button>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>Номер рейса</th>
            <th>Отправление</th>
            <th>Назначение</th>
            <th>Вылет</th>
            <th>Прибытие</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="flight" items="${flights}">
            <tr>
                <td>${flight.flightNumber}</td>
                <td>${flight.departureAirport.airportName}</td>
                <td>${flight.arrivalAirport.airportName}</td>
                <td>${flight.departureTime}</td>
                <td>${flight.arrivalTime}</td>
                <td>
                    <button class="btn btn-danger btn-sm"
                            onclick="showDeleteFlightModal(${flight.flightID})">
                        Удалить
                    </button>
                    <button class="btn btn-success btn-sm"
                            onclick="openAddSeatsModal(${flight.flightID})">
                        Места
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
