<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="/assets/img/favicons.png" rel="icon">
    <link rel="stylesheet" href="/assets/css/styleFE.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <title>NEWSEVEN - Shop bán giày nam</title>
</head>

<body>
<div id="freeShip">
    <span>Miễn phí vẫn chuyện khi mua hàng </span>
    <a href="#">Chi tiết</a>
</div>
<div class="container">
    <ul class="nav justify-content-end">
        <li class="nav-item">
            <a class="nav-link" href="#">Trợ giúp</a>
        </li>
        <c:choose>
            <c:when test="${CustomerName == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/khachHang/signup">Đăng ký</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/khachHang/login">Đăng nhập</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="/khachHang/logout">Đăng xuất</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
    <nav class="nav-search my-2">
        <ul class="navbar mx-auto mb-lg-0 list-unstyled">
            <li class="nav-item">
                <a href="/home"><img src="/assets/img/logo.png" width="200" alt="logo"/>
                </a>
            </li>
            <li class="nav-item">
                <form action="/sanPham/search" method="get" class="input-group" id="nav-search" style="width: 600px;">
                    <input type="text" name="tenSanPham" class="form-control" placeholder="Bạn cần tìm gì?"
                           aria-label="Recipient's userName"/>
                    <button type="submit" class="btn">Tìm kiếm</button>
                </form>
            </li>
            <li class="nav-item">
                <ul class="list-unstyled" style="display: flex; margin-right: 1rem;">
                    <c:if test="${CustomerName != null}">
                        <li>
                            <a href="/khachHang/indexcus">
                                <span class="fa fa-user"></span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa fa-heart"></span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa fa-sticky-note"></span>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a href="#">
                            <span class="fa fa-shopping-cart"></span>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</div>
<div class="nav-shadow">
    <ul class="nav nav-underline nav-justified container">
        <li class="nav-item">
            <a class="nav-link" href="/home">Trang chủ</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/sanPham/indexcus">Sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/thuongHieu/indexcus">Thương hiệu</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/blog">Blogs</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/aboutus">Về chúng tôi</a>
        </li>
    </ul>
</div>
<div id="nav-bottom"></div>

<main id="main" class="main">
    <jsp:include page="./${view}"/>
</main><!-- End #main -->

<footer class="bd-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-12">
                <div class="row">
                    <div class="col-md-12 col-12">
                        <img src="/assets/img/logo.png" class="logo" alt="logo"/>
                        <p id="text_logo">Chúng tôi có mọi thứ bạn cần</p>
                    </div>
                    <div class="col-md-12 col-12">
                        <p id="text_subscribe">Theo dõi bản tin của chúng tôi</p>
                        <p id="text_blog">Blog mới về giày mỗi tuần!</p>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Địa Chỉ Email Của Bạn"
                                   aria-label="Recipient's username" aria-describedby="button-addon2"/>
                            <button class="btn" type="button" id="button-addon2">
                                ĐẶT MUA
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-1 col-12"></div>
            <div class="col-md-7 col-12">
                <div class="row">
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Where's my order?</p>
                        <button class="btn" id="track_order" type="button">
                            Theo dõi đơn hàng
                        </button>
                        <p class="text_footer">
                            Xin lưu ý, việc xử lý đơn hàng có thể mất nhiều thời gian hơn bình thường do đường tắc.
                        </p>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Vận chuyển</p>
                        <p class="text_footer">Vận chuyển miễn phí</p>
                        <p class="text_footer">Thông tin vận chuyển</p>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Ứng dụng mua sắm</p>
                        <p class="text_footer">
                            Hãy thử tính năng "Xem trong Phòng của Bạn", quản lý các danh sách đăng ký và lưu thông tin thanh toán của bạn.
                        </p>
                        <img src="/assets/img/logo_appstore.png" id="logo_appstore"/>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Công ty của chúng tôi</p>
                        <ul class="list-unstyled">
                            <li class="p-1">
                                <a href="#" class="link_footer">Về chúng tôi</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Sự nghiệp</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Liên hệ</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Địa điểm của hàng</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Mạng xã hội</p>
                        <ul class="list-unstyled" style="display: flex;">
                            <li>
                                <a href="#" class="nav-search">
                                    <span class="fa fa-facebook-square"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-search">
                                    <span class="fa fa-instagram"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-search">
                                    <span class="fa fa-youtube-play"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-search">
                                    <span class="fa fa-twitter"></span>
                                </a>
                            </li>
                        </ul>
                        <span class="text_footer">Hiển thị về chúng tôi</span>
                        <span class="text_footer">Shop NEWSEVEN</span>
                        <br/>
                        <span class="text_footer">NEWSEVEN</span>
                    </div>
                    <div class="col-md-4 col-6">
                        <p class="text_content_footer">Chính sách</p>
                        <ul class="list-unstyled">
                            <li class="p-1">
                                <a href="#" class="link_footer">Chính sách vận chuyển</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Chính sách hoàn trả</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Chính sách bảo mật</a>
                            </li>
                            <li class="p-1">
                                <a href="#" class="link_footer">Điều khoản dịch vụ</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <hr/>
        <div id="Copyright">
            Copyright © 2023 The NEWSEVEN Shop. All rights reserved
        </div>
    </div>
</footer>

<!-- Vendor JS Files -->
<script src="/assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/vendor/chart.js/chart.umd.js"></script>
<script src="/assets/vendor/echarts/echarts.min.js"></script>
<script src="/assets/vendor/quill/quill.min.js"></script>
<script src="/assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="/assets/vendor/tinymce/tinymce.min.js"></script>
<script src="/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="/assets/js/main.js"></script>

</body>

</html>