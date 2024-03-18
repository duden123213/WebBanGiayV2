<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form action="/nhanVien/update" method="post">
                        <nav>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active"><h1>Thông tin nhân viên</h1></li>
                                <li style="margin-top: 20px" class="breadcrumb-item"><a href="/nhanVien/index">Trang chủ</a></li>
                            </ol>
                        </nav>
                        <div><h1></h1></div>

                        <div class="row" style="margin-top: 50px;">
                            <div class="col-md-6">
                                <b><label class="form-label">ID:</label></b>
                                <input type="text" class="form-control" name="idNhanVien" value="${emp.idNhanVien}" readonly />

                                <b><label class="form-label">Tên:</label></b>
                                <input type="text" class="form-control" value="${emp.tenNhanVien}" name="tenNhanVien" />
                                <c:if test="${errName != null}" >
                                    <p style="color: red">${errName}</p>
                                </c:if>
                                <b> <label class="form-label">Ngày sinh</label></b>
                                <input type="date" max="${now}" class="form-control" value="${emp.ngaySinh}"  name="ngaySinh" /><br>
                                <c:if test="${errDate != null}" >
                                    <p style="color: red">${errDate}</p>
                                </c:if>
                                <c:if test="${errDateAfter != null}" >
                                    <p style="color: red">${errDateAfter}</p>
                                </c:if>
                                <b><label class="form-label">Giới tính: </label></b>
                                <label class="form-label">Nam</label>
                                <input class="form-check-input" type="radio" ${emp.gioiTinh==1?"checked":""}  value="1" name="gioiTinh" >

                                <label class="form-label">Nữ</label>
                                <input class="form-check-input" type="radio" ${emp.gioiTinh==2?"checked":""} value="2" name="gioiTinh" ><br>


                                <b><label class="form-label">Phone Number</label></b>
                                <input type="number" class="form-control" value="${emp.soDienThoai}"  name="soDienThoai" />
                                <c:if test="${errPhone != null}" >
                                    <p style="color: red">${errPhone}</p>
                                </c:if>
                                <c:if test="${errPhoneErrr != null}" >
                                    <p style="color: red">${errPhoneErrr}</p>
                                </c:if>
                                <b><label class="form-label">Email</label></b>
                                <input type="email" class="form-control" value="${emp.email}"  name="email" />
                                <c:if test="${errEmail != null}" >
                                    <p style="color: red">${errEmail}</p>
                                </c:if>
                            </div>

                            <div class="col-md-6">

                                <b> <label class="form-label">Địa chỉ</label></b>
                                <input type="text" class="form-control" value="${emp.diaChi}"  name="diaChi" />
                                <c:if test="${errAdd != null}" >
                                    <p style="color: red">${errAdd}</p>
                                </c:if>
                                <b><label class="form-label">Chức Vụ</label></b>
                                    <select class="form-select" aria-label="Default select example" name="idChucVu">
                                    <option value="${emp.chucVu.idChucVu}">${emp.chucVu.tenChucVu} </option>
                                    <c:forEach items="${role}" var="r">
                                        <option value="${r.idChucVu}">${r.tenChucVu}</option>
                                    </c:forEach>
                                </select>

                                <b><label class="form-label">Username</label></b>
                                <input type="text" class="form-control" value="${emp.userName}"  name="username1" />
                                <c:if test="${errUser != null}" >
                                    <p style="color: red">${errUser}</p>
                                </c:if>
                                <c:if test="${errUserTrung != null}" >
                                    <p style="color: red">${errUserTrung}</p>
                                </c:if>
                                <b><label class="form-label">Password</label></b>
                                <input type="password" class="form-control" value="${emp.passWord}" name="password1" />
                                <c:if test="${errPass != null}" >
                                    <p style="color: red">${errPass}</p>
                                </c:if>

                                <b><label class="form-label">Ngày tạo: </label></b>
                                <label  name="datecreated">${emp.ngayTao}</label><br>
                                <%--                                        <input type="date" class="form-control" value="${emp.datecreated}" name="datecreated" readonly />--%>

                                <b><label class="form-label">Trạng thái: </label></b>
                                <label class="form-label">ON</label>
                                <input class="form-check-input" type="radio" ${emp.trangThai==1?"checked":""}  value="1" name="trangThai" >

                                <label class="form-label">OFF</label>
                                <input class="form-check-input" type="radio" ${emp.trangThai==2?"checked":""} value="2" name="trangThai" ><br>
                            </div>
                        </div>

                        <c:if test="${erCheckRole != null}" >
                            <p style="color: red">${erCheckRole}</p>
                        </c:if>

                        <button type="submit" class="btn btn-primary">Edit</button>
                    </form>

                </div>

            </div>
        </div>
    </div>

</section>






