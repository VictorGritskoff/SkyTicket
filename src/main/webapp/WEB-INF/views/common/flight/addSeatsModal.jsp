<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 07.12.2024
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="addSeatsModal" tabindex="-1" aria-labelledby="addSeatsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="addSeatsForm" method="POST" action="${pageContext.request.contextPath}/dashboard/seats/add">
                <div class="modal-header">
                    <h5 class="modal-title" id="addSeatsModalLabel">Добавить места</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="flightID" id="seatFlightID">

                    <div class="mb-3">
                        <label for="seatPrefixA" class="form-label">Класс A</label>
                        <input type="number" class="form-control" id="seatCountA" name="seatCountA" placeholder="Количество мест с префиксом A" min="1">
                        <input type="number" step="0.01" class="form-control" id="seatPriceA" name="seatPriceA" placeholder="Цена за место с префиксом A">
                    </div>
                    <div class="mb-3">
                        <label for="seatPrefixB" class="form-label">Класс B</label>
                        <input type="number" class="form-control" id="seatCountB" name="seatCountB" placeholder="Количество мест с префиксом B" min="1">
                        <input type="number" step="0.01" class="form-control" id="seatPriceB" name="seatPriceB" placeholder="Цена за место с префиксом B">
                    </div>
                    <div class="mb-3">
                        <label for="seatPrefixC" class="form-label">Класс C</label>
                        <input type="number" class="form-control" id="seatCountC" name="seatCountC" placeholder="Количество мест с префиксом C" min="1">
                        <input type="number" step="0.01" class="form-control" id="seatPriceC" name="seatPriceC" placeholder="Цена за место с префиксом C">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>


