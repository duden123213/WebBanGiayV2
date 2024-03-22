<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Phân loại</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/statisticsResult">Home</a></li>
            <li class="breadcrumb-item active">Overview</li>
            <li class="breadcrumb-item active">Phân loại</li>
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
                            <h5 class="card-title">Phân loại <span>| </span></h5>

                            <table class="table table-borderless datatable">
                                <thead>
                                <tr>
                                    <th>Tên phân loại</th>
                                    <th>Trạng thái</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listPhanLoai}" var="phanloai">
                                    <tr>
                                        <td>${phanloai.tenPhanLoai}</td>
                                        <td>${phanloai.trangThai == 0 ? "Còn hàng" : "Hết hàng"}</td>
                                        <td>
                                            <a href="/phanLoai/delete/${phanloai.idPhanLoai}" class="btn btn-danger"
                                               onclick="return confirm('Bạn chắc chắn có muốn xóa??')">Delete</a>
                                            <a href="/phanLoai/detail/${phanloai.idPhanLoai}" class="btn btn-success">Detail</a>
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
                    </ul>

                    <%--update--%>
                    <div class="tab-content pt-2" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel"
                             aria-labelledby="home-tab">
                            <form method="post" action="/phanLoai/update/${phanloai.idPhanLoai}">

                                <div>
                                    Tên phân loại :
                                    <input class="form-control" name="tenPhanLoai" value="${phanloai.tenPhanLoai}">
                                </div>
                                <div>
                                    Trạng thái :
                                    <input type="radio" name="trangThai" value="0" ${ phanloai.trangThai == "0" ? "checked" : "" }>
                                    Còn Hàng <br>
                                    <input type="radio" name="trangThai" value="1" ${ phanloai.trangThai == "1" ? "checked" : "" }>
                                    Hết hàng
                                </div>
                                <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                            </form>
                        </div>

                        <%--create--%>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <form method="post" action="/phanLoai/add">
                                <div>
                                    Tên phân loại :
                                    <input class="form-control" name="tenPhanLoai">
                                </div>
                                <div>
                                    Trạng thái :
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



