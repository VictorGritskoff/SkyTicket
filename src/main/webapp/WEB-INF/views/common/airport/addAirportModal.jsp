<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 01.12.2024
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="addAirportModal" tabindex="-1" aria-labelledby="addAirportModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form id="addAirportForm" method="post" action="${pageContext.request.contextPath}/admin/dashboard/airport">
        <div class="modal-header">
          <h5 class="modal-title" id="addAirportModalLabel">Модальное окно</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
        </div>
        <div class="modal-body">
          <input type="hidden" id="airportId" name="id">
          <div class="mb-3">
            <label for="airportName" class="form-label">Название аэропорта</label>
            <input type="text" class="form-control" id="airportName" name="airportName" required>
          </div>
          <div class="mb-3">
            <label for="city" class="form-label">Город</label>
            <input type="text" class="form-control" id="city" name="city" required>
          </div>
          <div class="mb-3">
            <label for="country" class="form-label">Страна</label>
            <input type="text" class="form-control" id="country" name="country" required>
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