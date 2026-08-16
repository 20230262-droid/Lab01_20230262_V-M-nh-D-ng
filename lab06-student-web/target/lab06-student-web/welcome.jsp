<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String username =
            (String) session.getAttribute("username");

    String role =
            (String) session.getAttribute("role");

    /*
     * Nếu chưa đăng nhập thì quay về login
     */
    if (username == null || role == null) {

        response.sendRedirect(
                request.getContextPath() + "/login.jsp"
        );

        return;
    }

    /*
     * Lấy ký tự đầu tiên của username để làm avatar
     */
    String avatarLetter = username.substring(0, 1).toUpperCase();
%>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Student Hub - Dashboard</title>


    <style>

        * {
            box-sizing: border-box;
        }


        html,
        body {
            margin: 0;
            padding: 0;
            min-height: 100%;
        }


        body {

            font-family:
                    "Segoe UI",
                    Arial,
                    sans-serif;

            background: #fff7fa;

            color: #403746;
        }


        /* =========================================
           LAYOUT
           ========================================= */

        .layout {

            display: flex;

            min-height: 100vh;
        }


        /* =========================================
           SIDEBAR
           ========================================= */

        .sidebar {

            width: 250px;

            min-height: 100vh;

            background:
                    linear-gradient(
                            180deg,
                            #ffffff 0%,
                            #fff5fa 100%
                    );

            border-right:
                    1px solid #f3dce8;

            padding: 25px 18px;

            display: flex;

            flex-direction: column;
        }


        /* BRAND */

        .brand {

            display: flex;

            align-items: center;

            gap: 12px;

            padding:
                    5px 10px 30px;
        }


        .brand-icon {

            width: 45px;

            height: 45px;

            display: flex;

            align-items: center;

            justify-content: center;

            border-radius: 14px;

            background:
                    linear-gradient(
                            135deg,
                            #f9a8d4,
                            #c4b5fd
                    );

            color: white;

            font-size: 22px;

            box-shadow:
                    0 8px 20px
                    rgba(225, 125, 175, .18);
        }


        .brand-name {

            font-size: 20px;

            font-weight: 800;

            color: #4a4050;
        }


        .brand-name span {

            color: #e56da9;
        }


        /* MENU */

        .menu-title {

            padding:
                    0 12px 8px;

            font-size: 11px;

            color: #b49aaa;

            text-transform: uppercase;

            letter-spacing: 1px;

            font-weight: 700;
        }


        .menu-item {

            display: flex;

            align-items: center;

            gap: 12px;

            padding:
                    12px 14px;

            margin-bottom: 6px;

            border-radius: 12px;

            color: #766b78;

            text-decoration: none;

            font-size: 14px;

            transition:
                    all .2s ease;
        }


        .menu-item:hover {

            background: #fce7f3;

            color: #d45b99;

            transform:
                    translateX(2px);
        }


        .menu-item.active {

            background:
                    linear-gradient(
                            90deg,
                            #fce7f3,
                            #f8efff
                    );

            color: #d45b99;

            font-weight: 700;

            box-shadow:
                    inset 3px 0 0 #ec6faf;
        }


        .menu-icon {

            width: 22px;

            text-align: center;

            font-size: 17px;
        }


        .sidebar-bottom {

            margin-top: auto;
        }


        /* =========================================
           MAIN
           ========================================= */

        .main {

            flex: 1;

            min-width: 0;

            padding:
                    28px 35px;
        }


        /* =========================================
           TOPBAR
           ========================================= */

        .topbar {

            display: flex;

            justify-content: space-between;

            align-items: center;

            margin-bottom: 30px;
        }


        .topbar-title h1 {

            margin: 0;

            font-size: 27px;

            color: #403746;

            letter-spacing: -.5px;
        }


        .topbar-title p {

            margin:
                    6px 0 0;

            color: #9a8e9b;

            font-size: 13px;
        }


        /* PROFILE */

        .profile {

            display: flex;

            align-items: center;

            gap: 12px;

            padding:
                    7px 12px 7px 7px;

            background: white;

            border:
                    1px solid #f3dfe9;

            border-radius: 15px;

            box-shadow:
                    0 5px 18px
                    rgba(190, 120, 160, .05);
        }


        .avatar {

            width: 43px;

            height: 43px;

            border-radius: 50%;

            display: flex;

            align-items: center;

            justify-content: center;

            background:
                    linear-gradient(
                            135deg,
                            #f9a8d4,
                            #c4b5fd
                    );

            color: white;

            font-weight: 700;

            font-size: 16px;
        }


        .profile-info strong {

            display: block;

            font-size: 14px;

            color: #4a4050;
        }


        .profile-info span {

            display: block;

            margin-top: 2px;

            font-size: 11px;

            color: #a08f9d;

            text-transform: uppercase;
        }


        /* =========================================
           WELCOME CARD
           ========================================= */

        .welcome-card {

            padding: 32px;

            border-radius: 22px;

            background:
                    linear-gradient(
                            120deg,
                            #f9a8d4 0%,
                            #e3b5ef 45%,
                            #c4b5fd 100%
                    );

            color: white;

            box-shadow:
                    0 15px 35px
                    rgba(215, 130, 180, .20);

            position: relative;

            overflow: hidden;
        }


        .welcome-card::before {

            content: "";

            position: absolute;

            width: 220px;

            height: 220px;

            right: -70px;

            top: -100px;

            border-radius: 50%;

            background:
                    rgba(255,255,255,.18);
        }


        .welcome-card::after {

            content: "🌸";

            position: absolute;

            right: 55px;

            bottom: -20px;

            font-size: 110px;

            opacity: .20;
        }


        .welcome-card h2 {

            margin: 0 0 8px;

            font-size: 26px;

            position: relative;

            z-index: 2;
        }


        .welcome-card p {

            margin: 0;

            color:
                    rgba(255,255,255,.92);

            font-size: 14px;

            position: relative;

            z-index: 2;
        }


        /* =========================================
           STATS
           ========================================= */

        .stats {

            display: grid;

            grid-template-columns:
                repeat(3, 1fr);

            gap: 18px;

            margin-top: 22px;
        }


        .stat-card {

            min-height: 150px;

            background: white;

            border:
                    1px solid #f4e1eb;

            border-radius: 18px;

            padding: 22px;

            box-shadow:
                    0 8px 25px
                    rgba(190,120,160,.06);

            transition:
                    all .2s ease;

            position: relative;

            overflow: hidden;
        }


        .stat-card::after {

            content: "";

            position: absolute;

            width: 80px;

            height: 80px;

            border-radius: 50%;

            right: -30px;

            bottom: -30px;

            background:
                    #fce7f3;

            opacity: .65;
        }


        .stat-card:hover {

            transform:
                    translateY(-3px);

            box-shadow:
                    0 14px 30px
                    rgba(190,120,160,.10);
        }


        .stat-top {

            display: flex;

            justify-content: space-between;

            align-items: center;

            position: relative;

            z-index: 2;
        }


        .stat-label {

            font-size: 12px;

            color: #a08f9d;

            font-weight: 600;

            letter-spacing: .4px;
        }


        .stat-icon {

            width: 43px;

            height: 43px;

            display: flex;

            align-items: center;

            justify-content: center;

            border-radius: 13px;

            background:
                    linear-gradient(
                            135deg,
                            #fce7f3,
                            #f3edff
                    );

            font-size: 20px;

            position: relative;

            z-index: 2;
        }


        .stat-card h3 {

            margin:
                    17px 0 4px;

            font-size: 30px;

            color: #42394a;

            position: relative;

            z-index: 2;
        }


        .stat-card p {

            margin: 0;

            color: #9c909c;

            font-size: 13px;

            position: relative;

            z-index: 2;
        }


        /* COUNT LOADING */

        #studentCount {

            transition:
                    opacity .2s ease;
        }


        /* =========================================
           SECTION
           ========================================= */

        .section {

            margin-top: 27px;
        }


        .section-title {

            font-size: 17px;

            font-weight: 700;

            margin-bottom: 15px;

            color: #4a4050;
        }


        /* =========================================
           QUICK ACTIONS
           ========================================= */

        .quick-actions {

            display: grid;

            grid-template-columns:
                repeat(2, 1fr);

            gap: 15px;
        }


        .action {

            background: white;

            border:
                    1px solid #f4e1eb;

            padding: 20px;

            border-radius: 16px;

            text-decoration: none;

            color: #514654;

            transition:
                    all .2s ease;

            position: relative;

            overflow: hidden;
        }


        .action::after {

            content: "";

            position: absolute;

            width: 90px;

            height: 90px;

            border-radius: 50%;

            right: -35px;

            bottom: -45px;

            background:
                    #fce7f3;

            opacity: .5;
        }


        .action:hover {

            transform:
                    translateY(-3px);

            box-shadow:
                    0 10px 25px
                    rgba(190,120,160,.12);

            border-color:
                    #f3b1d1;
        }


        .action-icon {

            font-size: 25px;

            margin-bottom: 10px;

            position: relative;

            z-index: 2;
        }


        .action strong {

            display: block;

            margin-bottom: 4px;

            position: relative;

            z-index: 2;
        }


        .action span {

            color: #9b909d;

            font-size: 12px;

            position: relative;

            z-index: 2;
        }


        /* =========================================
           REFRESH INFO
           ========================================= */

        .update-info {

            margin-top: 18px;

            text-align: right;

            color: #b19eab;

            font-size: 11px;
        }


        /* =========================================
           RESPONSIVE
           ========================================= */

        @media (max-width: 950px) {

            .stats {

                grid-template-columns:
                    1fr 1fr;
            }
        }


        @media (max-width: 800px) {

            .sidebar {

                width: 75px;

                padding:
                        25px 10px;
            }


            .brand-name,
            .menu-title,
            .menu-item span {

                display: none;
            }


            .brand {

                justify-content: center;

                padding-left: 0;

                padding-right: 0;
            }


            .menu-item {

                justify-content: center;
            }


            .main {

                padding:
                        22px 20px;
            }
        }


        @media (max-width: 650px) {

            .stats {

                grid-template-columns:
                    1fr;
            }


            .quick-actions {

                grid-template-columns:
                    1fr;
            }


            .profile-info {

                display: none;
            }


            .welcome-card {

                padding: 25px;
            }


            .welcome-card h2 {

                font-size: 22px;
            }
        }

    </style>

