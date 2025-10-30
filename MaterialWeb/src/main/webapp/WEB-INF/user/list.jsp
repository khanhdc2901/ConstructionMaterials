<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/WEB-INF/include/header.jsp" />

<div class="container mt-4">
    <h2>Danh sách người dùng</h2>

    <form action="user" method="get" class="mb-3">
        <input type="hidden" name="view" value="list"/>
        <input type="text" name="keyword" class="form-control w-25 d-inline" placeholder="Tìm kiếm..." value="${keyword}"/>
        <button type="submit" class="btn btn-primary">Tìm</button>
    </form>

    <c:choose>
        <c:when test="${not empty list}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Tên đầy đủ</th>
                        <th>SDT</th>
                        <th>Trạng thái</th>
                        <th>Vai trò</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${list}">
                        <tr>
                            <td>${u.userid}</td>
                            <td>${u.email}</td>
                            <td>${u.fullName}</td>
                            <td>${u.phonenumbers}</td>
                            <td>${u.status}</td>
                            <td>${u.role}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${sessionScope.user.userid == u.userid}">
                                        <span class="text-muted">You are here</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="user?view=update&id=${u.userid}" class="btn btn-sm btn-warning">Sửa</a>
                                        <a href="user?view=delete&id=${u.userid}" class="btn btn-sm btn-danger">Xoá</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="text-danger">Không có người dùng nào.</p>
        </c:otherwise>
    </c:choose>

    <!-- Pagination -->
    <nav>
        <ul class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <li class="page-item ${i == page ? 'active' : ''}">
                    <a class="page-link" href="user?view=list&page=${i}${keyword != null ? '&keyword=' + keyword : ''}">${i}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>

<jsp:include page="/WEB-INF/include/footer.jsp" />
