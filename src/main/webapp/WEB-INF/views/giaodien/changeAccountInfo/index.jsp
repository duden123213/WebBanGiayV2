<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<br/>
<div class="container">
    <div class="row">
        <div class="col-3">
            <div class="content">
                <span class="account-name">${cus.tenKhachHang} Tài khoản </span>
                <a href="/KhachHang/logout" class="link-text-logout">(Thoát)</a>
            </div>
            <div class="link">
                <a href="/KhachHang/indexcus" class="link-text">Thông tin tài khoản</a>
            </div>
            <div class="link">
                <a href="/KhachHang/indexcus/changeAccountInfo" class="link-text-active">Thay đổi thông tin</a>
            </div>
        </div>
        <div class="col-9">
            <div class="information">
                <p>Thay đổi thông tin tài khoản</p>
                <form method="post" action="/khachHang/updateCus/${cus.idKhachHang}">
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label form-label">Tên</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="tenKhachHang" value="${cus.tenKhachHang}">
                            <c:if test="${errName != null}" >
                                <p style="color: red">${errName}</p>
                            </c:if>

                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label form-label">Username</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="userName" value="${cus.userName}">
                            <c:if test="${errUser != null}" >
                                <p style="color: red">${errUser}</p>
                            </c:if>
                            <c:if test="${errUserTrung != null}" >
                                <p style="color: red">${errUserTrung}</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="passWord" value="${cus.passWord}">
                            <c:if test="${errPass != null}" >
                                <p style="color: red">${errPass}</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label form-label">Số điện thoại</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" name="soDienThoai" value="${cus.soDienThoai}">
                            <c:if test="${errPhone != null}" >
                                <p style="color: red">${errPhone}</p>
                            </c:if>
                            <c:if test="${errPhoneErrr != null}" >
                                <p style="color: red">${errPhoneErrr}</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" value="${cus.email}">
                            <c:if test="${errEmail != null}" >
                                <p style="color: red">${errEmail}</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label form-label">Ngày sinh</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" name="ngaySinh" value="${cus.ngaySinh}">
                            <c:if test="${errDate != null}" >
                                <p style="color: red">${errDate}</p>
                            </c:if>
                            <c:if test="${errDateAfter != null}" >
                                <p style="color: red">${errDateAfter}</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label form-label">Giới tính</label>
                        <div class="col-sm-10">
                            <select class="form-select" aria-label="Default select example" name="gioiTinh">
                                <option value="0" ${ cus.gioiTinh == "0" ? "selected" : "" }>Nam</option>
                                <option value="1" ${ cus.gioiTinh == "1" ? "selected" : "" }>Nữ</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label form-label">Địa chỉ</label>
                        <div class="col-sm-10">
                            <textarea type="text" class="form-control" name="diaChi">${cus.diaChi}</textarea>
                        </div>
                    </div>
                    <button type="submit" class="btn">Lưu</button>
                </form>
            </div>
        </div>
    </div>
    <br/>
</div>