</head>


<body>


<div class="layout">


    <!-- =====================================
         SIDEBAR
         ===================================== -->

    <aside class="sidebar">


        <div class="brand">

            <div class="brand-icon">
                🌸
            </div>


            <div class="brand-name">

                Student<span>Hub</span>

            </div>

        </div>


        <div class="menu-title">
            Menu
        </div>


        <!-- Dashboard -->

        <a class="menu-item active"
           href="<%= request.getContextPath() %>/welcome.jsp">

            <span class="menu-icon">
                🏠
            </span>

            <span>
                Dashboard
            </span>

        </a>


        <!-- Students -->

        <a class="menu-item"
           href="<%= request.getContextPath() %>/students">

            <span class="menu-icon">
                👨‍🎓
            </span>

            <span>
                Sinh viên
            </span>

        </a>


        <!-- Logout -->

        <div class="sidebar-bottom">

            <a class="menu-item"
               href="<%= request.getContextPath() %>/logout">

                <span class="menu-icon">
                    ↪
                </span>

                <span>
                    Đăng xuất
                </span>

            </a>

        </div>

    </aside>


    <!-- =====================================
         MAIN
         ===================================== -->

    <main class="main">


        <!-- TOPBAR -->

        <div class="topbar">


            <div class="topbar-title">

                <h1>
                    Dashboard
                </h1>


                <p>
                    Tổng quan hệ thống quản lý sinh viên
                </p>

            </div>


            <div class="profile">


                <div class="avatar">

                    <%= avatarLetter %>

                </div>


                <div class="profile-info">

                    <strong>
                        <%= username %>
                    </strong>


                    <span>
                        <%= role %>
                    </span>

                </div>

            </div>

        </div>


        <!-- =====================================
             WELCOME
             ===================================== -->

        <div class="welcome-card">


            <h2>

                Xin chào,
                <%= username %>
                👋

            </h2>


            <p>

                Chúc bạn một ngày làm việc hiệu quả.
                Hệ thống đang hoạt động bình thường.

            </p>

        </div>


        <!-- =====================================
             STATISTICS
             ===================================== -->

        <div class="stats">


            <!-- TOTAL STUDENTS -->

            <div class="stat-card">


                <div class="stat-top">


                    <div class="stat-label">

                        TOTAL

                    </div>


                    <div class="stat-icon">

                        👨‍🎓

                    </div>

                </div>


                <!--
                    KHÔNG ĐỂ 05 CỐ ĐỊNH NỮA.

                    JavaScript bên dưới sẽ lấy
                    số sinh viên thật từ /students.
                -->

                <h3 id="studentCount">
                    ...
                </h3>


                <p>
                    Sinh viên hiện có
                </p>

            </div>


            <!-- ROLE -->

            <div class="stat-card">


                <div class="stat-top">


                    <div class="stat-label">

                        ACCOUNT

                    </div>


                    <div class="stat-icon">

                        🔐

                    </div>

                </div>


                <h3 style="font-size:22px;">

                    <%= role %>

                </h3>


                <p>
                    Quyền tài khoản
                </p>

            </div>


            <!-- STATUS -->

            <div class="stat-card">


                <div class="stat-top">


                    <div class="stat-label">

                        STATUS

                    </div>


                    <div class="stat-icon">

                        ✨

                    </div>

                </div>


                <h3
                        style="
                        font-size:22px;
                        color:#e56da9;
                    ">

                    ACTIVE

                </h3>


                <p>
                    Hệ thống đang hoạt động
                </p>

            </div>


        </div>


        <!-- =====================================
             QUICK ACTIONS
             ===================================== -->

        <div class="section">


            <div class="section-title">

                Truy cập nhanh

            </div>


            <div class="quick-actions">


                <!-- MANAGE STUDENTS -->

                <a class="action"
                   href="<%= request.getContextPath() %>/students">


                    <div class="action-icon">

                        👨‍🎓

                    </div>


                    <strong>

                        Quản lý sinh viên

                    </strong>


                    <span>

                        Xem, tìm kiếm và quản lý
                        sinh viên trong hệ thống

                    </span>

                </a>


                <!-- ADD STUDENT -->

                <a class="action"
                   href="<%= request.getContextPath() %>/students?action=add">


                    <div class="action-icon">

                        ✨

                    </div>


                    <strong>

                        Thêm sinh viên

                    </strong>


                    <span>

                        Thêm một sinh viên mới
                        vào hệ thống

                    </span>

                </a>


            </div>


            <div class="update-info"
                 id="updateInfo">

                Đang tải dữ liệu...

            </div>


        </div>


    </main>

