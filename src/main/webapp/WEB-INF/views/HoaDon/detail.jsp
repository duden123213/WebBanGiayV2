

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form action="/chiTietHoaDon/addProductToBill" method="post">
                        <nav>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active"><h1>Thông tin hóa đơn</h1></li>
                                <li style="margin-top: 20px" class="breadcrumb-item"><a href="/chiTietHoaDon/index">Home</a></li>
                            </ol>
                        </nav>

                            <div class="row" style="margin-top: 50px;">
                                <div class="col-md-6">
                                    <b><label class="form-label">ID:</label></b>
                                    <input type="text" class="form-control" name="IdChiTietHoaDon" value="${billDetailD.IdChiTietHoaDon}" readonly />

                                    <b><label class="form-label">Tên:</label></b>
                                    <input type="text" class="form-control" value="${billDetailD.soLuong}" name="soLuong" />

                                    <b><label class="form-label">Số điện thoại</label></b>
                                    <input type="number" class="form-control" value="${billDetailD.gia}" name="gia" />



                                </div>
                            </div>



                        <button type="submit" class="btn btn-primary">Edit</button>
                    </form>

                </div>

            </div>
        </div>
    </div>

</section>






