<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Trang quản trị</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;

            font-family:
                    "Segoe UI",
                    Arial,
                    sans-serif;

            background:
                    linear-gradient(
                            135deg,
                            #fff5f8,
                            #ffe8f0,
                            #fff9fb
                    );

            min-height: 100vh;

            color: #555;
        }

        /* ==============================
           HEADER
           ============================== */

        .header {
            height: 75px;

            display: flex;

            align-items: center;

            justify-content: space-between;

            padding: 0 45px;

            background: rgba(
                    255,
                    255,
                    255,
                    0.95
            );

            border-bottom:
                    1px solid #f8ccdc;

            box-shadow:
                    0 4px 20px
                    rgba(
                            200,
                            80,
                            120,
                            0.08
                    );
        }

        .logo {

            display: flex;

            align-items: center;

            gap: 12px;

            color: #d85c88;

            font-size: 22px;

            font-weight: 700;
        }

        .logo-icon {

            width: 42px;

            height: 42px;

            display: flex;

            align-items: center;

            justify-content: center;

            border-radius: 12px;

            background:
                    linear-gradient(
                            135deg,
                            #ffafd0,
                            #ed78a5
                    );

            color: white;

            font-weight: bold;

            box-shadow:
                    0 5px 15px
                    rgba(
                            237,
                            120,
                            165,
                            0.3
                    );
        }

        .logout {

            text-decoration: none;

            padding: 10px 18px;

            border-radius: 10px;

            color: #d85c88;

            background: #fff0f5;

            border:
                    1px solid #f7c9d9;

            font-weight: 600;

            transition: 0.25s;
        }

        .logout:hover {

            background: #f58ab1;

            color: white;

            transform:
                    translateY(-2px);

            box-shadow:
                    0 6px 15px
                    rgba(
                            245,
                            138,
                            177,
                            0.25
                    );
        }

        /* ==============================
           MAIN
           ============================== */

        .container {

            max-width: 1200px;

            margin: 0 auto;

            padding: 50px 30px;
        }

        .welcome {

            margin-bottom: 35px;
        }

        .welcome h1 {

            margin: 0 0 8px;

            color: #c94f7c;

            font-size: 32px;
        }

        .welcome p {

            margin: 0;

            color: #888;

            font-size: 16px;
        }

        /* ==============================
           CARDS
           ============================== */

        .grid {

            display: grid;

            grid-template-columns:
                    repeat(
                            3,
                            1fr
                    );

            gap: 22px;
        }

        .card {

            background: white;

            border-radius: 18px;

            padding: 28px;

            text-decoration: none;

            border:
                    1px solid #f7d3df;

            box-shadow:
                    0 8px 25px
                    rgba(
                            180,
                            70,
                            110,
                            0.08
                    );

            transition:
                    transform 0.25s,
                    box-shadow 0.25s;
        }

        .card:hover {

            transform:
                    translateY(-6px);

            box-shadow:
                    0 15px 35px
                    rgba(
                            180,
                            70,
                            110,
                            0.15
                    );
        }

        .icon {

            width: 55px;

            height: 55px;

            display: flex;

            align-items: center;

            justify-content: center;

            border-radius: 15px;

            background:
                    #fff0f5;

            font-size: 27px;

            margin-bottom: 18px;
        }

        .card h3 {

            margin: 0 0 8px;

            color: #c94f7c;

            font-size: 19px;
        }

        .card p {

            margin: 0;

            color: #999;

            line-height: 1.5;

            font-size: 14px;
        }

        /* ==============================
           FOOTER
           ============================== */

        .footer {

            text-align: center;

            padding: 30px;

            color: #aaa;

            font-size: 13px;
        }

        /* ==============================
           RESPONSIVE
           ============================== */

        @media (max-width: 850px) {

            .grid {

                grid-template-columns:
                        repeat(
                                2,
                                1fr
                        );
            }
        }

        @media (max-width: 550px) {

            .grid {

                grid-template-columns: 1fr;
            }

            .header {

                padding: 0 20px;
            }

            .container {

                padding:
                        35px 20px;
            }
        }

    </style>

</head>

<body>

<!-- ==============================
     HEADER
     ============================== -->

<header class="header">

    <div class="logo">

        <div class="logo-icon">
            M
        </div>

        <span>
            Hệ thống quản lý
        </span>

    </div>

    <a
            class="logout"
            href="${pageContext.request.contextPath}/logout"
    >
        Đăng xuất
    </a>

</header>


<!-- ==============================
     MAIN
     ============================== -->

<main class="container">

    <div class="welcome">

        <h1>
            Xin chào, Admin! 👋
        </h1>

        <p>
            Chào mừng bạn đến với hệ thống quản lý CRUD MVC.
            Hãy chọn chức năng bạn muốn sử dụng.
        </p>

    </div>


    <div class="grid">


        <!-- SINH VIÊN -->

        <a
                class="card"
                href="${pageContext.request.contextPath}/sinhvien"
        >

            <div class="icon">
                👨‍🎓
            </div>

            <h3>
                Quản lý sinh viên
            </h3>

            <p>
                Thêm, sửa, xóa và xem
                thông tin sinh viên.
            </p>

        </a>


        <!-- SÁCH -->

        <a
                class="card"
                href="${pageContext.request.contextPath}/sach"
        >

            <div class="icon">
                📚
            </div>

            <h3>
                Quản lý sách
            </h3>

            <p>
                Quản lý thông tin sách,
                tác giả và nhà xuất bản.
            </p>

        </a>


        <!-- SẢN PHẨM -->

        <a
                class="card"
                href="${pageContext.request.contextPath}/sanpham"
        >

            <div class="icon">
                🛍️
            </div>

            <h3>
                Quản lý sản phẩm
            </h3>

            <p>
                Quản lý danh sách sản phẩm
                trong hệ thống.
            </p>

        </a>


        <!-- LỚP HỌC -->

        <a
                class="card"
                href="${pageContext.request.contextPath}/lophoc"
        >

            <div class="icon">
                🏫
            </div>

            <h3>
                Quản lý lớp học
            </h3>

            <p>
                Quản lý lớp học và
                thông tin liên quan.
            </p>

        </a>


        <!-- ĐIỂM -->

        <a
                class="card"
                href="${pageContext.request.contextPath}/diem"
        >

            <div class="icon">
                📊
            </div>

            <h3>
                Quản lý điểm
            </h3>

            <p>
                Theo dõi và quản lý
                điểm của sinh viên.
            </p>

        </a>


        <!-- GIỎ HÀNG -->

        <a
                class="card"
                href="${pageContext.request.contextPath}/giohang"
        >

            <div class="icon">
                🛒
            </div>

            <h3>
                Giỏ hàng
            </h3>

            <p>
                Xem và quản lý các
                sản phẩm trong giỏ hàng.
            </p>

        </a>


    </div>

</main>


<!-- ==============================
     FOOTER
     ============================== -->

<footer class="footer">

    Lab 07 - CRUD MVC
    <br>

    Java Web • Servlet • JSP • MVC

</footer>

</body>

</html>