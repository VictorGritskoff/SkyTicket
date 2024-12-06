<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 04.12.2024
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="addAirlineModal" tabindex="-1" aria-labelledby="addAirlineModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="addAirlineForm" method="post" action="${pageContext.request.contextPath}/dashboard/airlines">
                <div class="modal-header">
                    <h5 class="modal-title" id="addAirlineModalLabel">Модальное окно</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="airlineId" name="id">
                    <div class="mb-3">
                        <label for="airlineName" class="form-label">Название авиалинии</label>
                        <input type="text" class="form-control" id="airlineName" name="airlineName" required>
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