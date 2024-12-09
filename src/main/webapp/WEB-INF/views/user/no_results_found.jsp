<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 08.12.2024
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Результаты поиска</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main_page.css" />
</head>
<jsp:include page="/WEB-INF/views/common/main_page_header.jsp"/>
<body>
<div class="container text-center mt-5">
  <h1 class="display-4 text-danger">Результаты не найдены</h1>
  <p class="lead">К сожалению, по вашему запросу ничего не найдено.</p>
</div>
<jsp:include page="/WEB-INF/views/common/main_page_footer.jsp"/>
</body>
</html>
