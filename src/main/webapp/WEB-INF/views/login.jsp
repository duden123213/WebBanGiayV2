<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fo" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style><%@include file="giaodien/login/style.css"%></style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <title>Login</title>
    <style>
        #modal-container{
            height: 100vh;
            background: rgba(0, 0,0,0.5);
            position: fixed;
            top: 0px;
            left: 0px;
            width: 100%;
            opacity: 0;
            pointer-events: none;
        }
        #modal-container.show{
            opacity: 1;
            pointer-events: all;
        }
        #modal-container.show #modal{
            margin-top: 5px;
        }
        #modal{
            background: #fff;
            max-width: 500px;
            position: relative;
            left: 50%;
            transform: translateX(-50%);
            margin-top: 0px;
            transition: all 0.3s ease-in-out;

        }
        #modal .modal-header{
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 20px;
            border-bottom: 1px solid #dedede;
        }
        #modal .modal-header h3{
            margin: 0;
        }
        button#btn-close{
            outline: none;
            border: none;
            background: none;
            font-size: 19px;
        }
        #modal .modal-body{
            padding: 10px 20px 15px;
        }
        #btn-open{
            background: none;
            border: none;
            padding-top:  15px;
        }
    </style>
</head>

<body>
<div class="container">
    <nav class="nav-search">
        <div class="row">
            <div class="col-md-2 col-4">
                <a href="/home/index.html"><img src="/assets/img/logo.png" class="logo" alt="logo" />
                </a>
            </div>
            <div class="col-md-10 col-8">
                <a href="#" class="link-text" id="help">Bạn cần giúp gì?</a>
            </div>
        </div>
    </nav>
</div>
<div id="nav-bottom"></div>
<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-6"></div>
            <div class="col-5">
                <div class="form">
                    <h3>Log in</h3>
                    <div class="text-log">
                        <a id="btn-open" class="link-text">Sign up</a>
                    </div>
                    <form action="/loginOK" method="post">
                        <div class="mb-3">
                            <label for="exampleInputUsername" class="form-label">Tên đăng nhập</label>
                            <input class="form-control" id="exampleInputUsername" name="userName" value="${reUsername}" />
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword" class="form-label">Mật khẩu</label>
                            <input type="password" class="form-control" id="exampleInputPassword" value="${rePassword}" name="passWord"/>
                        </div>
                        <c:if test="${erTrong != null}" >
                            <p style="color: red">${erTrong}</p>
                        </c:if>
                        <c:if test="${erCheck != null}" >
                            <p style="color: red">${erCheck}</p>
                        </c:if>
                        <c:if test="${erCheckRole != null}" >
                            <p style="color: red">${erCheckChucVu}</p>
                        </c:if>
                        <c:if test="${sigsUp != null}" >
                            <p style="color: black">${sigsUp}</p>
                        </c:if>
                        <div class="d-grid">
                            <button type="submit" class="btn login">Đăng nhập</button>
                        </div>

                        <a href="/home"><img src="/assets/img/logo.png" class="logo" style="margin-top: 6rem;" alt="logo" /></a>
                    </form>
                </div>
            </div>
            <div class="col-1"></div>
        </div>
    </div>
    <br />
</div>

<div id="modal-container" >
    <div id="modal">
        <div class="modal-header">
            <h3>Đăng ký</h3>
            <button id="btn-close">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6"  style="width: 15px; height: 15px;">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                </svg>

            </button>
        </div>

        <div class="modal-body">
            <form class="form1" action="/customer/addLogin" method="post">
                <div class="form-group">
                    <b><label class="form-label">Họ tên:</label></b>
                    <input type="text" class="form-control"  name="fullname" />

                    <b> <label class="form-label">Ngày sinh</label></b>
                    <input type="date" class="form-control"  name="dateofbirth" />

                    <b> <label class="form-label">Địa Chỉ</label></b>
                    <input type="text" class="form-control"   name="address" />

                    <b><label class="form-label">Số điện thoại</label></b>
                    <input type="number" class="form-control"  name="phone" />

                    <b><label class="form-label">Giới tính</label></b>
                    <br />
                    <select class="form-select" aria-label="Default select example" name="gender">
                        <option value="1">Nam</option>
                        <option value="2">Nữ</option>

                    </select>

                    <b><label class="form-label">Email</label></b>
                    <input type="email" class="form-control"  name="email" />


                    <b><label class="form-label">Username</label></b>
                    <input type="text" class="form-control"  name="username" />

                    <b><label class="form-label">Password</label></b>
                    <input type="password" class="form-control"  name="password" />


                </div>

                <button type="submit" class="btn btn-default">Tạo tài khoản</button>

            </form>

        </div>
    </div>
</div>

<script>
    const btn_open = document.getElementById('btn-open');
    const btn_close = document.getElementById('btn-close');
    const modal_container = document.getElementById('modal-container');
    btn_open.addEventListener('click', ()=>{
        modal_container.classList.add('show')
    });
    btn_close.addEventListener('click', ()=>{
        modal_container.classList.remove('show')

    });
</script>
</body>

</html>