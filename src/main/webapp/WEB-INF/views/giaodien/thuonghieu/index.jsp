<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style><%@include file="style.css"%></style>
</head>

<br />
<div class="container">
    <div class="content">
        <span class="text-content">Thương hiệu </span>
        <span class="available">(${quantityBrand})</span>
    </div>
    <div class="row">
        <c:forEach items="${listBrand}" var="sp">
            <div class="col-md-2 col-4">
                <div class="thumnail">
                    <a href="/sanPham/indexcus/thuongHieu/${sp.id}">
                        <img src="/assets/img/brand/${sp.hinhAnh}" alt="" class="imageBrand" />
                        <div class="nameBrand" title="${sp.tenThuongHieu}">${sp.tenThuongHieu}</div>
                    </a>
                </div>
            </div>
        </c:forEach>

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
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="ChatBot"
        agent-id="c9aa1141-ab88-427a-9131-9140328e202b"
        language-code="vi"
></df-messenger>