<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagetitle">
    <h1>Product</h1>
    <nav>
        <ol class="breadcrumb">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
            <li class="breadcrumb-item active">Tổng quát</li>
            <li class="breadcrumb-item active">Chi tiết sản phẩm</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section className="section dashboard">
    <div className="row">
        <div className="col-lg-10">
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title">ADD<span></span></h5>
                    <form method="post" action="/chiTietSanPham/add" enctype="multipart/form-data">
                        <div class="form-group">
                            Màu
                            <select name="mauSac" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listColor}" var="color">
                                    <option value="${color.idMauSac}">${color.tenMauSac}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            Sản Phẩm
                            <select name="sanPham" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listProduct}" var="product">
                                    <option value="${product.idSanPham}">${product.tenSanPham}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                             Giá nhập
                            <input class="form-control" name="giaNhap" >
                        </div>
                        <div>
                            Giá bán
                            <input class="form-control" name="price" >
                        </div>
                        <div>
                            Số lượng
                            <input class="form-control" name="soLuong" >
                        </div>
                        <div>
                            Mô tả
                            <input class="form-control" name="Mô tả" >
                        </div>

                        <div class="form-group">
                            Hình ảnh :
                                <input type="file" name="files" multiple="multiple" />

                        </div>
                        <input type="submit" class="btn btn-primary" value="Add" style="margin-top: 10px">
                    </form>



                </div>

            </div>
        </div>


    </div>
    </div>
    </div>
</section>



