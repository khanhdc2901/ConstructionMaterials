<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="<%= request.getContextPath()%>/assets/css/style.css?v=<%= System.currentTimeMillis() %>" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
</head>
<body>
    <div class="wrapper">
        <form action="<%= request.getContextPath() %>/auth" method="post">
            <h1>Register</h1>

            <div class="input-box">
                <input type="text" name="fullname" placeholder="Full Name" required>
                <i class='bx bxs-user'></i>
            </div>

            <div class="input-box">
                <input type="text" name="phone" placeholder="Phone Number" required>
                <i class='bx bxs-phone'></i>
            </div>

            <div class="input-box">
                <input type="email" name="email" placeholder="Email" required>
                <i class='bx bxs-envelope'></i>
            </div>

            <div class="input-box">
                <input type="password" name="password" placeholder="Password" required>
                <i class='bx bxs-lock'></i>
            </div>
           
             <div class="input-box">
                <input type="password" name="confirmpassword" placeholder="Confirm password" required>
                <i class='bx bxs-lock'></i>
            </div>

            <button type="submit" name="action" value="REGISTER" class="btn">Register</button>

            <div class="register-link">
                <p>Already have an account? 
                    <a href="<%= request.getContextPath() %>/auth?view=login">Login</a>
                </p>
            </div>
            
            
            <% String mess = (String) request.getAttribute("msg"); %>
            <% if (mess != null) { %>
                <p style="color:red; text-align:center;"><%= mess %></p>
            <% } %>
        </form>
    </div>
</body>
</html>
