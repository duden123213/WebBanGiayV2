<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
  <style><%@include file="style.css"%></style>
</head>

    <br />
    <div class="container">
      <div class="content">
        <span class="text-content">Wishlist </span>
        <span class="available">(${quantityFavor})</span>
      </div>
      <div class="row">
        <c:forEach items="${listFavor}" var="sp">
          <div class="col-md-2 col-6">
            <div class="thumnail">
              <a href="/product_detail/indexcus/${sp.productDetailId}">
                <img src="/assets/img/product/${sp.image}" alt="" />
                <div class="caption">
                  <p>${sp.product.name}</p>
                  <span class="price">$${sp.price}</span>
                  <span class="sold">${sp.product.sold} sold</span>
                </div>
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