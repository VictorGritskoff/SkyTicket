<%--
  Created by IntelliJ IDEA.
  User: vitya
  Date: 09.12.2024
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- ===== Iconscout CSS ===== -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css" />
    <!-- ===== CSS ===== -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login-register.css"/>
    <title>Authentication</title>
</head>
<body>
<div class="container">
    <div class="forms">
        <div class="form login">
            <span class="title">Login</span>

            <form method="post" action="${pageContext.request.contextPath}/public/login">
                <div class="input-field">
                    <input type="email" name="email" placeholder="Enter your email" required />
                    <i class="uil uil-envelope icon"></i>
                </div>
                <div class="input-field">
                    <input type="password" name="password" class="password" placeholder="Enter your password" required />
                    <i class="uil uil-lock icon"></i>
                    <i class="uil uil-eye-slash showHidePw"></i>
                </div>

                <div class="checkbox-text">
                    <div class="checkbox-content">
                        <input type="checkbox" id="logCheck" />
                        <label for="logCheck" class="text">Remember me</label>
                    </div>

                    <a href="#" class="text">Forgot password?</a>
                </div>

                <div class="input-field button">
                    <input type="submit" value="Login" />
                </div>
            </form>

            <div class="login-signup">
            <span class="text"
            >Not a member?
              <a href="#" class="text signup-link">Signup Now</a>
            </span>
            </div>
        </div>

        <!-- Registration Form -->
        <div class="form signup">
            <span class="title">Registration</span>

            <form method="post" action="${pageContext.request.contextPath}/public/register">
                <div class="input-field">
                    <input type="text" name="firstName" placeholder="Enter your name" required />
                    <i class="uil uil-user"></i>
                </div>
                <div class="input-field">
                    <input type="text" name="lastName" placeholder="Enter your surname" required />
                    <i class="uil uil-user"></i>
                </div>
                <div class="input-field">
                    <input type="text" name="email" placeholder="Enter your email" required />
                    <i class="uil uil-envelope icon"></i>
                </div>
                <div class="input-field">
                    <input type="password" name="password" class="password" placeholder="Create a password" required />
                    <i class="uil uil-lock icon"></i>
                    <i class="uil uil-eye-slash showHidePw"></i>
                </div>

                <div class="checkbox-text">
                    <div class="checkbox-content">
                        <input type="checkbox" id="termCon" />
                        <label for="termCon" class="text">I accepted all terms and conditions</label>
                    </div>
                </div>

                <div class="input-field button">
                    <input type="submit" value="Signup" />
                </div>
            </form>

            <div class="login-signup">
            <span class="text"
            >Already a member?
              <a href="#" class="text login-link">Login Now</a>
            </span>
            </div>
        </div>
    </div>
</div>


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
</div>
<script src="${pageContext.request.contextPath}/resources/js/notifications.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/login-register.js"></script>
</body>
</html>
