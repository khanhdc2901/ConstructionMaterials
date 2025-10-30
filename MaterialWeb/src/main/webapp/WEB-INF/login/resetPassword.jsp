<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%= request.getContextPath()%>/assets/css/style.css?v=<%= System.currentTimeMillis() %>" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
</head>
<body>
    <div class="wrapper">
        <form action="<%= request.getContextPath() %>/resetPassword" method="post">
            <h1>Reset Password</h1>

            <div class="input-box">
                <input type="email" name="email" value="${email}" placeholder="Enter your email" required>
                <i class='bx bxs-envelope'></i>
            </div>

            <div class="input-box">
                <input type="password" name="password" placeholder="New Password" required>
                <i class='bx bxs-lock'></i>
            </div>

            <div class="input-box">
                <input type="password" name="confirm_password" placeholder="Confirm Password" required>
                <i class='bx bxs-lock-alt'></i>
            </div>

            <button type="submit" class="btn">Reset</button>

            <div class="register-link">
                <p>Back to 
                    <a href="<%= request.getContextPath() %>/auth?view=login">Login</a>
                </p>
            </div>

            <% String mess = (String) request.getAttribute("mess"); %>
            <% if (mess != null) { %>
                <p style="color:red; text-align:center;"><%= mess %></p>
            <% } %>
        </form>
    </div>
</body>
</html>
