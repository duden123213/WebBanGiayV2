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
        <div class="col-6 row">
            <div class="product-content-big-img col-9">
                <img src="/assets/img/sanPham/${image.tenHinhAnh}" alt="" />
            </div>
            <div class="product-content-small-img col-3">
                <c:forEach items="${listImage}" var="sp">
                    <img src="/assets/img/sanPham/${sp.tenHinhAnh}" alt="" />
                </c:forEach>
            </div>
        </div>
        <div class="col-6">
            <h5>${productDetail.ten}</h5>
            <p>${productDetail.daBan} <span class="daBan">Đã bán</span></p>
            <p class="giaBan">$${productDetail.giaBan}</p>
            <div class="product-content-product-color">
                <p><span style="font-weight: bold;">Color: </span><span class="product-content-product-color-name">${productDetail.mauSac.tenMauSac}</span></p>
                <ul class="list-inline">
                    <c:forEach items="${listColor}" var="color">
                        <c:choose>
                            <c:when test="${color.chiTietSanPhamId == productDetail.id}">
                                <li class="list-inline-item">
                                    <a href="/chiTietSanPham/indexcus/${color.chiTietSanPhamId}" class="product-content-product-color-img-active">
                                        <img src="/assets/img/color/${color.hinhAnh}" alt="" value="${color.id}" />
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="list-inline-item">
                                    <a href="/chiTietSanPham/indexcus/${color.chiTietSanPhamId}" class="product-content-product-color-img">
                                        <img src="/assets/img/color/${color.hinhAnh}" alt="" value="${color.id}" />
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
            <div class="row mb-3">
                <div class="col-3 my-2">
                    <span class="text-content">Số lượng</span>
                </div>
                <div class="col-3">
                    <div class="input-group mb-3">
                        <a href="/chiTietSanPham/reduce/${productDetail.id}" class="btn btn-outline">-</a>
                        <input disabled type="text" class="form-control" value="${productDetail.soLuong}"/>
                        <a href="/chiTietSanPham/increase/${productDetail.id}" class="btn btn-outline">+</a>
                    </div>
                </div>
                <div class="col-6 my-2">
                    <span class="text-content">${productDetail.soLuongTon} Sản phẩm có sẵn</span>
                </div>
            </div>
            <div class="d-grid gap-2">
                <div class="row">
                    <div class="col-10">
                        <a class="btn btn-outline" href="/gioHang/add/${productDetail.id}">
                            ADD TO CART
                        </a>
                    </div>
                    <c:if test="${CustomerName != null}">
                        <form class="col-2" action="/favor/like/${productDetail.id}" method="post">
                            <button type="submit" class="fa fa-heart-o"></button>
                        </form>
                    </c:if>
                </div>
                <button class="btn" type="button">Mua ngay</button>
            </div>
            <br>
            <strong>Cam kết của NewSeven</strong>
            <ul class="list-unstyled">
                <li>
                    <i class="fa fa-check">
                       Đảm bảo hàng chính hãng chất lượng 100%
                    </i>
                </li>
                <li>
                    <i class="fa fa-check">
                       Sản phẩm mới nhất
                    </i>
                </li>
                <li>
                    <i class="fa fa-check">
                        Phục vụ đến khi bạn hài lòng
                    </i>
                </li>
            </ul>
        </div>
    </div>
    <div class="recently">
        <p>Sản phẩm đã xem gần đây</p>
        <div class="row">
            <c:forEach items="${listProduct}" var="sp">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/chiTietSanPham/indexcus/${sp.chiTietSanPhamId}">
                            <img src="/assets/img/sanPham/${sp.hinhAnh}">
                            <div class="caption">
                                <p>${sp.tenSanPham}</p>
                                <span class="gia">$${sp.gia}</span>
                                <span class="daBan">${sp.daBan} sold</span>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br/>
    <div class="recently">
        <p>Bạn cũng có thể thích</p>
        <div class="row">
            <c:forEach items="${listProduct}" var="sp">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/chiTietSanPham/indexcus/${sp.chiTietSanPhamId}">
                            <img src="/assets/img/sanPham/${sp.hinhAnh}">
                            <div class="caption">
                                <p>${sp.tenSanPham}</p>
                                <span class="gia">$${sp.gia}</span>
                                <span class="daBan">${sp.daBan} Đã bán</span>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br/>
</div>

<script>
    <%@include file="logic.js" %>
</script>