<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style><%@include file="style.css"%></style>
</head>

<div class="container">
    <img src="/assets/img/banner/banner3.jpg" class="banner" alt="" />
    <div class="row">
        <c:forEach items="${uniqueQuantityProducts}" var="sp">
            <div class="col-md-3 col-6">
                <a href="/sanPham/indexcus/brand/${sp.thuongHieu.id}" class="link-recommend">
                    <img src="/assets/img/product/${sp.hinhAnh}" class="img-recommend" alt="" />
                    <div class="overlay">${sp.thuongHieu.tenThuongHieu}</div>
                </a>
            </div>
        </c:forEach>
    </div>
    <div class="frame">
        <p>Sản phẩm mới</p>
        <div class="row">
            <c:forEach items="${productSortByCreatedDate}" var="sp">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/chiTietSanPham/indexcus/${sp.chiTietSanPhamId}">
                            <img src="/assets/img/sanPham/${sp.hinhAnh}">
                            <div class="caption">
                                <p>${sp.tenSanPham}</p>
                                <span class="gia">$${sp.gia}</span>
                                <span class="daBan">${sp.daBan} đã bán</span>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="frame">
        <p>Sản phẩm bán chạy</p>
        <div class="row">
            <c:forEach items="${productSortBySold}" var="sp">
                <div class="col-md-3 col-6">
                    <div class="thumnail">
                        <a href="/chiTietSanPham/indexcus/${sp.chiTietSanPhamId}">
                            <img src="/assets/img/sanPham/${sp.hinhAnh}">
                            <div class="caption">
                                <p>${sp.tenSanPham}</p>
                                <span class="gia">$${sp.gia}</span>
                                <span class="daBan">${sp.daBan} đã bán</span>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<br />
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="Chào mừng bạn"
        chat-title="ChatBot"
        agent-id="c9aa1141-ab88-427a-9131-9140328e202b"
        language-code="vi"
></df-messenger>