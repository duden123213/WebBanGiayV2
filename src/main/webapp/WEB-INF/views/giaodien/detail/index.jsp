<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<body>
<div class="container">
    <div class="row">
        <!-- Images Section -->
        <div class="col-6 row">
            <!-- Main Product Image -->
            <div class="product-content-big-img col-9">
                <img src="/assets/img/sanPham/${image.tenHinhAnh}" alt="" />
            </div>
            <!-- Small Product Images -->
            <div class="product-content-small-img col-3">
                <c:forEach items="${listImage}" var="sp">
                    <img src="/assets/img/sanPham/${sp.tenHinhAnh}" alt="" />
                </c:forEach>
            </div>
        </div>

        <!-- Product Details Section -->
        <div class="col-6">
            <h5>${productDetail.ten}</h5>
            <p>${productDetail.daBan} <span class="daBan">Đã bán</span></p>
            <p class="giaBan">$${productDetail.giaBan}</p>
            <!-- Color Section -->
            <div class="product-content-product-color">
                <p><span style="font-weight: bold;">Color: </span><span class="product-content-product-color-name">${productDetail.mauSac.tenMauSac}</span></p>
                <ul class="list-inline">
                    <c:forEach items="${listColor}" var="color">
                        <li class="list-inline-item">
                            <a href="/chiTietSanPham/indexcus/${color.chiTietSanPhamId}" class="${color.chiTietSanPhamId == productDetail.id ? 'product-content-product-color-img-active' : 'product-content-product-color-img'}">
                                <img src="/assets/img/color/${color.hinhAnh}" alt="" value="${color.id}" />
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!-- Size Section -->
            <div class="product-content-product-size">
                <p><span style="font-weight: bold;">Kích cỡ: </span></p>
                <ul class="list-inline">
                    <c:forEach items="${listKichCo}" var="kichCo">
                        <li class="list-inline-item">
                            <a href="/chiTietSanPham/indexcus/${kichCo.chiTietSanPhamId}" class="product-content-product-size-item">
                                <span class="product-content-product-size-name">${kichCo.ten}</span>

                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!-- Quantity Section -->
            <div class="row mb-3">
                <div class="col-3 my-2">
                    <span class="text-content">Số lượng</span>
                </div>
                <div class="col-3">
                    <div class="input-group mb-3">
                        <a href="/chiTietSanPham/reduce/${productDetail.id}" class="btn btn-outline">-</a>
                        <input disabled type="text" class="form-control" value="${productDetail.soLuong}" />
                        <a href="/chiTietSanPham/increase/${productDetail.id}" class="btn btn-outline">+</a>

                    </div>
                </div>
                <div class="col-6 my-2">
                    <span class="text-content">${productDetail.soLuongTon} Sản phẩm có sẵn</span>
                </div>
            </div>
            <!-- Alert Message Section -->
            <div th:if="${message}" class="alert alert-warning" role="alert">
                <span th:text="${message}"></span>
            </div>
            <!-- Buttons Section -->
            <div class="d-grid gap-2">
                <div class="row">
                    <div class="col-10">
                        <a class="btn btn-outline" href="/gioHang/add/${productDetail.id}">Thêm vào giỏ hàng</a>
                    </div>
                    <c:if test="${CustomerName != null}">
                        <form class="col-2" action="/dsyt/like/${productDetail.id}" method="post">
                            <button type="submit" class="fa fa-heart-o"></button>
                        </form>
                    </c:if>
                </div>
                <button class="btn" href="/gioHang/add/${productDetail.id}"type="button">Mua ngay</button>
            </div>
            <!-- Commitment Section -->
            <br>
            <strong>Cam kết của NewSeven</strong>
            <ul class="list-unstyled">
                <li><i class="fa fa-check">Đảm bảo hàng chính hãng chất lượng 100%</i></li>
                <li><i class="fa fa-check">Sản phẩm mới nhất</i></li>
                <li><i class="fa fa-check">Phục vụ đến khi bạn hài lòng</i></li>
            </ul>
        </div>
    </div>

    <!-- Recently Viewed Products Section -->
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

    <!-- Recommended Products Section -->
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
</div>

<script>
    <%@include file="logic.js" %>
</script>
</body>
</html>
