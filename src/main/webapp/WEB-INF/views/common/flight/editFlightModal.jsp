<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 05.12.2024
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal fade" id="addFlightModal" tabindex="-1" aria-labelledby="addFlightModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form id="addFlightForm" method="post" action="${pageContext.request.contextPath}/dashboard/flights">
        <div class="modal-header">
          <h5 class="modal-title" id="addFlightModalLabel">Изменить данные рейса</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="flightNumber" class="form-label">Номер рейса</label>
            <input type="text" class="form-control" id="flightNumber" name="flightNumber" required>
          </div>
          <div class="mb-3">
            <label for="departureAirport" class="form-label">Аэропорт отправления</label>
            <select class="form-select" id="departureAirport" name="departureAirport" required>
              <option value="" disabled selected>Выберите аэропорт</option>
              <c:forEach var="airport" items="${airports}">
                <option value="${airport.airportID}">
                    ${airport.airportName} (${airport.city}, ${airport.country})
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <label for="arrivalAirport" class="form-label">Аэропорт прибытия</label>
            <select class="form-select" id="arrivalAirport" name="arrivalAirport" required>
              <option value="" disabled selected>Выберите аэропорт</option>
              <c:forEach var="airport" items="${airports}">
                <option value="${airport.airportID}">
                    ${airport.airportName} (${airport.city}, ${airport.country})
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <label for="airline" class="form-label">Авиалиния</label>
            <select class="form-select" id="airline" name="airline" required>
              <option value="" disabled selected>Выберите авиалинию</option>
              <c:forEach var="airline" items="${airlines}">
                <option value="${airline.airlineID}">
                    ${airline.airlineName}
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3">
            <label for="departureTime" class="form-label">Время отправления</label>
            <input type="datetime-local" class="form-control" id="departureTime" name="departureTime" required>
          </div>
          <div class="mb-3">
            <label for="arrivalTime" class="form-label">Время прибытия</label>
            <input type="datetime-local" class="form-control" id="arrivalTime" name="arrivalTime" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
          <button type="submit" class="btn btn-primary" onclick="submitEditFlight()">Сохранить</button>
        </div>
      </form>
    </div>
  </div>
</div>