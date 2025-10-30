<%-- 
    Document   : home
    Created on : May 31, 2025, 12:23:38 PM
    Author     : Nguyen Thanh Nhan - CE190122
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User " %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello </h1>
        <% User fname = (User) request.getAttribute("member"); %>
<<<<<<< HEAD
        <%= fname.getFullName() %>
=======
        <%= fname.getFullName()%>
>>>>>>> c43a8274351fe1dc291c89d7143e28b0d3db60f3
    </body>
</html>
