<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 04.12.2024
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
    <title>Управление авиалиниями</title>
</head>
<body id="body-pd">

<jsp:include page="/WEB-INF/views/common/dashboard.jsp"/>
<jsp:include page="/WEB-INF/views/common/addAirlineModal.jsp"/>

<main class="container mt-5 main-content">
    <h1>Список авиалиний</h1>
    <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addAirlineModal">
        Добавить авиалинию
    </button>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>Название</th>
            <th>Страна</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="airline" items="${airlines}">
            <tr>
                <td>${airline.airlineName}</td>
                <td>${airline.country}</td>
                <td>
                    <button class="btn btn-primary btn-sm"
                            onclick="openAirlineModal('edit', { id: ${airline.airlineID}, airlineName: '${airline.airlineName}', country: '${airline.country}' })">
                        Редактировать
                    </button>
                    <button class="btn btn-danger btn-sm" onclick="showDeleteAirlineModal(${airline.airlineID})">Удалить</button>
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

