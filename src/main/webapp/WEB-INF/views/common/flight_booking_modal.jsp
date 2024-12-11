<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 09.12.2024
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="bookingModal" tabindex="-1" aria-labelledby="bookingModalLabel" aria-hidden="true" data-min-price="0">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-uppercase text-center w-100" id="bookingModalLabel">Бронирование мест</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="bookingForm" method="post" action="${pageContext.request.contextPath}/bookSeats">
                <div class="modal-body">
                    <input type="hidden" name="flightId" id="flightIdInput">
                    <div class="flight-info text-center mb-4">
                        <p class="route text-uppercase fw-bold mb-2" id="routeInfo"></p>
                        <p class="dates fw-light" id="dateInfo"></p>
                    </div>
                    <div class="mb-4">
                        <label for="seatCount" class="form-label">Количество мест</label>
                        <input type="number" class="form-control" id="seatCount" name="seatCount" min="1" max="10" required>
                    </div>
                    <h6 class="payment-info-title text-center text-decoration-underline mb-3">Реквизиты для оплаты</h6>
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label">Номер карты</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber" pattern="\d{16}" required>
                    </div>
                    <div class="mb-3">
                        <label for="cardCvv" class="form-label">CVV</label>
                        <input type="text" class="form-control" id="cardCvv" name="cardCvv" pattern="\d{3}" required>
                    </div>
                    <div class="mb-3">
                        <label for="cardExpiry" class="form-label">Срок действия (MM/YY)</label>
                        <input type="text" class="form-control" id="cardExpiry" name="cardExpiry" pattern="\d{2}/\d{2}" required>
                    </div>
                </div>
                    <div class="text-center mb-4">
                        <p class="fw-bold">Общая стоимость:</p>
                        <p id="totalPrice" class="fs-5">0.00</p>
                    </div>
                <div class="modal-footer justify-content-center">
                    <button type="submit" class="btn btn-primary btn-submit">Забронировать</button>
                </div>
            </form>
        </div>
    </div>
</div>





