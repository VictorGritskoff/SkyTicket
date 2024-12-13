<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 13.12.2024
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editUserModalLabel">Редактировать данные пользователя</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editUserForm"  method="post" action="${pageContext.request.contextPath}/user/profile">
                    <input type="hidden" id="id" name="id" value="${currentUser.userID}">
                    <div class="form-group">
                        <label for="firstName">Имя</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" value="${currentUser.firstName}">
                    </div>
                    <div class="form-group">
                        <label for="lastName">Фамилия</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${currentUser.lastName}">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${currentUser.email}">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-submit" id="saveChanges">Сохранить изменения</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>




