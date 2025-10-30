<%-- 
    Document   : create
    Created on : Jul 11, 2025, 3:01:01 PM
    Author     : Nguyen Thanh Nhan - CE190122
--%>

<%@page import="dao.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">
        <h1 class="mt-5">Add New Category</h1>
        <form method="post" action="${pageContext.request.contextPath}/category">
            <table class="table">

                <tr>
                    <td><label for="name" class="form-label">Category</label></td>
                    <td><input type="text" name="name" id="name" class="form-control" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="create">Save</button>
                        <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/category">Cancel</a>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>
