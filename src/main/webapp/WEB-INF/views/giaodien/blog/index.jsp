<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <style><%@include file="style.css"%></style>
</head>

<br />
<div class="container">
    <div class="row">
        <div class="col-3">
            <h6 class="filter">Recent Post</h6>
            <ul class="list-unstyled">
                <li>
                    <a href="/blogPage1">Mẫu giày mới nhất năm 2024</a>
                </li>
                <hr>
                <li>
                    <a href="/blogPage2">Mẫu giày mới nhất năm 2024</a>
                </li>
                <hr>
                <li>
                    <a href="/blogPage5">Mua giày chính hãng ở đâu? Sản phẩm nào bán chạy nhất thi trường</a>
                </li>
            </ul>
            <h6 class="filter">Bài viết thích nhiều nhất</h6>
            <ul class="list-unstyled">
                <li>
                    <a href="/blogPage2">Mẫu giày mới nhất năm 2024</a>
                </li>
                <li>
                    <a href="/blogPage3">Mẫu giày mới nhất năm 2024</a>
                </li>
                <li>
                    <a href="/blogPage4">Mẫu giày mới nhất năm 2024</a>
                </li>
            </ul>
        </div>
        <div class="col-9">
            <div class="post">
                <div class="thumnail">
                    <a href="/blogPage1">
                        <img src="<c:url value="/assets/img/posts/post1.jpeg"/>" alt="" />
                        <div class="caption">
                            <span class="title">Mẫu giày mới nhất năm 2024</span>
                            <span class="content">Mẫu giày mới nhất, nhiều người mua nhất, tiện nghi nhất [...]</span>
                        </div>
                    </a>
                </div>
            </div>
            <div class="post">
                <div class="thumnail">
                    <a href="/blogPage2">
                        <img src="<c:url value="/assets/img/posts/post2.jpeg"/>" alt="" />
                        <div class="caption">
                            <span class="title">Mẫu giày mới năm 2024</span>
                            <span class="content">Mẫu giày đẹp nhất, nhiều người mua nhất[...]</span>
                        </div>
                    </a>
                </div>
            </div>
            <div class="post">
                <div class="thumnail">
                    <a href="/blogPage3">
                        <img src="<c:url value="/assets/img/posts/post3.jpeg"/>" alt="1" />
                        <div class="caption">
                            <span class="title">Mẫu giày mới nhất năm 2024</span>
                            <span class="content">Mẫu giày nhiều lượt mua nhất [...]</span>
                        </div>
                    </a>
                </div>
            </div>
            <div class="post">
                <div class="thumnail">
                    <a href="/blogPage4">
                        <img src="<c:url value="/assets/img/posts/post4.jpeg"/>" alt="2" />
                        <div class="caption">
                            <span class="title">Mẫu giày mới nhất năm 2024</span>
                            <span class="content">Mẫu giày có thường hiệu lâu đời nhất [...]</span>
                        </div>
                    </a>
                </div>
            </div>
            <div class="post">
                <div class="thumnail">
                    <a href="/blogPage5">
                        <img src="<c:url value="/assets/img/posts/post5.jpeg"/>" alt="3" />
                        <div class="caption">
                  <span class="title">Mua giày nam chính hãng ở đâu? Mẫu nào bán chạy nhất</span>
                  <span class="content">Mua giày chính hãng tại NewSeven tại Hà Nội [...]</span>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="ChatBot"
        agent-id="c9aa1141-ab88-427a-9131-9140328e202b"
        language-code="vi"
></df-messenger>