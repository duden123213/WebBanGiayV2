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
            <h2>Order details</h2>
            <table class="table">

                <thead>
                <tr>
                    <th>Product</th>
                    <th class="text-end">Total</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${billDetail}" var="b">
                    <tr>
                        <td>
                            <a>${b.productName} - ${b.colorName}</a> <strong>×&nbsp;${b.quantity}</strong>
                            <ul class="list-unstyled">
                                <li><strong>Color:</strong>
                                    <p>${b.colorName}</p>
                                </li>
                            </ul>
                        </td>
                        <td style="text-align: right">
                            <span><bdi class="money"><span>$</span>${b.productDetail.price}</bdi></span>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>

                <tfoot>
                <tr>
                    <th scope="row">Subtotal:</th>
                    <c:choose>
                        <c:when test="${not empty usedVoucher}">
                            <td class="text-end"><span class="money"><span>$</span>${usedVoucher.subTotal}</span></td>
                        </c:when>
                        <c:otherwise>
                            <td class="text-end"><span class="money"><span>$</span>${bill.totalMoney}</span></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <c:if test="${not empty voucher}">
                    <tr>
                        <th scope="row">Discount:</th>
                        <td class="text-end"><span class="money"><span>$</span>${voucher.value}</span></td>
                    </tr>
                </c:if>
                <tr>
                    <th scope="row">Shipping:</th>
                    <td class="text-end"><span><span>$</span>0</span>&nbsp;<small>via Delivery in Hanoi</small></td>
                </tr>
                <tr>
                    <th scope="row">Payment methods:</th>
                    <td class="text-end">${bill.payment.name}</td>
                </tr>
                <tr>
                    <th scope="row">Total Money:</th>
                    <td class="text-end"><span class="money"><span>$</span>${bill.totalMoney}</span></td>
                </tr>
                </tfoot>
            </table>
        </div>
        <div class="col-5">
            <div class="thankyou-order">
                <p class="thankyou-order-received">
                    <strong>Thank you. Your order has been received.</strong>
                </p>

                <ul>
                    <li>
                        Code orders: <strong>${bill.id}</strong>
                    </li>
                    <li>
                        Day: <strong>${bill.createdDate}</strong>
                    </li>
                    <li>
                        Total Money: <strong><span><bdi class="money"><span>$</span>${bill.totalMoney}</bdi></span></strong>
                    </li>
                    <li>
                        Payment methods: <strong>${bill.payment.name}</strong>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <br/>
</div>
<br/>