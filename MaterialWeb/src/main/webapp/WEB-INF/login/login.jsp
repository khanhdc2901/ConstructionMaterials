
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="<%= request.getContextPath()%>/assets/css/style.css?v=<%= System.currentTimeMillis() %>" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    </head>

    <body>
        
        <%
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (Cookie cookie : cookies){
                if (cookie.getName().equals("EMAIL")) {
                        request.setAttribute("Emailsave", cookie.getValue());
                    }
            }
            }
        %>
        <div class="wrapper">
            <form action="<%= request.getContextPath()%>/auth" method="post">
                <h1>Login</h1>


                <div class="input-box">
                    <input type="email" name="email" placeholder="Email" required
                     <c:if test= "${requestScope.Emailsave != null}"
                          value ="${requestScope.Emailsave}" >
                    </c:if>
                    <i class='bx bxs-user-circle'></i>  
                </div>

                <div class="input-box">
                    <input type="password" name="password" placeholder="Password" required>
                    <i class='bx bxs-lock'></i> 
                </div>

                <div class="remember-forgot">
                    <label><input name="rememberme" type="checkbox">Remember me</label>
                    <a href="<%= request.getContextPath()%>/requestPassword">Forgot password?</a>
                </div>

                <button type="submit" name="action" value="LOGIN" class="btn">Login</button>

                <div class="register-link">
                    <p>Don't have an account?
                        <a href="<%= request.getContextPath()%>/auth?view=register">Register</a>
                    </p>
                </div>
                <% String mess = (String) request.getAttribute("msg"); %>
                <% if (mess != null) {%>
                <p style="color:red; text-align:center;"><%= mess%></p>
                <% }%>
            </form>
        </div>
    </body>

</html>
