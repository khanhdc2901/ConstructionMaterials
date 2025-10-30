<%-- 
    Document   : footer2.jsp
    Created on : 19 Jun 2025, 8:46:19 PM
    Author     : Dai Minh Nhu - CE190213
--%>


     	<!-- Start Footer Section -->
 <footer class="text-center text-white">
  <!-- Grid container -->
  <div class="container p-5 pb-0">
    <!-- Section: Social media -->
    <section class="mb-4">
      <!-- Facebook -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-facebook-f"></i
      ></a>

      <!-- Twitter -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-twitter"></i
      ></a>

      <!-- Google -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-google"></i
      ></a>

      <!-- Instagram -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-instagram"></i
      ></a>

      <!-- Linkedin -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-linkedin-in"></i
      ></a>

      <!-- Github -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-github"></i
      ></a>
    </section>
    <!-- Section: Social media -->
  </div>
  <!-- Grid container -->

  <!-- Copyright -->
  <%
    java.time.Year currentYear = java.time.Year.now();
%>
  <div class="text-center pt-0 pb-5" >
    © <%= currentYear.getValue() %> Copyright:
    <a class="text-white" href="<%=request.getContextPath()%>/home">Peragi.com</a>
  </div>
  <!-- Copyright -->
</footer>
    </body>
</html>
