<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <link href="/assets/vendor/simple-datatables/style.css" rel="stylesheet">
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<br>
<div class="container">
    <div class="card recent-sales overflow-auto">
        <div class="card-body">
            <h5 class="card-title">Hóa đơn <span>| </span></h5>

            <table class="table table-hover datatable">
                <thead>
                <tr>
                    <th>Tên người nhân</th>
                    <th>Tổng tiền</th>
                    <th>Số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Trạng thái</th>
                    <th>Hang động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listBill}" var="bill">
                    <tr onclick="goToPage('/hoaDon/index/${bill.idHoaDon}')">
                        <td>${bill.tenNguoiNhan}</td>
                        <td>$${bill.tongTien}</td>
                        <td>${bill.soDienThoai}</td>
                        <td>${bill.diaChi}</td>
                        <td>${bill.ngayTao}</td>
                        <td>${bill.trangThaiHoaDon.tenTrangThaiHoaDon}</td>
                        <td>
                            <a href="/hoaDon/orderComplete/${bill.idHoaDon}" class="btn">Detail</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<br/>

<!-- Vendor JS Files -->
<script src="/assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/vendor/chart.js/chart.umd.js"></script>
<script src="/assets/vendor/echarts/echarts.min.js"></script>
<script src="/assets/vendor/quill/quill.min.js"></script>
<script src="/assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="/assets/vendor/tinymce/tinymce.min.js"></script>
<script src="/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="/assets/js/main.js"></script>
</body>

</html>