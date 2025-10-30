<%-- 
    Document   : rating
    Created on : 4 Jul 2025, 7:06:24 AM
    Author     : Dai Minh Nhu - CE190213
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="theReviewAddition" value="It is"/>

<%@include file="/WEB-INF/include/header.jsp" %>
<section id="comment_section">
    <div class="album py-5 bg-light">
        <div class="container">



            <h3>
                List of Comment
            </h3>

            <c:choose>
                <c:when test="${empty reviewList}">
                    Hiện tại chưa có comment
                </c:when>
                <c:otherwise>
                    <c:forEach var="theReview" items="${reviewList}" varStatus="loop">
                        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                            <div class="col-12">
                                <div class="card shadow-sm">
                                    <div class="card-body d-flex align-items-center mb-3">
                                        <img src="<c:url value="/assets/img/avatar-default-icon.png"/>" class="rounded me-3 img-fluid" width="10%" alt="User" />
                                        <div>
                                            <p class="mb-0">
                                                <strong>Product:</strong>
                                                <a href="<c:url value="/display">
                                                       <c:param name="view" value="detail"/>
                                                       <c:param name="id" value="${theReview.product.id}"/>
                                                   </c:url>" class="mb-0">
                                                    ${theReview.product.name}
                                                </a>
                                            </p>
                                            <small class="text-muted">
                                                User: ${theReview.user.fullName} - 
                                                <c:forEach var="star" begin="1" end="${theReview.rating}">
                                                    ⭐
                                                </c:forEach>
                                            </small>

                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">${theReview.review}</p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <form method="post" action="<c:url value="/theReview">
                                                      <c:param name="pId" value="${theReview.product.id}"/>
                                                      <c:param name="uId" value="${theReview.user.userid}"/>
                                                  </c:url>">
                                                <div class="btn-group">
                                                    <button type="submit" name="action" value="remove" class="btn btn-sm btn-outline-secondary">Remove</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item ${(empty param.page || param.page <= 1)?"disabled":""}">
                                <a class="page-link" href="<c:url value="/theReview">
                                       <c:param name="view" value="list"/>
                                       <c:param name="page" value="${param.page - 1}"/>
                                   </c:url>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                                <li class="page-item ${((empty param.page && i == 1) || param.page == i)?"active":""}">
                                    <a class="page-link" href="<c:url value="/theReview">
                                           <c:param name="view" value="list"/>
                                           <c:param name="page" value="${i}"/>
                                       </c:url>">${i}</a></li>
                                </c:forEach>
                            <li class="page-item ${(empty param.page || requestScope.totalPages <= param.page)?"disabled":""}">
                                <a class="page-link" href="<c:url value="/theReview">
                                       <c:param name="view" value="list"/>
                                       <c:param name="page" value="${param.page + 1}"/>
                                   </c:url>" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:otherwise>
            </c:choose>


        </div>
    </div>
</section>
<%@include file="/WEB-INF/include/footer.jsp" %>
