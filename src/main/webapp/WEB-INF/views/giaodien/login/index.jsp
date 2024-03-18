<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style><%@include file="style.css"%></style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <title>Document</title>
</head>

<body>
<div class="container">
    <nav class="nav-search">
        <div class="row">
            <div class="col-md-2 col-4">
                <a href="/home"><img src="/assets/img/logo.png" class="logo" alt="logo" />
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
                        <span>Chào Mừng Bạn Đến Với NewSeven</span>
                        <a href="/khachHang/signup" class="link-text">Tạo tài khoản</a>
                    </div>
                    <form action="/khachHang/loginOK" method="post">
                        <div class="mb-3">
                            <label for="exampleInputUsername" class="form-label">Tên đăng nhập</label>
                            <input class="form-control" id="exampleInputUsername" name="username" />
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword" class="form-label">Mật Khẩu</label>
                            <input type="password" class="form-control" id="exampleInputPassword" name="password"/>
                        </div>
                        <c:if test="${erTrongCustomer != null}" >
                            <p style="color: red">${erTrongCustomer}</p>
                        </c:if>
                        <c:if test="${erCheckCustomer != null}" >
                            <p style="color: red">${erCheckCustomer}</p>
                        </c:if>
                        <c:if test="${sigsUp != null}" >
                            <p style="color: black">${sigsUp}</p>
                        </c:if>
                        <div class="d-grid">
                            <button type="submit" class="btn login">Đăng Nhập</button>
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
</body>

</html>