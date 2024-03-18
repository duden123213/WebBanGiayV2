<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Color</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/statisticsResult">Trang chủ</a></li>
            <li class="breadcrumb-item active">Tổng quát</li>
            <li class="breadcrumb-item active">Thương hiệu</li>
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
                            <h5 class="card-title">Thương hiệu <span>| </span></h5>

                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>
                                    <th>Tên thương hiệu</th>
                                    <th>Hình ảnh</th>
                                    <th>Trang thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listBrand}" var="bra">
                                    <tr>

                                        <td>${bra.tenThuongHieu}</td>
                                        <td><img src="/assets/img/brand/${bra.hinhAnh}" width="100px" height="auto"></td>
                                        <td>${bra.trangThai == 0 ? "Còn Hàng" : "Hết Hàng"}</td>
                                        <td>
                                            <a href="/thuongHieu/delete/${bra.id}" class="btn btn-danger"
                                               onclick="return confirm('Bạn chắc chắn có muốn xóa??')">Delete</a>
                                            <a href="/thuongHieu/detail/${bra.id}" class="btn btn-success">Detail</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <%--                            <form method="post" enctype="multipart/form-data" action="import">--%>
                            <%--                                Thêm từ file excel: <input class="form-control" name="file" type="file">--%>
                            <%--                                <button>Thêm</button>--%>
                            <%--                            </form>--%>
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
                            <form method="post" action="/thuongHieu/update/${bra.id}">

                                <div>
                                    Name :
                                    <input class="form-control" name="tenThuongHieu" value="${bra.tenThuongHieu}">
                                </div>
                                <div>
                                    Image :
                                    <img src="/assets/img/brand/${bra.hinhAnh}" height="100px" width="100px">
                                    <input class="form-control" name="hinhAnh" type="file" value="${bra.hinhAnh}">
                                </div>
                                <div>
                                    Status :
                                    <input type="radio" name="trangThai" value="1" ${ bra.trangThai == "1" ? "checked" : "" }>
                                    Còn Hàng <br>
                                    <input type="radio" name="trangThai" value="0" ${ bra.trangThai == "0" ? "checked" : "" }>
                                    Hết hàng
                                </div>
                                <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                            </form>
                        </div>

                        <%--create--%>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form method="post" action="/thuongHieu/add">
                                <div>
                                    Name :
                                    <input class="form-control" name="tenThuongHieu">
                                </div>
                                <div>
                                    Image :

                                    <input class="form-control" name="hinhAnh" type="file">
                                </div>
                                <div>
                                    Status :
                                    <input type="radio" name="trangThai" value="0"> Còn Hàng <br>
                                    <input type="radio" name="trangThai" value="1"> Hết hàng
                                </div>
                                <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">
                            </form>
                        </div>

                    </div><!-- End Default Tabs -->


                </div>

            </div>
        </div><!-- End Recent Activity -->


    </div><!-- End Right side columns -->

    </div>
</section>



