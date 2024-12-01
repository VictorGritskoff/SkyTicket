<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 30.11.2024
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- ===== BOX ICONS ===== -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <!-- ===== CSS ===== -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">

    <title>Аэропорты</title>
</head>
<body id="body-pd">
<header class="header" id="header">
    <div class="header__toggle">
        <i class="bi bi-list" id="header-toggle"></i>
    </div>

</header>

<jsp:include page="/WEB-INF/views/common/dashboard.jsp"/>
<jsp:include page="/WEB-INF/views/common/addAirportModal.jsp"/>  REMOVE
<main class="container mt-5 main-content">
    <h1>Список Аэропортов</h1>
    <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addFlightModal">
        Добавить аэропорт
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
        <tr>
            <td>AB123</td>
            <td>Москва (Шереметьево)</td>
            <td>Париж (Шарль де Голль)</td>
            <td>2024-12-10 14:30</td>
            <td>2024-12-10 16:45</td>
            <td>
                <button class="btn btn-primary btn-sm">Просмотр</button>
                <button class="btn btn-warning btn-sm">Редактировать</button>
                <button class="btn btn-danger btn-sm">Удалить</button>
            </td>
        </tr>
        </tbody>
    </table>
</main>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
