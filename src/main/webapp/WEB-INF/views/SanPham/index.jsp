<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Sản Phẩm</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/statisticsResult">Trang chủ</a></li>
            <li class="breadcrumb-item active">Tổng quan</li>
            <li class="breadcrumb-item active">Sản Phẩm </li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Sản Phẩm <span>| </span></h5>

                    <table class="table table-borderless datatable">
                        <thead>
                        <tr>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Đã bán</th>
                            <th>Lượt thích</th>
                            <th>Ngày tạo</th>
                            <th>Thương hiệu</th>
                            <th>Trạng thái </th>
                            <%--                                    <th>Category</th>--%>
                            <th>Hành động</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listProduct}" var="sp" >
                            <tr>

                                <td>${sp.tenSanPham}</td>
                                <td>${sp.soLuong}</td>
                                <td>${sp.daBan}</td>
                                <td>${sp.luotThich}</td>
                                <td>${sp.ngayTao}</td>
                                <td>${sp.thuongHieu.tenThuongHieu}</td>


                                <td>${sp.trangThai == 0 ? "Hoạt động" : "Tạm dừng"}</td>


                                <td>
                                    <a href="/sanPham/delete/${sp.idSanPham}" class="btn btn-danger" onclick="return confirm('Bạn chắc chắn có muốn xóa??')" style="text-decoration: none;color: white">Delete</a>
                                        <%--                                         <a href="/product/detail/${sp.id}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Detail</a>--%>
                                    <a href="/sanPham/detail/${sp.idSanPham}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Detail</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                    <a href="/sanPham/create" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Thêm sản phẩm</a>

                </div>

            </div>


        </div><!-- End Recent Sales -->

    </div>

    </div><!-- End Left side columns -->



    </div><!-- End Right side columns -->

    </div>
</section>


