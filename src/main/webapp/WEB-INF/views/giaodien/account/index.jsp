<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style><%@include file="style.css"%></style>
</head>

<br />
<div class="container">
    <div class="row">
        <div class="col-3">
            <div class="content">
                <span class="account-name">${cus.tenKhachHang} Account </span>
                <a href="/khachHang/logout" class="link-text-logout">(Thoát)</a>
            </div>
            <div class="link">
                <a href="/khachHang/indexcus" class="link-text-active">Thông tin tài khoản</a>
            </div>
            <div class="link">
                <a href="/khachHang/indexcus/changeAccountInfo" class="link-text">Thay đổi thông tin</a>
            </div>
        </div>
        <div class="col-9">
            <div class="information">
                <p>Account Info</p>
                <form>
                    <div class="mb-3">
                        <label class="form-label">Ho tên: </label>
                        <label class="form-label text-inf">${cus.tenKhachHang}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tên tài khoản: </label>
                        <label class="form-label text-inf">${cus.userName}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mật khẩu: </label>
                        <input type="password" class="form-label text-inf password" value="${cus.passWord}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Số điện thoại: </label>
                        <label class="form-label text-inf">${cus.soDienThoai}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email: </label>
                        <label class="form-label text-inf">${cus.email}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Ngày sinh: </label>
                        <label class="form-label text-inf">${cus.ngaySinh}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Giới tính: </label>
                        <label class="form-label text-inf">${cus.gioiTinh==1?"Nam":"Nữ"}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Địa chỉ: </label>
                        <label class="form-label text-inf">${cus.diaChi}</label>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br />
</div>