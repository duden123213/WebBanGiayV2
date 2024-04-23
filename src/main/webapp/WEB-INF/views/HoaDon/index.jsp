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
                                    <th>Địa chỉ giao hàng</th>
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
                                            <a href="/chiTietHoaDon/index/${bill.idHoaDon}" class="btn btn-success">Hiển thị</a>

                                            <c:if test="${bill.trangThaiHoaDon.idTrangThaiHoaDon ne '159b8bc3-5489-47c0-a115-b94a0cf6286f'}">
                                               <button type="button" class="btn btn-danger" onclick="showCancelReason()">Hủy đơn hàng</button>

                                                   <form id="cancelForm" action="/hoaDon/cancel_bill/${bill.idHoaDon}" method="post" style="display:none;">
                                                       <div class="form-group">
                                                           <label for="cancelReason">Lý do hủy đơn hàng:</label>
                                                           <select class="form-control" id="cancelReason" name="cancelReason">
                                                               <option value="Out of stock">Hết hàng</option>
                                                               <option value="Change of mind">Thay đổi ý định</option>
                                                               <option value="Delivery issues">Vấn đề giao hàng</option>
                                                               <option value="Other">Lý do khác</option>
                                                           </select>
                                                       </div>

                                                       <button type="button" class="btn btn-secondary" onclick="hideCancelReason()">Hủy</button>
                                                       <button type="button" class="btn btn-danger" onclick="confirmCancellation()">Xác nhận hủy đơn hàng</button>
                                                   </form>

                                                   <script>
                                                       function showCancelReason() {
                                                           document.getElementById('cancelForm').style.display = 'block';
                                                       }

                                                       function hideCancelReason() {
                                                           document.getElementById('cancelForm').style.display = 'none';
                                                       }

                                                       function confirmCancellation() {
                                                           if (confirm('Bạn có chắc chắn muốn hủy đơn hàng này không?')) {
                                                               document.getElementById('cancelForm').submit();
                                                           }
                                                       }
                                                   </script>
                                            </c:if>
                                            <c:if test="${bill.trangThaiHoaDon.idTrangThaiHoaDon eq '159b8bc3-5489-47c0-a115-b94a0cf6286f'}">
                                                <p>Đơn hàng đã được hủy</p>
                                            </c:if>
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
