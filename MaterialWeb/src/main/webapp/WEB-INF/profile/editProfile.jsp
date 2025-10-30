<%-- 
    Document   : EditProfile
    Created on : Jun 23, 2025, 1:31:17 PM
    Author     : Tieu Gia Huy - CE191594
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/header.jsp" %>

<c:if test="${not empty user}">
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow border-0">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Edit Profile</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/profile?view=editProfile"
                              method="post"
                              enctype="multipart/form-data">

                            <!-- Hiển thị avatar hiện tại -->
                            <div class="text-center mb-3">
                                <c:if test="${not empty user.avatar}">
                                    <img src="${pageContext.request.contextPath}/assets/img/${user.avatar}"
                                         class="rounded-circle"
                                         style="width:100px; height:100px; object-fit:cover;"
                                         alt="Avatar"/>
                                </c:if>
                            </div>

                            <!-- Chọn avatar mới -->
                            <div class="mb-3">
                                <label class="form-label">Avatar mới</label>
                                <input type="file" name="avatarFile"
                                       accept="image/*"
                                       class="form-control"/>
                            </div>

                            <!-- Các field cũ -->
                            <div class="mb-3">
                                <label class="form-label">Name</label>
                                <input type="text" name="fullName" class="form-control"
                                       value="${user.fullName}" required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input type="email" name="email" class="form-control"
                                       value="${user.email}" required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Phone</label>
                                <input type="text" name="phone" class="form-control"
                                       value="${user.phonenumbers}" pattern="\d+"
                                       title="Chỉ nhập số" required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input type="password" name="password" class="form-control" required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Confirm Password</label>
                                <input type="password" name="confirmPassword" class="form-control" required/>
                            </div>

                            <div class="d-flex justify-content-between">
                                <button type="submit" class="btn btn-success">Save Changes</button>
                                <a href="${pageContext.request.contextPath}/profile"
                                   class="btn btn-secondary">Back</a>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>

<%@ include file="/WEB-INF/include/footer.jsp" %>




