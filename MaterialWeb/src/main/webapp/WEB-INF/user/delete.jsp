<%-- 
    Document   : delete
    Created on : Jul 16, 2025, 9:26:39 PM
    Author     : Nguyen Thanh Nhan - CE190122
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">
        <h1 class="mt-5">UPDATE USER</h1>
        <form method="post" action="${pageContext.request.contextPath}/user">
            <table class="table">

                <tr>
                    <th><label for="id">ID</label></th>
                    <td><input type="text" name="id" id="id" value="${user.userid}" readonly class="form-control"/></td>
                </tr>

                <tr>
                    <th><label for="email">Email</label></th>
                    <td><input type="text" name="email" id="email" value="${user.email}" readonly class="form-control"/></td>
                </tr>

                <tr>
                    <th><label for="fullName">Họ và tên</label></th>
                    <td><input type="text" name="fullName" id="fullName" value="${user.fullName}" readonly class="form-control"/></td>
                </tr>

                <tr>
                    <th><label for="phone">Số điện thoại</label></th>
                    <td><input type="text" name="phone" id="phone" value="${user.phonenumbers}" readonly class="form-control"/></td>
                </tr>

                <tr>
                    <th><label for="role">Quyền</label></th>
                    <td>
                        <select name="role" id="role" class="form-control" disabled>
                            <option value="Admin" ${user.role == 'Admin' ? 'selected' : ''}>Admin</option>
                            <option value="User" ${user.role == 'User' ? 'selected' : ''}>User</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <th><label for="status">Trạng thái</label></th>
                    <td>
                        <select name="status" id="status" class="form-control" disabled >
                            <option value="active" ${user.status == 'active' ? 'selected' : ''}>active</option>
                            <option value="ban" ${user.status == 'ban' ? 'selected' : ''}>ban</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="delete">DELETE</button>
                        <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/user?view=list">Cancel</a>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>
