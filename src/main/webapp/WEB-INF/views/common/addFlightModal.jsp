<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 30.11.2024
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="addFlightModal" tabindex="-1" aria-labelledby="addFlightModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form id="addFlightForm" method="post" action="${pageContext.request.contextPath}/dashboard/flights">
      <div class="modal-header">
          <h5 class="modal-title" id="addFlightModalLabel">Добавить рейс</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="flightNumber" class="form-label">Номер рейса</label>
            <input type="text" class="form-control" id="flightNumber" name="flightNumber" required>
          </div>
          <div class="mb-3">
            <label for="departureAirport" class="form-label">Аэропорт отправления</label>
            <select class="form-select" id="departureAirport" name="departureAirport"></select>
          </div>
          <div class="mb-3">
            <label for="arrivalAirport" class="form-label">Аэропорт прибытия</label>
            <select class="form-select" id="arrivalAirport" name="arrivalAirport"></select>
          </div>
          <div class="mb-3">
            <label for="airline" class="form-label">Авиалиния</label>
            <select class="form-select" id="airline" name="airline"></select>
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
          <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
      </form>
    </div>
  </div>
</div>
