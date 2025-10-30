<%-- 
    Document   : statistic
    Created on : Jul 12, 2025, 6:36:22 PM
    Author     : Tieu Gia Huy - CE191594
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>  
<%@ page contentType="text/html;charset=UTF-8" %>  
<%@ include file="/WEB-INF/include/header.jsp" %>

<div class="container mt-4">
    <h2>Thống kê</h2>

    <!-- Hiện error nếu có -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/report" method="get" class="row g-3">
        <!-- Chọn loại -->
        <div class="col-md-2">
            <label class="form-label">Loại:</label>
            <select name="type" class="form-select">
                <option value="order">Đơn hàng</option>
                <option value="product">Sản phẩm</option>
            </select>
        </div>

        <!-- Chọn năm -->
        <div class="col-md-2">
            <label class="form-label">Năm:</label>
            <input type="number" name="year" value="2025" class="form-control"/>
        </div>

        <!-- Chọn cấp độ -->
        <div class="col-md-2">
            <label class="form-label">Theo:</label>
            <select name="period" class="form-select" onchange="onPeriodChange(this.value)">
                <option value="day">Ngày</option>
                <option value="month">Tháng</option>
                <option value="quarter">Quý</option>
            </select>
        </div>

        <!-- Ngày -->
        <div class="col-md-2" id="divDate">
            <label class="form-label">Chọn ngày:</label>
            <input type="date" name="date" class="form-control"/>
        </div>

        <!-- Tháng -->
        <div class="col-md-2" id="divMonth" style="display:none">
            <label class="form-label">Chọn tháng:</label>
            <input type="month" name="month" class="form-control"/>
        </div>

        <!-- Quý -->
        <div class="col-md-2" id="divQuarter" style="display:none">
            <label class="form-label">Chọn quý:</label>
            <select name="quarter" class="form-select">
                <option value="1">Quý 1</option>
                <option value="2">Quý 2</option>
                <option value="3">Quý 3</option>
                <option value="4">Quý 4</option>
            </select>
        </div>

        <!-- Nút thống kê -->
        <div class="col-md-2 align-self-end">
            <button type="submit" class="btn btn-primary">Thống kê</button>
        </div>
    </form>
</div>

<script>
    function onPeriodChange(p) {
        document.getElementById('divDate').style.display = p === 'day' ? '' : 'none';
        document.getElementById('divMonth').style.display = p === 'month' ? '' : 'none';
        document.getElementById('divQuarter').style.display = p === 'quarter' ? '' : 'none';
    }
</script>

<%@ include file="/WEB-INF/include/footer.jsp" %>




