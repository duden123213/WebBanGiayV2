<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Product</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">Product</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section class="section dashboard">
    <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-8">
            <div class="row">
                <!-- Recent Sales -->
                <div class="col-12">
                    <div class="card recent-sales overflow-auto">


                        <div class="card-body">
                            <h5 class="card-title">Product <span>| </span></h5>

                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>
                                    <th>Ten</th>
                                    <th>Trang thai</th>
                                    <th>SPCT</th>
                                    <th>Action</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listProductImage}" var="imageSP">
                                    <tr>

                                        <td><img src="/assets/img/sanPham/${imageSP.tenHinhAnh}" height="100px" width="100px"></td>
                                        <td>${imageSP.trangThai == 0 ? "Còn hàng" : "Hết hàng"}</td>
                                        <td>${imageSP.chiTietSanPham.sanPham.tenSanPham}</td>
                                            <%--                                        <td>${sp.brand.name}</td>--%>

                                        <td>
                                            <a href="/hinhAnh/delete/${imageSP.idHinhAnh}" class="btn btn-danger" onclick="return confirm('Bạn chắc chắn có muốn xóa??')" style="text-decoration: none;color: white">Delete</a>
                                            <a href="/hinhAnh/detail/${imageSP.idHinhAnh}" class="btn btn-success" style="text-decoration: none;color: white; margin-top: 5px" >Detail</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>
                        </div>

                    </div>


                </div><!-- End Recent Sales -->

            </div>

        </div><!-- End Left side columns -->

        <!-- Right side columns -->
        <div class="col-lg-4">

            <!-- Recent Activity -->
            <div class="card">

                <div class="card-body">
                    <h5 class="card-title">Sửa <span>| xx</span></h5>
                    <!-- Default Tabs -->
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="home-tab" data-bs-toggle="tab"
                                    data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                    aria-selected="true">Edit
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="profile-tab" data-bs-toggle="tab"
                                    data-bs-target="#profile" type="button" role="tab" aria-controls="profile"
                                    aria-selected="false">Add new
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="contact-tab" data-bs-toggle="tab"
                                    data-bs-target="#contact" type="button" role="tab" aria-controls="contact"
                                    aria-selected="false">Detail
                            </button>
                        </li>
                    </ul>

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                            <form method="post" action="/hinhAnh/update/${imageSP.idHinhAnh}" enctype="multipart/form-data">
                                <div class="form-group">
                                    SPCT :
                                    <select name="chiTietSanPham" class="form-select"  aria-label="Default select example">
                                        <c:forEach items="${listProductDetail}" var="productDetail">
                                            <option value="${productDetail.idChiTietSanPham}">${productDetail.sanPham.tenSanPham}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    Ten :
                                    <label><img src="/assets/img/sanPham/${imageSP.tenHinhAnh}" height="100px" width="100px"></label>
                                    <input class="form-control" type="file" name="files"  multiple value="${imageSP.tenHinhAnh}">
                                </div>
                                <div>
                                    Trang thai :<br>
                                    <input  type="radio" name="trangThai" value="0" ${ imageSP.trangThai == "0" ? "checked" : "" }> Còn Hàng <br>
                                    <input   type="radio" name="trangThai" value="1" ${ imageSP.trangThai == "1" ? "checked" : "" }> Hết hàng
                                </div>
                                <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                            </form>
                        </div>

                        <%--create--%>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form method="post" action="/hinhAnh/add"  enctype="multipart/form-data">
                                <div class="form-group">
                                    SPCT :
                                    <select name="chiTietSanPham" class="form-select"  aria-label="Default select example">
                                        <c:forEach items="${listProductDetail}" var="productDetail">
                                            <option value="${productDetail.idChiTietSanPham}">${productDetail.sanPham.tenSanPham}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    Ten :

                                    <input class="form-control" type="file" name="files"  multiple>
                                </div>
                                <div>
                                    Trang thai :<br>
                                    <input  type="radio" name="trangThai" value="0"> Còn Hàng <br>
                                    <input   type="radio" name="trangThai" value="1"> Hết hàng
                                </div>
                                <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">
                            </form>
                        </div>
                        <%--detail--%>
                        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">

                        </div>
                    </div><!-- End Default Tabs -->


                </div>

            </div>
        </div><!-- End Recent Activity -->


    </div><!-- End Right side columns -->

    </div>
</section>



