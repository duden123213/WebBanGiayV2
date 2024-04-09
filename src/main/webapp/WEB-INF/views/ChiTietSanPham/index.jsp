<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Product</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Trang Chủ</a></li>
            <li class="breadcrumb-item active">Tổng quan</li>
            <li class="breadcrumb-item active">Chi tiết sản phẩm</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section className="section dashboard">
    <div className="row">


        <div className="col-lg-10">


            <div className="card">



                <div class="card-body">
                    <h5 class="card-title">Chi tiết sản phẩm <span>| </span></h5>

                    <table class="table table-borderless datatable">
                        <thead>
                        <tr>
                            <%--                                    <th>ImportPrice</th>--%>
                            <th>Giá</th>
                            <th>Số lượng </th>
                            <th>Ngày tạo</th>
                            <th>Trạng thái</th>
                            <th>Sản phẩm</th>
                            <th>Kích cỡ</th>
                            <th>Màu sắc</th>
                            <th>Hình ảnh</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listProductDetail}" var="detailSP">
                            <tr>

                                <td>${detailSP.giaBan}</td>
                                <td>${detailSP.soLuong}</td>
                                <td>${detailSP.ngayTao}</td>
                                <td>${detailSP.trangThai == 0 ? "Còn hàng" : "Hết hàng"}</td>
                                <td>${detailSP.sanPham.tenSanPham}</td>
                                <td>${detailSP.kichCo.tenKichCo}</td>

                                <td>
                                    <img src="/assets/img/color/${detailSP.mauSac.hinhAnh}" height="100px" width="100px"></td>
                                <td>
                                    <c:forEach items="${detailSP.lisHinhAnh}" var="image" varStatus="loop">
                                        <c:if test="${loop.index == 0}">
                                            <img src="/assets/img/sanPham/${image.tenHinhAnh}" height="100px" width="100px">
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <a href="/chiTietSanPham/delete/${detailSP.idChiTietSanPham}" class="btn btn-danger" onclick="return confirm('Bạn chắc chắn có muốn xóa??')" style="text-decoration: none;color: white">Delete</a>
                                    <a href="/chiTietSanPham/detail/${detailSP.idChiTietSanPham}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Detail</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                    <a href="/chiTietSanPham/create" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Thêm sản phẩm</a>
                </div>

            </div>


        </div><!-- End Recent Sales -->

    </div>

    </div><!-- End Left side columns -->



    </div>
</section>






