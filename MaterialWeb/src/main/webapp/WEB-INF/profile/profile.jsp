<%-- 
    Document   : profile
    Created on : Jun 23, 2025, 1:31:07 PM
    Author     : Tieu Gia Huy - CE191594
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/include/header.jsp" %>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow border-0">
                <div class="card-header bg-success text-white">
                    <h4 class="mb-0">Profile</h4>
                </div>
                <div class="card-body text-center">
                    <!-- Nếu không có user -->
                    <c:if test="${empty user}">
                        <div class="alert alert-danger">No user found.</div>
                    </c:if>

                    <!-- Nếu có user -->
                    <c:if test="${not empty user}">
                        <!-- Hiển thị avatar -->
                        <div class="mb-3">
                            <c:choose>
                                <c:when test="${not empty user.avatar}">
                                    <img src="${pageContext.request.contextPath}/assets/img/${user.avatar}"
                                         class="rounded-circle mb-3"
                                         style="width:120px; height:120px; object-fit:cover;"
                                         alt="Avatar của ${user.fullName}" />
                                </c:when>
                                <c:otherwise>
                                    <i class="bi bi-person-circle text-secondary mb-3" style="font-size:120px;"></i>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <!-- Thông tin cá nhân -->
                        <p class="text-start"><strong>Name:</strong> ${user.fullName}</p>
                        <p class="text-start"><strong>Email:</strong> ${user.email}</p>
                        <p class="text-start"><strong>Phone:</strong> ${user.phonenumbers}</p>

                        <a href="profile?view=editProfile" class="btn btn-outline-primary mt-3">Edit Profile</a>
                    </c:if>

                    <!-- Thông báo thành công -->
                    <c:if test="${not empty sessionScope.message}">
                        <div class="alert alert-success mt-3">${sessionScope.message}</div>
                        <c:remove var="message" scope="session" />
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/include/footer.jsp" %>
