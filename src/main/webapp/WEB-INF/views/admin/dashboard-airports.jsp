<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 30.11.2024
  Time: 23:48
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

    <title>Аэропорты</title>
</head>
<body id="body-pd">

<jsp:include page="/WEB-INF/views/common/dashboard.jsp"/>
<jsp:include page="/WEB-INF/views/common/airport/addAirportModal.jsp"/>
<main class="container mt-5 main-content">
    <h1>Список аэропортов</h1>
    <button class="btn btn-success mb-3" onclick="openAirportModal('add')">Добавить аэропорт</button>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>Название</th>
            <th>Город</th>
            <th>Страна</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="airport" items="${airports}">
            <tr>
                <td>${airport.airportName}</td>
                <td>${airport.city}</td>
                <td>${airport.country}</td>
                <td>
                    <button class="btn btn-primary btn-sm"
                            onclick="openAirportModal('edit', { id: ${airport.airportID}, airportName: '${airport.airportName}', city: '${airport.city}', country: '${airport.country}' })">
                        Редактировать
                    </button>
                    <button class="btn btn-danger btn-sm" onclick="showDeleteAirportModal(${airport.airportID})">Удалить</button>
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
