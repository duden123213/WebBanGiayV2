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
                <span class="account-name">${cus.fullname} Account </span>
                <a href="/customer/logout" class="link-text-logout">(SignOut)</a>
            </div>
            <div class="link">
                <a href="/customer/indexcus" class="link-text-active">Account Info</a>
            </div>
            <div class="link">
                <a href="/customer/indexcus/changeAccountInfo" class="link-text">Change Account Info</a>
            </div>
        </div>
        <div class="col-9">
            <div class="information">
                <p>Account Info</p>
                <form>
                    <div class="mb-3">
                        <label class="form-label">FullName: </label>
                        <label class="form-label text-inf">${cus.fullname}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Username: </label>
                        <label class="form-label text-inf">${cus.username}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Password: </label>
                        <input type="password" class="form-label text-inf password" value="${cus.password}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">PhoneNumber: </label>
                        <label class="form-label text-inf">${cus.phone}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email: </label>
                        <label class="form-label text-inf">${cus.email}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Birthday: </label>
                        <label class="form-label text-inf">${cus.dateofbirth}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Gender: </label>
                        <label class="form-label text-inf">${cus.gender==1?"Male":"Female"}</label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Address: </label>
                        <label class="form-label text-inf">${cus.address}</label>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br />
</div>