<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 12.12.2024
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main_page.css" />
    <title>Profile</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/main_page_header.jsp"/>
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-3 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="100"> <!-- Уменьшаем размер изображения -->
                            <div class="mt-3">
                                <h4>${currentUser.firstName} ${currentUser.lastName}</h4>
                                <form action="${pageContext.request.contextPath}/public/logout" method="post" style="display: inline;">
                                    <button class="btn btn-danger btn-sm" type="submit">Выйти</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8 mb-3">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Имя</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${currentUser.firstName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Фамилия</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${currentUser.lastName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${currentUser.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info" onclick='openProfileModal({
                                        id: "${currentUser.userID}",
                                        firstName: "${currentUser.firstName}",
                                        lastName: "${currentUser.lastName}",
                                        email: "${currentUser.email}"
                                        })'>Редактировать</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="tickets__container">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>Номер рейса</th>
                    <th>Откуда</th>
                    <th>Куда</th>
                    <th>Место</th>
                    <th>Время вылета</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty tickets}">
                        <c:forEach var="ticket" items="${tickets}" varStatus="status">
                            <tr>
                                <td>${ticket.flightNumber}</td>
                                <td>${ticket.departureAirport}</td>
                                <td>${ticket.arrivalAirport}</td>
                                <td>${ticket.seatNumber}</td>
                                <td>
                                    <c:out value="${departureTimes[status.index]}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="text-center">Билеты отсутствуют.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/user/profileEditModal.jsp"/>
<jsp:include page="/WEB-INF/views/common/main_page_footer.jsp"/>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


