<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 29.11.2024
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
            href="https://cdn.jsdelivr.net/npm/remixicon@3.4.0/fonts/remixicon.css"
            rel="stylesheet"
    />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main_page.css" />
    <title>SkyTicket</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/main_page_header.jsp"/>
<header class="section__container header__container">
    <h1 class="section__header">Найди и Забронируй<br />Отличное Воспоминание</h1>
    <img src="${pageContext.request.contextPath}/resources/assets/header.jpg" alt="header" />
</header>

<section class="section__container booking__container">
    <div class="booking__nav">
        <span>Тут покупают авиабилеты</span>
    </div>
    <form action="${pageContext.request.contextPath}/public/searchFlights" method="post">
        <div class="form__group">
            <span><i class="ri-map-pin-line"></i></span>
            <div class="input__content">
                <div class="input__group">
                    <input type="text" name="departureCountry" placeholder=" " />
                    <label>Откуда</label>
                </div>
                <p>Где мы находимся?</p>
            </div>
        </div>
        <div class="form__group">
            <span><i class="ri-map-pin-line"></i></span>
            <div class="input__content">
                <div class="input__group">
                    <input type="text" name="arrivalCountry" placeholder=" " />
                    <label>Куда</label>
                </div>
                <p>Куда вы направляетесь?</p>
            </div>
        </div>
        <div class="form__group">
            <span><i class="ri-user-3-line"></i></span>
            <div class="input__content">
                <div class="input__group">
                    <input type="number" name="tickets" placeholder=" " />
                    <label>Билеты</label>
                </div>
                <p>Количество билетов</p>
            </div>
        </div>
        <div class="form__group">
            <span><i class="ri-calendar-line"></i></span>
            <div class="input__content">
                <div class="input__group">
                    <input type="text" name="departureDate" placeholder=" " />
                    <label>Когда</label>
                </div>
                <p>Выберите дату</p>
            </div>
        </div>
        <div class="form__group">
            <span><i class="ri-calendar-line"></i></span>
            <div class="input__content">
                <div class="input__group">
                    <input type="text" placeholder=" " />
                    <label>Обратно</label>
                </div>
                <p>Выберите дату</p>
            </div>
        </div>
        <div class="form__group form__group--button">
            <button class="btn">Искать&nbsp;<i class="ri-search-line"></i></button>
        </div>
    </form>
</section>

<section class="section__container plan__container">
    <p class="subheader">ПОМОЩЬ ВО ВРЕМЯ ПУТЕШЕСТВИЙ</p>
    <h2 class="section__header">Планируйте свой отдых с уверенностью</h2>
    <p class="description">
        Получите помощь с вашими бронированиями и планами путешествий, а также узнайте, чего ожидать во время поездки
    </p>
    <div class="plan__grid">
        <div class="plan__content">
            <span class="number">01</span>
            <h4>Бронирование авиабилетов</h4>
            <p>
                Получите помощь с вашими бронированиями
                авиабилетов, чтобы найти самые выгодные предложения и избежать стресса, связанного с планированием полетов.
            </p>
            <span class="number">02</span>
            <h4>Размещение и отели</h4>
            <p>
                Узнайте, как забронировать лучшие отели и варианты размещения, чтобы ваше пребывание было комфортным и приятным.
            </p>
            <span class="number">03</span>
            <h4>Планы маршрутов</h4>
            <p>
                Получите помощь в составлении маршрутов, чтобы ваше путешествие было максимально организованным и насыщенным впечатлениями.
            </p>
        </div>
        <div class="plan__image">
            <img src="${pageContext.request.contextPath}/resources/assets/plan-1.jpg" alt="plan" />
            <img src="${pageContext.request.contextPath}/resources/assets/plan-2.jpg" alt="plan" />
            <img src="${pageContext.request.contextPath}/resources/assets/plan-3.jpg" alt="plan" />
        </div>
    </div>
</section>

<section class="memories">
    <div class="section__container memories__container">
        <div class="memories__header">
            <h2 class="section__header">
                Путешествуй и создавай воспоминания по всему миру
            </h2>
        </div>
        <div class="memories__grid">
            <div class="memories__card">
                <span><i class="ri-calendar-2-line"></i></span>
                <h4>Покупай и отдыхай!</h4>
                <p>
                    С "Покупай и отдыхай" вы можете сесть, расслабиться и наслаждаться поездкой, пока мы позаботимся обо всем остальном.
                </p>
            </div>
            <div class="memories__card">
                <span><i class="ri-shield-check-line"></i></span>
                <h4>Безопасность</h4>
                <p>
                    Работаем только с надежными авиалиниями
                </p>
            </div>
            <div class="memories__card">
                <span><i class="ri-bookmark-2-line"></i></span>
                <h4>Скидки</h4>
                <p>
                    От скидок на билеты до эксклюзивных акций
                    и предложений — мы ставим в приоритет доступность, не жертвуя качеством.
                </p>
            </div>
        </div>
    </div>
</section>

<section class="section__container travellers__container">
    <h2 class="section__header">Топ мест чтобы посетить!</h2>
    <div class="travellers__grid">
        <div class="travellers__card">
            <img src="${pageContext.request.contextPath}/resources/assets/traveller-1.jpg" alt="traveller" />
            <div class="travellers__card__content">
                <p>Дубай</p>
            </div>
        </div>
        <div class="travellers__card">
            <img src="${pageContext.request.contextPath}/resources/assets/traveller-2.jpg" alt="traveller" />
            <div class="travellers__card__content">
                <p>Париж</p>
            </div>
        </div>
        <div class="travellers__card">
            <img src="${pageContext.request.contextPath}/resources/assets/traveller-3.jpg" alt="traveller" />
            <div class="travellers__card__content">
                <p>Сингапур</p>
            </div>
        </div>
        <div class="travellers__card">
            <img src="${pageContext.request.contextPath}/resources/assets/traveller-4.jpg" alt="traveller" />
            <div class="travellers__card__content">
                <p>Малайзия</p>
            </div>
        </div>
    </div>
</section>

<section class="subscribe">
    <div class="section__container subscribe__container">
        <h2 class="section__header">Подпишитесь на рассылку и получайте последние новости</h2>
        <form class="subscribe__form">
            <input type="text" placeholder="Ваш email" />
            <button class="btn">Подписаться</button>
        </form>
    </div>
</section>
<jsp:include page="/WEB-INF/views/common/main_page_footer.jsp"/>
<div class="toast-container position-absolute top-0 end-0 p-3" style="z-index: 1050;">
    <c:if test="${not empty error}">
        <div id="errorToast" class="toast align-items-center text-bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="5000">
            <div class="d-flex">
                <div class="toast-body">
                    <i class="bi bi-exclamation-triangle-fill"></i>
                        ${error}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty message}">
        <div id="successToast" class="toast align-items-center text-bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="5000">
            <div class="d-flex">
                <div class="toast-body">
                    <i class="bi bi-check-circle-fill"></i>
                        ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </c:if>
</div>
<script src="${pageContext.request.contextPath}/resources/js/notifications.js"></script>
</body>
</html>
