<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<div class="container">
    <br/>
    <div class="row">
        <div class="col-8">
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead>
                    <tr>
                        <%--                  <th scope="col">--%>
                        <%--                    <input class="form-check-input" type="checkbox" onchange="checkAll(this)" />--%>
                        <%--                  </th>--%>
                        <th scope="col" class="text-sp"colspan="2">Sản Phâm</th>
                        <th scope="col" class="text-sp">Màu sắc</th>
                        <th scope="col" class="text-sp">Số lượng</th>
                        <th scope="col" class="text-sp">Tổng tiền</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listCartDetail}" var="gh">
                        <tr>
                            <td>
                                <img src="/assets/img/sanPham/${gh.hinhAnh}" alt="" height="65px"/>
                            </td>
                            <td>
                                <p>${gh.ten}</p>
                                <a href="/gioHang/delete/${gh.chiTietSanPhamid}">
                                    <span class="fa fa-trash"></span>
                                </a>
                            </td>
                            <td>
                                <p>${gh.tenMau}</p>
                            </td>
                            <td>
                                <div class="input-group mb-3">
                                    <a href="/gioHang/reduce/${gh.chiTietSanPhamid}" class="btn btn-outline">-</a>
                                    <input disabled type="text" class="form-control" value="${gh.soLuong}"/>
                                    <a href="/gioHang/increase/${gh.chiTietSanPhamid}" class="btn btn-outline">+</a>
                                </div>
                            </td>
                            <td style="font-weight: bold;color: red">$${gh.gia}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-4">
            <div class="table-responsive">
                <div class="checkout">
                    <p>Chúc mừng! Bạn đã được miễn phí vận chuyển!</p>
                    <div class="total">
                        <span>Mặt hàng(s) tổng tiền</span>
                        <c:choose>
                            <c:when test="${listCartDetail == null}">
                                <span class="after" style="font-weight: bold;color: red">$0</span>
                            </c:when>
                            <c:otherwise>
                                <span class="after" style="font-weight: bold;color: red">$${cart.tongTien}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="ship">
                        <div>
                            <span>Giao Hàng</span>
                            <span class="after">0vnđ</span>
                        </div>
                        <div>
                            <span class="location">(To Hanoi)</span>
                            <span class="delivery-fee">30.000vnđ</span>
                        </div>
                    </div>
                    <hr/>
                    <div class="total-item">
                        <c:choose>
                            <c:when test="${listCartDetail == null}">
                                <span>Total (0 mặt hàng)</span>
                                <span class="after" style="font-weight: bold;color: red">$0</span>
                            </c:when>
                            <c:otherwise>
                                <span>Total (${cart.soLuong} mặt hàng)</span>
                                <span class="after" style="font-weight: bold;color: red">$${cart.tongTien}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="d-grid">
                        <a href="/bill/payment" class="btn">Thanh toán</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
</div>