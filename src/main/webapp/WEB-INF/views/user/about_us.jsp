<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 09.12.2024
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>О нас - Авиабилеты</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main_page.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/common/main_page_header.jsp"/>

<main class="container">
    <section class="mb-5">
        <h2 class="text-primary mb-3">Кто мы?</h2>
        <p>
            Мы — инновационная компания, специализирующаяся на продаже авиабилетов. С момента нашего основания в 2015 году
            мы стремимся сделать путешествия проще, удобнее и доступнее для всех. Наши клиенты — это миллионы путешественников
            со всего мира, которые доверяют нам планирование своих перелетов.
        </p>
    </section>

    <section class="mb-5">
        <h2 class="text-primary mb-3">Наша миссия</h2>
        <p>
            Мы верим, что путешествия — это не только передвижение из точки А в точку Б, но и незабываемые впечатления. Наша миссия —
            предложить лучший сервис по доступной цене, чтобы вы могли наслаждаться каждым моментом вашей поездки.
        </p>
    </section>

    <section class="mb-5">
        <h2 class="text-primary mb-3">Наши достижения</h2>
        <ul>
            <li>Партнерство с более чем 100 авиакомпаниями по всему миру</li>
            <li>Обслуживание более 10 миллионов довольных клиентов</li>
            <li>Получение награды "Лучший сервис авиабилетов" в 2023 году</li>
        </ul>
    </section>

    <section>
        <h2 class="text-primary mb-3">Контакты</h2>
        <p>Если у вас есть вопросы или предложения, свяжитесь с нами:</p>
        <ul>
            <li><strong>Email:</strong> support@skyticket.com</li>
            <li><strong>Телефон:</strong> +375 29 123-45-67</li>
            <li><strong>Адрес:</strong> Минск, ул. Центральная, 15</li>
        </ul>
    </section>
</main>

<jsp:include page="/WEB-INF/views/common/main_page_footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>