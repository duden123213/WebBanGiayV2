<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Bill Management</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
            <li class="breadcrumb-item active">Tổng quát</li>
            <li class="breadcrumb-item active">Hóa đơn</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-12">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">


                        <div class="card-body">
                            <h5 class="card-title">Hóa đơn <span>| </span></h5>

                            <table class="table table-hover datatable">
                                <thead>
                                <tr>
                                    <th>Tên người nhận</th>
                                    <th>Số điện thoại</th>
                                    <th>Địa chỉ giao hang</th>
                                    <th>Tổng tiền</th>
                                    <th>Ngày tạo</th>
                                    <th>Trạng thái</th>
                                    <th>Thanh toán</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listBill}" var="bill">
                                    <tr onclick="goToPage('/bill_detail/index/${bill.idHoaDon}')">

                                        <td>${bill.tenNguoiNhan}</td>
                                        <td>${bill.soDienThoai}</td>
                                        <td>${bill.diaChi}</td>
                                        <td>${bill.tongTien}</td>
                                        <td>${bill.ngayTao}</td>
                                        <td>${bill.trangThaiHoaDon.tenTrangThaiHoaDon}</td>
                                        <td>${bill.phuongThucThanhToan.tenPhuongThucThanhToan}</td>
                                        <td>
                                            <a href="/chiTietHoaDon/index/${bill.idHoaDon}" class="btn btn-success">Hiện thị</a>
                                            <form action="/hoaDon/change_bill_status/${bill.idHoaDon}" method="post">
                                                <button class=" ${bill.trangThaiHoaDon.idTrangThaiHoaDon!="159b8bc3-5489-47c0-a115-b94a0cf6286f"?"btn btn-warning":"btn btn-dark"}"
                                                        onclick="return confirm('Bạn có muốn thay đổi trang thái?')"
                                                    ${bill.trangThaiHoaDon.idTrangThaiHoaDon=="159b8bc3-5489-47c0-a115-b94a0cf6286f"?"disabled":""}>${bill.trangThaiHoaDon.idTrangThaiHoaDon=="159b8bc3-5489-47c0-a115-b94a0cf6286f"?"Xong":"Xác nhận"}</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>


                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->


    </div><!-- End Right side columns -->

    </div>

    <script>
        function goToPage(url) {
            window.location.href = url;
        }
    </script>
</section>



