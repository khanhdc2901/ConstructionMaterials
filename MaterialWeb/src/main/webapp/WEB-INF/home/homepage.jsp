<%-- 
    Document   : homepage
    Created on : Jun 18, 2025, 8:53:53 AM
    Author     : Huynh Thai Duy Phuong - CE190603 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
  <link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/assets/css/stylesheet.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
    
    <body>
        <main>
	<!-- Start Hero Section -->
			<div class="hero">
				<div class="container">
					<div class="row justify-content-between">
						<div class="col-lg-5">
							<div class="intro-excerpt">
								<h1>Peragi<span class="d-block">SE1902 - G2</span></h1>
								<p class="mb-4">Your trusted source for high-quality building materials. We provide everything you need for durable construction and stunning finishes.</p>
								<p><a href="<%=request.getContextPath()%>/display" class="btn btn-secondary me-2">Shop Now</a><a href="#" class="btn btn-white-outline">Explore</a></p>
							  <!-- Search Bar -->
    <form class="d-flex mt-3" action="<%=request.getContextPath()%>/display" method="get">
  <input class="form-control me-2" type="search" name="keyword" placeholder="Search for products..." required>
  <button class="btn btn-dark" type="submit">Search</button>
</form>

                                                        </div>
						</div>
                                            
						<div class="col-lg-7">
							<div class="hero-img-wrap">
								<img src="<%= request.getContextPath() %>/assets/img/banner.png" class="img-fluid">
							</div>
						</div>
					</div>
				</div>
			</div>
		<!-- End Hero Section -->
<!-- Highlight Carousel - Full Width -->
<section class="py-5 bg-light">
  <div class="container-fluid text-center">
   <h1 class="mb-5 fw-bold display-5">Discover Our Highlights</h1>

    <div id="highlightCarousel" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">

        <!-- Slide 1 -->
        <div class="carousel-item active">
          <div class="d-flex justify-content-center">
            <div class="card shadow w-100 mx-3" style="max-width: 1000px;">
              <img src="<%=request.getContextPath()%>/assets/img/quote.png" class="card-img-top" alt="Highlight 1" style="object-fit: cover; height: 400px;">
              <div class="card-body text-center">
                       </div>
            </div>
          </div>
        </div>

        <!-- Slide 2 -->
        <div class="carousel-item">
          <div class="d-flex justify-content-center">
            <div class="card shadow w-100 mx-3" style="max-width: 1000px;">
              <img src="<%=request.getContextPath()%>/assets/img/quote2.png" class="card-img-top" alt="Highlight 2" style="object-fit: cover; height: 400px;">
              <div class="card-body text-center">

              
              </div>
            </div>
          </div>
        </div>

        <!-- Slide 3 -->
        <div class="carousel-item">
          <div class="d-flex justify-content-center">
            <div class="card shadow w-100 mx-3" style="max-width: 1000px;">
              <img src="<%=request.getContextPath()%>/assets/img/quote3.png" class="card-img-top" alt="Highlight 3" style="object-fit: cover; height: 400px;">
              <div class="card-body text-center">
            
              </div>
            </div>
          </div>
        </div>

      </div>

      <!-- Controls -->
      <button class="carousel-control-prev" type="button" data-bs-target="#highlightCarousel" data-bs-slide="prev">
  <i class="fas fa-chevron-left fs-2 text-white bg-dark rounded-circle p-3 shadow"></i>
</button>
   <button class="carousel-control-next" type="button" data-bs-target="#highlightCarousel" data-bs-slide="next">
  <i class="fas fa-chevron-right fs-2 text-white bg-dark rounded-circle p-3 shadow"></i>
</button>
    </div>
  </div>
</section>





