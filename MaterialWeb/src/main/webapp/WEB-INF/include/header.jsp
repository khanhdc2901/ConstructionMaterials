<%-- 
    Document   : header2
    Created on : 19 Jun 2025, 8:44:53 PM
    Author     : Dai Minh Nhu - CE190213
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="bootstrap, bootstrap4" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="<%=request.getContextPath()%>/assets/img/favcon.ico" type="image/x-icon">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <title>Web Vat lieu xay dung uy tin nhat Viet nam</title>

        <link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/assets/css/stylesheet.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Bootstrap Icons CDN -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">


        <c:if test="${not empty theReviewAddition}">
            <!--<script type="text/javascript" src="dist/js/jquery-1.10.2.js"></script>-->

            <script src="<%=request.getContextPath()%>/assets/js/forRating.js"></script>
            <style>
                .bd-placeholder-img {
                    font-size: 1.125rem;
                    text-anchor: middle;
                    -webkit-user-select: none;
                    -moz-user-select: none;
                    user-select: none;
                }

                @media (min-width: 768px) {
                    .bd-placeholder-img-lg {
                        font-size: 3.5rem;
                    }
                }
            </style>
        </c:if>

    </head>
    <body>
        <header>
            <!-- Start Header/Navigation -->
            <nav class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark">

                <div class="container">
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/home">Peragi<span>.</span></a>

                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarsFurni">
                        <ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
                            <!--<li class="nav-item active">-->


                            <%--<c:if test="${not empty user and user.admin}">  

                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Manager</a>
                                    <ul class=" custom-navbar dropdown-menu">
                                        <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/user">Manager User</a></li>
                                        <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/category">Manager Category</a></li>
                                        <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/product">Manager Product</a></li>
                                        <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/theReview">Manager Review</a></li>
                                        <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/sale">Manager Sale</a></li>
                                        <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/orders">Manager Orders</a></li>
                                        <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/report">Manager Report</a></li>
                                    </ul>
                                </li>
                            </c:if>--%>



                            <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath()%>/home">Home</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/display" data-bs-toggle="dropdown">Products</a>
                                <ul class=" custom-navbar dropdown-menu ">
                                    <li >
                                        <a class="dropdown-item border-bottom " href="${pageContext.request.contextPath}/display">
                                            ALL PRODUCTS
                                        </a>
                                    </li>
                                    <c:forEach var="c" items="${categories}">
                                        <li>
                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/display?cid=${c.id}">
                                                ${c.name}
                                            </a>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/order?view=order">Order</a>
                            </li>


                            <li>
                                <a href="<%=request.getContextPath()%>/carts">
                                    <svg width="40px" height="40px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" stroke="#ffffff"><g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                                    <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier">
                                    <path d="M6.29977 5H21L19 12H7.37671M20 16H8L6 3H3M9 20C9 20.5523 8.55228 21 8 21C7.44772 21 7 20.5523 7 20C7 19.4477 7.44772 19 8 19C8.55228 19 9 19.4477 9 20ZM20 20C20 20.5523 19.5523 21 19 21C18.4477 21 18 20.5523 18 20C18 19.4477 18.4477 19 19 19C19.5523 19 20 19.4477 20 20Z" stroke="#ffffff" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path> </g></svg>
                                </a>
                            </li>


                            <c:choose>
                                <c:when test="${not empty user}">
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle user-icon" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            <svg width="40px" height="40px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" stroke="#ffffff">
                                            <path d="M12.12 12.78C12.05 12.77 11.96 12.77 11.88 12.78C10.12 12.72 8.71997 11.28 8.71997 9.50998C8.71997 7.69998 10.18 6.22998 12 6.22998C13.81 6.22998 15.28 7.69998 15.28 9.50998C15.27 11.28 13.88 12.72 12.12 12.78Z" stroke="#ffffff" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                            <path d="M18.74 19.3801C16.96 21.0101 14.6 22.0001 12 22.0001C9.40001 22.0001 7.04001 21.0101 5.26001 19.3801C5.36001 18.4401 5.96001 17.5201 7.03001 16.8001C9.77001 14.9801 14.25 14.9801 16.97 16.8001C18.04 17.5201 18.64 18.4401 18.74 19.3801Z" stroke="#ffffff" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                            <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="#ffffff" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                            </svg>
                                        </a>
                                        <ul class=" custom-navbar dropdown-menu" aria-labelledby="userDropdown">
                                            <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/profile">My Profile</a></li>
                                            <c:if test="${user.admin}">      
                                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user">Manager User</a></li>
                                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/category">Manager Category</a></li>
                                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/product">Manager Product</a></li>
                                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/theReview">Manager Review</a></li>
                                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/sale">Manager Sale</a></li>
                                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/report">Manager Reports</a></li>
                                                <li><a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/orders">Manager Orders</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                </c:if>
                                            
                                            <li>
                                                <form action="<%= request.getContextPath()%>/auth" method="post" style="display: inline;">
                                                    <button type="submit" name="action" value="LOGOUT" class="dropdown-item text-light">Logout</button>
                                                </form>
                                            </li>
                                        </ul>
                                    </li>
                                </c:when>
                                <c:otherwise>

                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle user-icon" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            <svg width="40px" height="40px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" stroke="#ffffff">
                                            <path d="M12.12 12.78C12.05 12.77 11.96 12.77 11.88 12.78C10.12 12.72 8.71997 11.28 8.71997 9.50998C8.71997 7.69998 10.18 6.22998 12 6.22998C13.81 6.22998 15.28 7.69998 15.28 9.50998C15.27 11.28 13.88 12.72 12.12 12.78Z" stroke="#ffffff" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                            <path d="M18.74 19.3801C16.96 21.0101 14.6 22.0001 12 22.0001C9.40001 22.0001 7.04001 21.0101 5.26001 19.3801C5.36001 18.4401 5.96001 17.5201 7.03001 16.8001C9.77001 14.9801 14.25 14.9801 16.97 16.8001C18.04 17.5201 18.64 18.4401 18.74 19.3801Z" stroke="#ffffff" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                            <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="#ffffff" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                            </svg>
                                        </a>
                                        <ul class=" custom-navbar dropdown-menu" aria-labelledby="userDropdown">


                                            <li>
                                                <a class="dropdown-item border-bottom" href="<%= request.getContextPath()%>/auth?view=login">Login or Register</a>
                                            </li>
                                        </ul>
                                    </li>
                                </c:otherwise>
                            </c:choose>



                            <c:choose>
                                <c:when test="${not empty user}">
                                    <li class="nav-item">
                                        <span class="nav-link text-white">Hello, ${user.fullName}</span>
                                    </li>
                                    <form action="<%= request.getContextPath()%>/auth" method="post">
                                        <li class="nav-item">
                                            <button class="nav-link btn btn-outline-light btn-sm logbutton" type="submit" name="action" value="LOGOUT" class="btn">Logout</button>
                                        </li>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <li class="nav-item">
                                        <a class=" btn btn-outline-light btn logbutton" href="<%= request.getContextPath()%>/auth?view=login">Login</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>


                        </ul>

                    </div>
                </div>

            </nav>
<div class="modal fade" id="contactModal" tabindex="-1" aria-labelledby="contactModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content bg-white text-dark">
      <div class="modal-header">
        <h5 class="modal-title" id="contactModalLabel">Contact Us</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <p><strong>Email:</strong> peragi@gmail.com</p>
        <p><strong>Phone:</strong> 0942XXX134</p>
      </div>
    </div>
  </div>
</div>

        </header>