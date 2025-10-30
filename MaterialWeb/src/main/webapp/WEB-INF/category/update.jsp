<%-- 
    Document   : update
    Created on : Jul 11, 2025, 3:08:14 PM
    Author     : Nguyen Thanh Nhan - CE190122
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">
        <h1 class="mt-5">UPDATE CATEGORY</h1>
        <form method="post" action="${pageContext.request.contextPath}/category">
            <table class="table">

                <tr>
                    <th><label for="id">ID</label></th>
                    <td><input type="text" name="id" id="id" value="${category.id}" readonly class="form-control"/></td>
                </tr>

                <tr>
                    <th><label for="name">Name Category</label></th>
                    <td><input type="text" name="name" id="name" value="${category.name}" required class="form-control"/></td>
                </tr>

                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="update">SAVE</button>
                        <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/category?view=list">CANCEL</a>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>

