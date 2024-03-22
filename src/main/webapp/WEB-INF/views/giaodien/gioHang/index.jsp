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
                        <th scope="col" class="text-sp"colspan="2">Product</th>
                        <th scope="col" class="text-sp">Color</th>
                        <th scope="col" class="text-sp">Quantity</th>
                        <th scope="col" class="text-sp">Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listCartDetail}" var="gh">
                        <tr>
                            <td>
                                <img src="/assets/img/product/${gh.image}" alt="" height="65px"/>
                            </td>
                            <td>
                                <p>${gh.name}</p>
                                <a href="/cart/delete/${gh.productDetailId}">
                                    <span class="fa fa-trash"></span>
                                </a>
                            </td>
                            <td>
                                <p>${gh.colorName}</p>
                            </td>
                            <td>
                                <div class="input-group mb-3">
                                    <a href="/cart/reduce/${gh.productDetailId}" class="btn btn-outline">-</a>
                                    <input disabled type="text" class="form-control" value="${gh.quantity}"/>
                                    <a href="/cart/increase/${gh.productDetailId}" class="btn btn-outline">+</a>
                                </div>
                            </td>
                            <td style="font-weight: bold;color: red">$${gh.productPrice}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-4">
            <div class="table-responsive">
                <div class="checkout">
                    <p>Congratulations! You've got free shipping!</p>
                    <div class="total">
                        <span>Item(s) total</span>
                        <c:choose>
                            <c:when test="${listCartDetail == null}">
                                <span class="after" style="font-weight: bold;color: red">$0</span>
                            </c:when>
                            <c:otherwise>
                                <span class="after" style="font-weight: bold;color: red">$${cart.totalMoney}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="ship">
                        <div>
                            <span>Shipping</span>
                            <span class="after">$0</span>
                        </div>
                        <div>
                            <span class="location">(To Hanoi)</span>
                            <span class="delivery-fee">$1</span>
                        </div>
                    </div>
                    <hr/>
                    <div class="total-item">
                        <c:choose>
                            <c:when test="${listCartDetail == null}">
                                <span>Total (0 items)</span>
                                <span class="after" style="font-weight: bold;color: red">$0</span>
                            </c:when>
                            <c:otherwise>
                                <span>Total (${cart.quantity} items)</span>
                                <span class="after" style="font-weight: bold;color: red">$${cart.totalMoney}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="d-grid">
                        <a href="/bill/payment" class="btn">Check out</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
</div>