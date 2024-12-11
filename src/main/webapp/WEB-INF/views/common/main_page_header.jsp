<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 07.12.2024
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
    <div class="nav__logo">SkyTicket</div>
    <ul class="nav__links">
        <li class="link"><a href="${pageContext.request.contextPath}/public">Главная</a></li>
        <li class="link"><a href="#">Страны</a></li>
        <li class="link"><a href="${pageContext.request.contextPath}/booking">Рейсы</a></li>
        <li class="link"><a href="${pageContext.request.contextPath}/public/about">О нас</a></li>
    </ul>
    <button class="btn">Войти</button>
</nav>
