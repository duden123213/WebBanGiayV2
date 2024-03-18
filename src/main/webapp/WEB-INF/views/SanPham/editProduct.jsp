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
            <li class="breadcrumb-item active">Sản Phẩm</li>
        </ol>
    </nav>
</div>
<!-- End Page Title -->

<section className="section dashboard">
    <div className="row">


        <div className="col-lg-10">


            <div className="card">

                <div className="card-body">
                    <h5 className="card-title">ADD<span>| xx</span></h5>

                    <form method="post" action="/sanPham/update/${sp.idSanPham}">
                        <div class="form-group">
                            Thương hiệu :
                            <select name="thuongHieu" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listBrand}" var="brand">
                                    <option value="${brand.id}">${brand.tenThuongHieu}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            Phân loại :
                            <select name="phanLoai" class="form-select"  aria-label="Default select example">
                                <c:forEach items="${listCategory}" var="ca">
                                    <option value="${ca.idPhanLoai}">${ca.tenPhanLoai}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            Tên :
                            <input class="form-control" name="tenSanPham" value="${sp.tenSanPham}">
                        </div>
                        <div>
                            Mô tả :
                            <input class="form-control" name="moTa" value="${sp.moTa}">
                        </div>
                        <div>
                            Số lượng :
                            <input class="form-control" name="soLuong" value="${sp.soLuong}">
                        </div>
                        <div>
                            Đã bán :
                            <input class="form-control" name="daBan" value="${sp.daBan}">
                        </div>
                        <div>
                            Lượt thích :
                            <input class="form-control" name="luotThich" value="${sp.luotThich}">
                        </div>

                        <div>
                            Trạng thái :<br>
                            <input  type="radio" name="trangThai" value="0" ${ sp.trangThai == "0" ? "checked" : "" }> Còn Hàng <br>
                            <input   type="radio" name="trangThai" value="1" ${ sp.trangThai == "1" ? "checked" : "" }> Hết hàng
                        </div>
                        <input type="submit" class="btn btn-primary" value="Update" style="margin-top: 10px">
                    </form>


                </div>

            </div>
        </div>


    </div>
    </div>
    </div>
</section>



