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
            <h5 class="card-title">Bill <span>| </span></h5>

            <table class="table table-hover datatable">
                <thead>
                <tr>
                    <th>ReceiverName</th>
                    <th>TotalMoney</th>
                    <th>CustomerPhone</th>
                    <th>AddressDelivery</th>
                    <th>CreatedDate</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listBill}" var="bill">
                    <tr onclick="goToPage('/bill_detail/index/${bill.id}')">
                        <td>${bill.receiverName}</td>
                        <td>$${bill.totalMoney}</td>
                        <td>${bill.customerPhone}</td>
                        <td>${bill.addressDelivery}</td>
                        <td>${bill.createdDate}</td>
                        <td>${bill.billStatus.name}</td>
                        <td>
                            <a href="/bill/orderComplete/${bill.id}" class="btn">Detail</a>
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