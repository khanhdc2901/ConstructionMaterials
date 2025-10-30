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
        <form action="<%= request.getContextPath() %>/requestPassword" method="post">
            <h1>Reset Password</h1>

            <div class="input-box">
                <input type="email" name="email" placeholder="Enter your email" required>
                <i class='bx bxs-envelope'></i>
            </div>

            <button type="submit" class="btn">Send to Email</button>

            <div class="register-link">
                <p>Remembered your password?
                    <a href="<%= request.getContextPath() %>/auth?view=login">Back to Login</a>
                </p>
                <br>
                <p>Don't have an account?
                    <a href="<%= request.getContextPath() %>/auth?view=register">Go to sign-up page</a>
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
