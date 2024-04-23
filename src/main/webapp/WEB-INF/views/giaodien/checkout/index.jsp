<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<div class="container">
    <br/>
    <div class="row main-checkout">
        <div class="col-7">
            <h2>Chi tiết đặt hàng</h2>
            <table class="table">

                <thead>
                <tr>
                    <th>Sản phẩm</th>
                    <th class="text-end">Tổng tiền</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${billDetail}" var="b">
                    <tr>
                        <td>
                            <a>${b.tenSanPham} - ${b.tenMauSac}</a> <strong>×&nbsp;${b.soLuong}</strong>
                            <ul class="list-unstyled">
                                <li><strong>Màu sắc:</strong>
                                    <p>${b.tenMauSac}</p>
                                </li>
                            </ul>
                        </td>
                        <td style="text-align: right">
                            <span><bdi class="money"><span>$</span>${b.chiTietSanPham.giaBan}</bdi></span>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>

                <tfoot>
                <tr>
                    <th scope="row">Tổng tiền:</th>
                    <td class="text-end"><span class="money"><span>$</span>${bill.tongTien}</span></td>
                </tr>
                <tr>
                    <th scope="row">Giao hàng:</th>
                    <td class="text-end"><span><span>$</span>0</span>&nbsp;<small>giao hàng tại Hà Nội</small></td>
                </tr>
                <tr>
                    <th scope="row">Phương thức thanh toán:</th>
                    <td class="text-end">${bill.phuongThucThanhToan.tenPhuongThucThanhToan}</td>
                </tr>
                <tr>
                    <th scope="row">Tổng tiền:</th>
                    <td class="text-end"><span class="money"><span>$</span>${bill.tongTien}</span></td>
                </tr>
                </tfoot>
            </table>
        </div>
        <div class="col-5">
            <div class="thankyou-order">
                <p class="thankyou-order-received">
                    <strong>"Cảm ơn bạn. Đơn đặt hàng của bạn đã được nhận."</strong>
                </p>

                <ul>
                    <li>
                        Mã đơn: <strong>${bill.idHoaDon}</strong>
                    </li>
                    <li>
                        Ngày tạo: <strong>${bill.ngayTao}</strong>
                    </li>
                    <li>
                        Tổng tiền: <strong><span><bdi class="money">${bill.tongTien}<span>vnđ</span></bdi></span></strong>
                    </li>
                    <li>
                        Phương thức thanh toán: <strong>${bill.phuongThucThanhToan.tenPhuongThucThanhToan}</strong>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <br/>
</div>
<br/>