<!-- About Us Section -->
<section class="py-5 bg-white border-top">
  <div class="container">
    <h2 class="text-center fw-bold mb-5">Meet Our Team</h2>
    <div class="row g-4 justify-content-center">

      <!-- Dai Minh Nhu -->
      <div class="col-md-4 col-lg-2">
        <div class="card h-100 text-center shadow-sm border-success">
          <img src="<%=request.getContextPath()%>/assets/img/nhu.jpg" class="card-img-top rounded-circle mx-auto mt-4" style="width:100px; height:100px; object-fit:cover;" alt="Dai Minh Nhu">
          <div class="card-body">
            <h6 class="card-title fw-bold mb-1">Dai Minh Nhu</h6>
            <p class="text-muted small">"Durability for every project."</p>
          </div>
        </div>
      </div>

      <!-- Nguyen Thanh Nhan -->
      <div class="col-md-4 col-lg-2">
        <div class="card h-100 text-center shadow-sm border-success">
          <img src="<%=request.getContextPath()%>/assets/img/nhan.jpg" class="card-img-top rounded-circle mx-auto mt-4" style="width:100px; height:100px; object-fit:cover;" alt="Nguyen Thanh Nhan">
          <div class="card-body">
            <h6 class="card-title fw-bold mb-1">Nguyen Thanh Nhan</h6>
            <p class="text-muted small">"Quality builds trust."</p>
          </div>
        </div>
      </div>

      <!-- Le Duy Khanh -->
      <div class="col-md-4 col-lg-2">
        <div class="card h-100 text-center shadow-sm border-success">
          <img src="<%=request.getContextPath()%>/assets/img/khanh.jpg" class="card-img-top rounded-circle mx-auto mt-4" style="width:100px; height:100px; object-fit:cover;" alt="Le Duy Khanh">
          <div class="card-body">
            <h6 class="card-title fw-bold mb-1">Le Duy Khanh</h6>
            <p class="text-muted small">"Structure starts with selection."</p>
          </div>
        </div>
      </div>

      <!-- Tieu Gia Huy -->
      <div class="col-md-4 col-lg-2">
        <div class="card h-100 text-center shadow-sm border-success">
          <img src="<%=request.getContextPath()%>/assets/img/huy.jpg" class="card-img-top rounded-circle mx-auto mt-4" style="width:100px; height:100px; object-fit:cover;" alt="Tieu Gia Huy">
          <div class="card-body">
            <h6 class="card-title fw-bold mb-1">Tieu Gia Huy</h6>
            <p class="text-muted small">"Optimizing the customer journey."</p>
          </div>
        </div>
      </div>

      <!-- Huynh Thai Duy Phuong -->
      <div class="col-md-4 col-lg-2">
        <div class="card h-100 text-center shadow-sm border-success">
          <img src="<%=request.getContextPath()%>/assets/img/phuong.jpg" class="card-img-top rounded-circle mx-auto mt-4" style="width:100px; height:100px; object-fit:cover;" alt="Huynh Thai Duy Phuong">
          <div class="card-body">
            <h6 class="card-title fw-bold mb-1">Huynh Thai Duy Phuong</h6>
            <p class="text-muted small">"Code the future of e-commerce."</p>
          </div>
        </div>
      </div>

    </div>
  </div>
</section>


<!-- Sale Banner -->
<div class="container-fluid py-4 text-white" style="background: linear-gradient(90deg, #ff8a00, #e52e71);">
  <div class="container">
    <div class="row align-items-center justify-content-center text-center">
      <div class="col-md-10">
        <h2 class="fw-bold mb-2">
          <img src="<%=request.getContextPath()%>/assets/img/new-star.gif" alt="Star" class="me-2" style="width:32px; height:32px;">
          Limited Time Offer!
        </h2>
        <p class="lead mb-0">
          Enjoy up to <strong>50% off</strong> on selected items. Act fast before it's gone!
        </p>
      </div>
    </div>
  </div>
</div>
<!-- End Sale Banner -->




<!--------Categories--------->
<div class="container-fluid  text-dark py-0">
  <div class="container text-center">
    <h1 class="fw-bold mt-5 mb-0 bg-light text-dark p-2 border-success d-inline-block">What are you looking for?</h1>
  
  </div>
</div>
<%@ page import="java.util.*, model.Category, dao.CategoriesDao" %>

<%
    CategoriesDao dao = new CategoriesDao();
    List<Category> categories = dao.getAllCategories();
%>

<section class="py-5">
  <div class="container">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
      <% for (Category c : categories) { %>
      <div class="col">
        <a href="${pageContext.request.contextPath}/display?cid=<%= c.getId() %>" class="text-decoration-none">
          <div class="card h-100 text-center border-2 border-dark">
            <div class="card-body">
              <div class="mb-3">
                <i class="bi bi-ui-radios-grid fs-1 text-dark"></i>
              </div>
              <h5 class="card-title text-dark"><%= c.getName() %></h5>
            </div>
          </div>
        </a>
      </div>
      <% } %>
    </div>
     <div class="text-center">
      <a href="<%=request.getContextPath()%>/display" class="btn btn-dark btn-lg">
        View All ➪
      </a>
    </div>
  </div>
</section>







        </main> 
                <!-- footer -->
                <%@include file="/WEB-INF/include/footer.jsp" %>
	
    </body>
</html>
