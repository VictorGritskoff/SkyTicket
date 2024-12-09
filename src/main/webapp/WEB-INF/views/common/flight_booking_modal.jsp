<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 09.12.2024
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="bookingModal" tabindex="-1" aria-labelledby="bookingModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="bookingModalLabel">Бронирование мест</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="bookingForm" method="post" action="${pageContext.request.contextPath}/bookSeats">
                <div class="modal-body">
                    <input type="hidden" name="flightId" id="flightIdInput">
                    <div class="mb-3">
                        <label for="seatCount" class="form-label">Количество мест</label>
                        <input type="number" class="form-control" id="seatCount" name="seatCount" min="1" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                    <button type="submit" class="btn btn-primary">Забронировать</button>
                </div>
            </form>
        </div>
    </div>
</div>

