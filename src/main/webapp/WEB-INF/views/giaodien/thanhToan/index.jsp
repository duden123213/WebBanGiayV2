<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>

</head>

<br/>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h2>Thông tin thanh toán</h2>

            <form method="post" action="/hoaDon/placeorder">

                <div class="form-group">
                    <label for="receiverName">Tên người nhận:</label>
                    <c:choose>
                        <c:when test="${not empty customer}">
                            <input type="text" class="form-control" value="${customer.tenKhachHang}" id="receiverName"
                                   name="receiverName">
                            <c:if test="${not empty receiverNameError}">
                                <div class="text-danger">${receiverNameError}</div>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" value="${inputReceiverName}" id="receiverName"
                                   name="receiverName">
                            <c:if test="${not empty receiverNameError}">
                                <div class="text-danger">${receiverNameError}</div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group">
                    <label for="customerPhone">Số điện thoại:</label>
                    <c:choose>
                        <c:when test="${not empty customer}">
                            <input type="text" class="form-control" value="${customer.soDienThoai}" id="customerPhone"
                                   name="customerPhone">
                            <c:if test="${not empty customerPhoneError}">
                                <p class="text-danger">${customerPhoneError}</p>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" value="${inputCustomerPhone}" id="customerPhone"
                                   name="customerPhone">
                            <c:if test="${not empty customerPhoneError}">
                                <p class="text-danger">${customerPhoneError}</p>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group">
                    <label for="addressDelivery">Địa chỉ giao hàng:</label>
                    <c:choose>
                        <c:when test="${not empty customer}">
                            <textarea type="text" class="form-control" id="addressDelivery"
                                      name="addressDelivery">${customer.diaChi}</textarea>
                            <c:if test="${not empty addressDeliveryError}">
                                <p class="text-danger">${addressDeliveryError}</p>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                    <textarea class="form-control" id="addressDelivery" data-value="${inputReceiverName}"
                              name="addressDelivery"></textarea>
                            <c:if test="${not empty addressDeliveryError}">
                                <p class="text-danger">${addressDeliveryError}</p>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                <div class="form-group">
                <div class="alert alert-warning" th:if="${productOutOfStock != null}">
                    <p th:text="${productOutOfStock}"></p>
                </div>
                    <div class="py-3">
                        <label for="addressDelivery" class="form-label">Thanh toán:</label>
                        <input class="btn-check" value="2f7fbcf3-3007-4180-a5fe-84d2bcdf171b" type="radio"
                               name="PaymentId" id="flexRadioDefault1">
                        <label class="btn" for="flexRadioDefault1">
                            Tiền mặt
                        </label>

                    </div>
                </div>
                <button type="submit" class="btn">Đặt hàng</button>
            </form>
        </div>
       <!-- Giỏ hàng -->
              <div class="col-md-6">
                  <h2>Giỏ hàng của bạn</h2>
                  <table class="table table-striped">
                      <thead>
                      <tr>
                          <th>Sản phẩm </th>
                          <th>Giá</th>
                          <th>Số lượng</th>
                          <th>Tổng tiền</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${cartDetail}" var="gh">
                          <tr>
                              <td>${gh.chiTietSanPham.sanPham.tenSanPham}</td>
                              <td>${gh.gia}</td>
                              <td>${gh.soLuong}</td>
                              <td>${gh.chiTietSanPham.giaBan * gh.soLuong}VND</td>
                          </tr>
                      </c:forEach>
                      </tbody>
                  </table>
                  <h4>Tổng tiền: ${cart.tongTien}VND</h4>
              </div>
          </div>
      </div>