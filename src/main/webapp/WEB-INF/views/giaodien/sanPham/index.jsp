<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<br/>
<div class="container">
    <div class="content">
        <span class="text-content">Lips </span>
        <span class="available">(${quantityProduct})</span>
        <%--<div class="btn-group" role="group">
            <button type="button" class="btn btn-outline dropdown-toggle" data-bs-toggle="dropdown"
                    aria-expanded="false">
                Best selling
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Lip Dior</a></li>
                <li><a class="dropdown-item" href="#">Lip Channel</a></li>
            </ul>
        </div>--%>
    </div>
    <div class="row">
        <div class="col-3">
            <h6 class="text-filter">Filter</h6>
            <h6 class="filter">Thương hiệu</h6>
            <c:forEach items="${listBrand}" var="brand">
                <div class="filter-brand">
                    <a href="/sanPham/indexcus/thuongHieu/${brand.id}">${brand.tenThuongHieu}</a>
                </div>
            </c:forEach>
            <h6 class="filter">Giá</h6>
            <div class="filter-brand">
                <a href="/sanPham/indexcus/price/0/500">Dưới 500.000vnđ</a>
            </div>
            <div class="filter-brand">
                <a href="/sanPham/indexcus/price/500/1000">500.000vnđ - 1000.000vnđ</a>
            </div>
            <div class="filter-brand">
                <a href="/sanPham/indexcus/price/1000/2000">1000.000vnđ - 2000.000vnđ</a>
            </div>
            <div class="filter-brand">
                <a href="/sanPham/indexcus/price/2000/100000">Trên 2000.000vnđ</a>
            </div>
            <h6 class="filter">Kích cỡ</h6>
                        <div class="filter-brand">
                            <a href="/sanPham/indexcus/price/0/500">Dưới 30</a>
                        </div>
                        <div class="filter-brand">
                            <a href="/sanPham/indexcus/price/500/1000">30-35  </a>
                        </div>
                        <div class="filter-brand">
                            <a href="/sanPham/indexcus/price/1000/2000">35-40</a>
                        </div>
                        <div class="filter-brand">
                            <a href="/sanPham/indexcus/price/2000/100000">Trên 40</a>
                        </div>
            <%--<h6 class="filter">COLOR</h6>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value=""/>
                <label class="form-check-label">
                    Strong - rich chocolate
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value=""/>
                <label class="form-check-label">
                    Gifted - deep dusty plum
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value=""/>
                <label class="form-check-label">
                    Fun - neutral mauve
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value=""/>
                <label class="form-check-label">
                    Creative - muted peach
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value=""/>
                <label class="form-check-label">
                    Humble - rose mauve
                </label>
            </div>--%>
        </div>
        <div class="col-9">
            <div class="row">
                <c:forEach items="${listProduct}" var="sp">
                    <div class="col-md-3 col-6">
                        <div class="thumnail">
                            <a href="/chiTietSanPham/indexcus/${sp.chiTietSanPhamId}">
                                <img src="/assets/img/sanPham/${sp.hinhAnh}">
                                <div class="caption">
                                    <p>${sp.tenSanPham}</p>
                                    <span class="price">$${sp.gia}</span>
                                    <span class="sold">${sp.daBan} đã bán</span>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <ul class="pagination">
                <c:if test="${not entitiesPage.first}">
                    <li class="pagea-item">
                        <a class="pagea-link" href="?page=${entitiesPage.number - 1}&size=${entitiesPage.size}">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:choose>
                    <c:when test="${entitiesPage.totalPages > 5}">
                        <c:choose>
                            <c:when test="${entitiesPage.number < 4}">
                                <c:forEach var="pageNumber" begin="1" end="${entitiesPage.number + 3}">
                                    <c:choose>
                                        <c:when test="${(pageNumber - 1) == entitiesPage.number}">
                                            <li class="pagea-item-active">
                                                <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="pagea-item">
                                                <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <li class="pagea-item"><span class="pagea-text">...</span></li>
                                <li class="pagea-item">
                                    <a class="pagea-link" href="?page=${entitiesPage.totalPages - 1}&size=${entitiesPage.size}">${entitiesPage.totalPages}</a>
                                </li>
                            </c:when>
                            <c:when test="${(entitiesPage.totalPages - entitiesPage.number) < 5}">
                                <li class="pagea-item">
                                    <a class="pagea-link" href="?page=1&size=${entitiesPage.size}">1</a>
                                </li>
                                <li class="pagea-item"><span class="pagea-text">...</span></li>
                                <c:forEach var="pageNumber" begin="${entitiesPage.number - 1}" end="${entitiesPage.totalPages}">
                                    <c:choose>
                                        <c:when test="${(pageNumber - 1) == entitiesPage.number}">
                                            <li class="pagea-item-active">
                                                <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="pagea-item">
                                                <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li class="pagea-item">
                                    <a class="pagea-link" href="?page=1&size=${entitiesPage.size}">1</a>
                                </li>
                                <li class="pagea-item"><span class="pagea-text">...</span></li>
                                <c:forEach var="pageNumber" begin="${entitiesPage.number - 1}" end="${entitiesPage.number + 3}">
                                    <c:choose>
                                        <c:when test="${(pageNumber - 1) == entitiesPage.number}">
                                            <li class="pagea-item-active">
                                                <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="pagea-item">
                                                <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <li class="pagea-item"><span class="pagea-text">...</span></li>
                                <li class="pagea-item">
                                    <a class="pagea-link" href="?page=${entitiesPage.totalPages - 1}&size=${entitiesPage.size}">${entitiesPage.totalPages}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:when test="${entitiesPage.totalPages > 3}">
                        <c:forEach var="pageNumber" begin="1" end="${entitiesPage.totalPages}">
                            <c:choose>
                                <c:when test="${(pageNumber - 1) == entitiesPage.number}">
                                    <li class="pagea-item-active">
                                        <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="pagea-item">
                                        <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="pageNumber" begin="1" end="${entitiesPage.totalPages}">
                            <c:choose>
                                <c:when test="${(pageNumber - 1) == entitiesPage.number}">
                                    <li class="pagea-item-active">
                                        <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="pagea-item">
                                        <a class="pagea-link" href="?page=${pageNumber - 1}&size=${entitiesPage.size}">${pageNumber}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <c:if test="${not entitiesPage.last}">
                    <li class="pagea-item">
                        <a class="pagea-link" href="?page=${entitiesPage.number + 1}&size=${entitiesPage.size}">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<br>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="ChatBot"
        agent-id="c9aa1141-ab88-427a-9131-9140328e202b"
        language-code="vi"
></df-messenger>