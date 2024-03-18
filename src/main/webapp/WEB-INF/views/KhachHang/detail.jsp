

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form action="/khachHang/update" method="post">
                        <nav>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active"><h1>Thông tin khách hàng</h1></li>
                                <li style="margin-top: 20px" class="breadcrumb-item"><a href="/khachHang/index">Home</a></li>
                            </ol>
                        </nav>

                            <div class="row" style="margin-top: 50px;">
                                <div class="col-md-6">
                                    <b><label class="form-label">ID:</label></b>
                                    <input type="text" class="form-control" name="idKhachHang" value="${cus.idKhachHang}" readonly />

                                    <b><label class="form-label">Tên:</label></b>
                                    <input type="text" class="form-control" value="${cus.tenKhachHang}" name="tenKhachHang" />
                                    <c:if test="${errName != null}" >
                                        <p style="color: red">${errName}</p>
                                    </c:if>

                                    <b> <label class="form-label">Ngày sinh</label></b>
                                    <input type="date" class="form-control" value="${cus.ngaySinh}" name="ngaySinh" />
                                    <c:if test="${errDate != null}" >
                                        <p style="color: red">${errDate}</p>
                                    </c:if>
                                    <c:if test="${errDateAfter != null}" >
                                        <p style="color: red">${errDateAfter}</p>
                                    </c:if>

                                    <b> <label class="form-label">Địa chỉ</label></b>
                                    <input type="text" class="form-control"  value="${cus.diaChi}" name="diaChi" />
                                    <c:if test="${errAdd != null}" >
                                        <p style="color: red">${errAdd}</p>
                                    </c:if>

                                    <b><label class="form-label">Số điện thoại</label></b>
                                    <input type="number" class="form-control" value="${cus.soDienThoai}" name="soDienThoai" />
                                    <c:if test="${errPhone != null}" >
                                        <p style="color: red">${errPhone}</p>
                                    </c:if>
                                    <c:if test="${errPhoneErrr != null}" >
                                        <p style="color: red">${errPhoneErrr}</p>
                                    </c:if>

                                    <b><label class="form-label">Ngày tạo: </label></b>
                                    <label  name="datecreated">${cus.ngayTao}</label><br>
                                </div>

                                <div class="col-md-6">
                                    <b><label class="form-label">Giới tính: </label></b>
                                    <label class="form-label">Nam</label>
                                    <input class="form-check-input" type="radio" checked value="1" name="gioiTinh" >

                                    <label class="form-label">Nữ</label>
                                    <input class="form-check-input" type="radio" value="2" name="gioiTinh" ><br>

                                    <b><label class="form-label">Email</label></b>
                                    <input type="email" class="form-control" value="${cus.email}" name="email" />
                                    <c:if test="${errEmail != null}" >
                                        <p style="color: red">${errEmail}</p>
                                    </c:if>


                                    <b><label class="form-label">Username</label></b>
                                    <input type="text" class="form-control" value="${cus.userName}" name="userName" />
                                    <c:if test="${errUser != null}" >
                                        <p style="color: red">${errUser}</p>
                                    </c:if>
                                    <c:if test="${errUserTrung != null}" >
                                        <p style="color: red">${errUserTrung}</p>
                                    </c:if>

                                    <b><label class="form-label">Password</label></b>
                                    <input type="password" class="form-control" value="${cus.passWord}" name="passWord" />
                                    <c:if test="${errPass != null}" >
                                        <p style="color: red">${errPass}</p>
                                    </c:if>


                                    <b><label class="form-label">Trạng thái</label></b>
                                    <select class="form-select" aria-label="Default select example" name="trangThai">
                                        <option value="${cus.trangThai}">${cus.trangThai==1?"ON":"OFF"} </option>
                                        <option value="1">ON</option>
                                        <option value="2">OFF</option>

                                    </select>
                                </div>
                            </div>



                        <button type="submit" class="btn btn-primary">Edit</button>
                    </form>

                </div>

            </div>
        </div>
    </div>

</section>






