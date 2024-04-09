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
            <li class="breadcrumb-item active">Chi tiết sản phảm</li>
        </ol>
        <style>
            .image-container {
                display: flex;
                flex-wrap: wrap;
            }

            .image-item {
                margin-right: 10px;
                margin-bottom: 10px;
            }

        </style>
    </nav>
</div>
<!-- End Page Title -->

<section className="section dashboard">
    <div className="row">


        <div className="col-lg-10">


            <div className="card">

                <div className="card-body">
                    <h5 className="card-title">ADD<span></span></h5>

                    <form method="post" action="/chiTietSanPham/update/${detailSP.idChiTietSanPham}" enctype="multipart/form-data" name="updateForm" onsubmit="return validateForm()">
                        <div class="form-group">
                            Màu :
                            <select name="mauSac" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listColor}" var="color">
                                    <option value="${color.idMauSac}">${color.tenMauSac}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            Kích cỡ :
                            <select name="kichCo" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listKichCo}" var="kichCo">
                                    <option value="${kichCo.idKichCo}">${kichCo.tenKichCo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            Sản Phẩm :
                            <select name="sanPham" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listProduct}" var="product">
                                    <option value="${product.idSanPham}">${product.tenSanPham}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            Gia nhập:
                            <input class="form-control" name="giaNhap" value="${detailSP.giaNhap}">
                        </div>
                        <div>
                            Gia bán :
                            <input class="form-control" name="giaBan" value="${detailSP.giaBan}">
                        </div>
                        <div>
                            Số lượng :
                            <input class="form-control" name="soLuong" value="${detailSP.soLuong}">
                        </div>
                        <div>
                            Mô tả :
                            <input class="form-control" name="moTa" value="${detailSP.moTa}">
                        </div>
                        <div>
                            Trang thai :<br>
                            <input  type="radio" name="trangThai" value="0" ${ detailSP.trangThai == "0" ? "checked" : "" }> Còn Hàng <br>
                            <input   type="radio" name="trangThai" value="1" ${ detailSP.trangThai == "1" ? "checked" : "" }> Hết hàng
                        </div>
                        <div class="form-group">
                            <label>Images:</label>

                            <!-- Display existing images with delete option -->
                            <div class="image-container">
                                <c:forEach items="${detailSP.lisHinhAnh}" var="image">
                                    <div class="image-item">
                                        <img src="<%= request.getContextPath() %>/assets/img/sanPham/${image.tenHinhAnh}" height="100px" width="100px">
                                        <a href="/chiTietSanPham/deleteImage/${image.idHinhAnh}" class="delete-image-link">Xóa</a>
                                    </div>
                                </c:forEach>
                            </div>

                            <!-- Input for adding new images -->
                            <div class="image-container">
                                <input type="file" name="files" multiple="multiple" accept="image/*" />
                            </div>
                        </div>


                        <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                    </form>


                </div>

            </div>
        </div>


    </div>
    </div>
    </div>
    <script>
        function validateForm() {
            var importPrice = document.forms["updateForm"]["importPrice"].value;
            var price = document.forms["updateForm"]["price"].value;
            var quantity = document.forms["updateForm"]["quantity"].value;

            if (importPrice === "" || isNaN(importPrice)) {
                alert("Please enter a valid import price.");
                return false;
            }

            if (price === "" || isNaN(price)) {
                alert("Please enter a valid price.");
                return false;
            }

            if (quantity === "" || isNaN(quantity)) {
                alert("Please enter a valid quantity.");
                return false;
            }

            // Add more validation if needed...

            return true;
        }
    </script>

</section>



