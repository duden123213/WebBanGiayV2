
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="section dashboard">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <div><h1>Customer</h1></div>
                    <a class="btn btn-primary" href="/khachHang/viewAdd">CREATE</a>
                    <form action="/khachHang/timKiem" method="get">
                        <div class="col-md-6" style="margin-left: 350px;">
                            <div  class="input-group flex-nowrap ">
                                <input type="text" class="form-control" placeholder="Tim kiếm theo So đieno thoại" aria-label="UserName" aria-describedby="addon-wrapping" name="phone1">
                                <button class="btn btn-light">
                                    <i>
                                        <svg style="margin-top: 5px;" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"/></svg>
                                    </i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li>
                                <c:if test="${not empty cusList && cusList.totalPages >0}">
                                <c:forEach var="i" begin="0" end="${cusList.totalPages-1}">
                            <li  class="page-item">
                                <a class="page-link" href="/khachHang/index?pageNo=${i}&pageSize=${cusList.size}" >${i+1}</a>
                            </li>
                            </c:forEach>
                            </c:if>
                            </li>
                        </ul>
                    </nav>
                    <table class="table table-borderless datatable">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Tên</th>
                            <th scope="col">Ngày sinh</th>
                            <th scope="col">Giới tính</th>
                            <th scope="col">Số điện thoại</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cusList.content}" var="cu" varStatus="tt">
                            <tr>
                                <th scope="row">${tt.index+1}</th>
                                <td>${cu.tenKhachHang}</td>
                                <td>${cu.ngaySinh}</td>
                                <td>${cu.gioiTinh==1?"Nam":"Nữ"}</td>
                                <td>${cu.soDienThoai}</td>
                                <td>
                                    <a class="btn btn-danger" href="/khachHang/detail/${cu.idKhachHang}">Chi tiết</a>
                                </td>

                            </tr>
                        </c:forEach>

                        <c:forEach items="${tim}" var="cu" varStatus="tt">
                            <tr>
                                <th scope="row">${tt.index+1}</th>
                                <td>${cu.tenKhachHang}</td>
                                <td>${cu.ngaySinh}</td>
                                <td>${cu.gioiTinh==1?"Male":"Female"}</td>
                                <td>${cu.soDienThoai}</td>
                                <td>
                                    <a class="btn btn-danger" href="/customer/detail/${cu.idKhachHang}">Chi tiết</a>
                                </td>

                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>

                </div>

            </div>
        </div>
    </div>

</section>