</div>


<!-- =========================================
     JAVASCRIPT
     CẬP NHẬT SỐ LƯỢNG SINH VIÊN THỰC TẾ
     ========================================= -->

<script>

    async function updateStudentCount() {

        const countElement =
            document.getElementById("studentCount");

        const updateInfo =
            document.getElementById("updateInfo");


        try {

            /*
             * Gọi đúng URL:
             *
             * /lab06-student-web/students
             *
             * request.getContextPath()
             * sẽ tự lấy:
             *
             * /lab06-student-web
             */

            const url =
                "<%= request.getContextPath() %>/students";


            const response =
                await fetch(
                    url,
                    {
                        method: "GET",

                        credentials: "same-origin",

                        cache: "no-store"
                    }
                );


            if (!response.ok) {

                throw new Error(
                    "HTTP " + response.status
                );
            }


            /*
             * Lấy HTML trả về từ student-list.jsp
             */

            const html =
                await response.text();


            /*
             * Chuyển HTML thành DOM
             */

            const parser =
                new DOMParser();


            const doc =
                parser.parseFromString(
                    html,
                    "text/html"
                );


            /*
             * Tìm bảng sinh viên
             */

            const table =
                doc.querySelector(
                    "table"
                );


            let count = 0;


            if (table) {

                /*
                 * Đếm các dòng trong tbody.
                 *
                 * student-list.jsp:
                 *
                 * <tbody>
                 *     <tr>...</tr>
                 *     <tr>...</tr>
                 * </tbody>
                 */

                const rows =
                    table.querySelectorAll(
                        "tbody tr"
                    );


                count =
                    rows.length;
            }


            /*
             * Hiển thị số lượng.
             *
             * Ví dụ:
             *
             * 5  -> 05
             * 6  -> 06
             * 10 -> 10
             */

            countElement.textContent =
                String(count).padStart(2, "0");


            /*
             * Hiển thị thời gian cập nhật
             */

            const now =
                new Date();


            const time =
                now.toLocaleTimeString(
                    "vi-VN",
                    {
                        hour: "2-digit",
                        minute: "2-digit"
                    }
                );


            updateInfo.textContent =
                "Dữ liệu cập nhật lúc " + time;


        } catch (error) {


            console.error(
                "Lỗi cập nhật số lượng sinh viên:",
                error
            );


            /*
             * Không để dashboard bị lỗi
             */

            countElement.textContent =
                "--";


            updateInfo.textContent =
                "Không thể tải dữ liệu sinh viên";

        }

    }


    /*
     * Chạy ngay khi Dashboard mở
     */

    updateStudentCount();

</script>


</body>

</html>