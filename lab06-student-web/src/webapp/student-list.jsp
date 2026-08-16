<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="vn.edu.eaut.lab6.model.Student" %>

<%
    String username =
            (String) session.getAttribute("username");

    String role =
            (String) session.getAttribute("role");

    if (username == null || role == null) {

        response.sendRedirect(
                request.getContextPath() + "/login.jsp"
        );

        return;
    }

    List<Student> students =
            (List<Student>) request.getAttribute("students");

    String keyword =
            (String) request.getAttribute("keyword");

    String success =
            (String) request.getAttribute("success");

    String error =
            (String) request.getAttribute("error");

    boolean isAdmin =
            "ADMIN".equals(role);
%>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Student Hub - Sinh viên</title>


    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: "Segoe UI", Arial, sans-serif;
            background: #fff7fa;
            color: #433846;
        }

        .layout {
            min-height: 100vh;
            display: flex;
        }

        /* SIDEBAR */

        .sidebar {
            width: 235px;
            background: white;
            border-right: 1px solid #f3dce8;
            padding: 25px 16px;
            display: flex;
            flex-direction: column;
        }

        .brand {
            display: flex;
            align-items: center;
            gap: 11px;
            padding: 5px 10px 30px;
        }

        .brand-icon {
            width: 43px;
            height: 43px;
            border-radius: 13px;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(
                    135deg,
                    #f9a8d4,
                    #c4b5fd
            );
            color: white;
            font-size: 21px;
        }

        .brand-name {
            font-size: 19px;
            font-weight: 800;
        }

        .brand-name span {
            color: #e56da9;
        }

        .menu-title {
            font-size: 10px;
            color: #b39aaa;
            text-transform: uppercase;
            letter-spacing: 1px;
            padding: 0 12px 8px;
        }

        .menu-item {
            display: flex;
            align-items: center;
            gap: 11px;
            padding: 12px 13px;
            margin-bottom: 5px;
            border-radius: 11px;
            color: #766b78;
            text-decoration: none;
            font-size: 13px;
        }

        .menu-item:hover,
        .menu-item.active {
            background: #fce7f3;
            color: #d45b99;
        }

        .sidebar-bottom {
            margin-top: auto;
        }

        /* MAIN */

        .main {
            flex: 1;
            padding: 28px 35px;
            min-width: 0;
        }

        .topbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 28px;
        }

        .title h1 {
            margin: 0;
            font-size: 25px;
        }

        .title p {
            margin: 6px 0 0;
            color: #9c909c;
            font-size: 13px;
        }

        .user {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background: linear-gradient(
                    135deg,
                    #f9a8d4,
                    #c4b5fd
            );
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            font-weight: bold;
        }

        .user small {
            color: #9c909c;
        }

        /* CONTENT CARD */

        .card {
            background: white;
            border: 1px solid #f3e0ea;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(190,120,160,.06);
            overflow: hidden;
        }

        .card-header {
            padding: 23px 25px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 15px;
            border-bottom: 1px solid #f5e6ed;
        }

        .card-title h2 {
            margin: 0;
            font-size: 18px;
        }

        .card-title p {
            margin: 5px 0 0;
            color: #a0929e;
            font-size: 12px;
        }

        /* BUTTON */

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            gap: 6px;
            border: none;
            border-radius: 10px;
            padding: 10px 15px;
            text-decoration: none;
            cursor: pointer;
            font-size: 12px;
            font-weight: 600;
            transition: .2s;
        }

        .btn-primary {
            background: linear-gradient(
                    135deg,
                    #ec6faf,
                    #c4a7f8
            );
            color: white;
            box-shadow: 0 7px 18px rgba(224,115,170,.18);
        }

        .btn-primary:hover {
            transform: translateY(-1px);
        }

        .btn-light {
            background: #fff4f9;
            color: #c65d91;
            border: 1px solid #f3d9e7;
        }

        .btn-warning {
            background: #f3edff;
            color: #8065c9;
        }

        .btn-danger {
            background: #fff1f2;
            color: #d94f6a;
        }

        /* ALERT */

        .alert {
            margin: 20px 25px 0;
            padding: 12px 15px;
            border-radius: 11px;
            font-size: 13px;
        }

        .success {
            background: #f0fdf4;
            color: #15803d;
            border: 1px solid #bbf7d0;
        }

        .error {
            background: #fff1f2;
            color: #be123c;
            border: 1px solid #fecdd3;
        }

        /* SEARCH */

        .search-area {
            padding: 20px 25px;
            background: #fffafd;
            border-bottom: 1px solid #f5e6ed;
        }

        .search-form {
            display: flex;
            gap: 9px;
        }

        .search-input {
            flex: 1;
            height: 43px;
            border: 1px solid #eadde7;
            border-radius: 10px;
            padding: 0 14px;
            font-size: 13px;
            background: white;
        }

        .search-input:focus {
            outline: none;
            border-color: #f0a0c8;
            box-shadow: 0 0 0 3px rgba(249,168,212,.14);
        }

        /* TABLE */

        .table-wrap {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 780px;
        }

        th {
            background: #fff5fa;
            color: #9b7187;
            font-size: 11px;
            text-transform: uppercase;
            letter-spacing: .5px;
            padding: 14px 16px;
            text-align: left;
            border-bottom: 1px solid #f2e1ea;
        }

        td {
            padding: 15px 16px;
            border-bottom: 1px solid #f5e9ef;
            font-size: 13px;
        }

        tr:hover td {
            background: #fffafd;
        }

        .student-id {
            font-weight: 700;
            color: #d35f98;
        }

        .student-name {
            font-weight: 600;
            color: #4d4350;
        }

        .class-badge {
            display: inline-block;
            padding: 5px 9px;
            border-radius: 8px;
            background: #f3edff;
            color: #8065c9;
            font-size: 11px;
            font-weight: 600;
        }

        .email {
            color: #8d8190;
        }

        .actions {
            display: flex;
            gap: 6px;
        }

        .empty {
            padding: 60px 20px;
            text-align: center;
            color: #a397a1;
        }

        .empty-icon {
            font-size: 42px;
            margin-bottom: 12px;
        }

        .footer {
            padding: 16px 25px;
            color: #9c909c;
            font-size: 12px;
        }

        @media (max-width: 800px) {

            .sidebar {
                width: 70px;
            }

            .brand-name,
            .menu-title,
            .menu-item span {
                display: none;
            }

            .brand {
                justify-content: center;
            }

            .menu-item {
                justify-content: center;
            }

            .main {
                padding: 20px;
            }
        }

    </style>

</head>

<body>

<div class="layout">

    <!-- SIDEBAR -->

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


        <a class="menu-item"
           href="<%= request.getContextPath() %>/welcome.jsp">

            <span>🏠</span>
            <span>Dashboard</span>

        </a>


        <a class="menu-item active"
           href="<%= request.getContextPath() %>/students">

            <span>👨‍🎓</span>
            <span>Sinh viên</span>

        </a>


        <div class="sidebar-bottom">

            <a class="menu-item"
               href="<%= request.getContextPath() %>/logout">

                <span>↪</span>
                <span>Đăng xuất</span>

            </a>

        </div>

    </aside>


    <!-- MAIN -->

    <main class="main">

        <div class="topbar">

            <div class="title">

                <h1>Quản lý sinh viên</h1>

                <p>
                    Danh sách và thông tin sinh viên
                </p>

            </div>


            <div class="user">

                <div class="avatar">
                    <%= username.substring(0,1).toUpperCase() %>
                </div>

                <div>

                    <strong>
                        <%= username %>
                    </strong>

                    <br>

                    <small>
                        <%= role %>
                    </small>

                </div>

            </div>

        </div>


        <div class="card">


            <!-- HEADER -->

            <div class="card-header">

                <div class="card-title">

                    <h2>Danh sách sinh viên</h2>

                    <p>
                        Quản lý thông tin sinh viên trong hệ thống
                    </p>

                </div>


                <div>

                    <% if (isAdmin) { %>

                    <a class="btn btn-primary"
                       href="<%= request.getContextPath() %>/students?action=add">

                        ＋ Thêm sinh viên

                    </a>

                    <% } %>

                </div>

            </div>


            <!-- ALERT -->

            <% if (success != null && !success.isBlank()) { %>

            <div class="alert success">
                ✓ <%= success %>
            </div>

            <% } %>


            <% if (error != null && !error.isBlank()) { %>

            <div class="alert error">
                ⚠ <%= error %>
            </div>

            <% } %>


            <!-- SEARCH -->

            <div class="search-area">

                <form class="search-form"
                      method="get"
                      action="<%= request.getContextPath() %>/students">

                    <input class="search-input"
                           type="text"
                           name="keyword"
                           value="<%= keyword != null ? keyword : "" %>"
                           placeholder="🔎  Tìm theo mã, tên, lớp hoặc email...">

                    <button class="btn btn-primary"
                            type="submit">

                        Tìm kiếm

                    </button>

                    <a class="btn btn-light"
                       href="<%= request.getContextPath() %>/students">

                        Xóa

                    </a>

                </form>

            </div>


            <!-- TABLE -->

            <% if (students != null && !students.isEmpty()) { %>

            <div class="table-wrap">

                <table>

                    <thead>

                    <tr>

                        <th>STT</th>
                        <th>Mã SV</th>
                        <th>Họ tên</th>
                        <th>Lớp</th>
                        <th>Email</th>

                        <% if (isAdmin) { %>
                        <th>Thao tác</th>
                        <% } %>

                    </tr>

                    </thead>


                    <tbody>

                    <%
                        int stt = 1;

                        for (Student student : students) {
                    %>

                    <tr>

                        <td>
                            <%= stt++ %>
                        </td>


                        <td>

                                <span class="student-id">
                                    <%= student.getId() %>
                                </span>

                        </td>


                        <td>

                                <span class="student-name">
                                    <%= student.getName() %>
                                </span>

                        </td>


                        <td>

                                <span class="class-badge">
                                    <%= student.getClassName() %>
                                </span>

                        </td>


                        <td>

                                <span class="email">
                                    <%= student.getEmail() %>
                                </span>

                        </td>


                        <% if (isAdmin) { %>

                        <td>

                            <div class="actions">

                                <a class="btn btn-warning"
                                   href="<%= request.getContextPath() %>/students?action=edit&id=<%= student.getId() %>">

                                    Sửa

                                </a>


                                <form method="post"
                                      action="<%= request.getContextPath() %>/students"
                                      onsubmit="return confirm('Bạn có chắc muốn xóa sinh viên này?');">

                                    <input type="hidden"
                                           name="action"
                                           value="delete">

                                    <input type="hidden"
                                           name="id"
                                           value="<%= student.getId() %>">

                                    <button type="submit"
                                            class="btn btn-danger">

                                        Xóa

                                    </button>

                                </form>

                            </div>

                        </td>

                        <% } %>

                    </tr>

                    <% } %>

                    </tbody>

                </table>

            </div>

            <% } else { %>

            <div class="empty">

                <div class="empty-icon">
                    🌸
                </div>

                <strong>
                    Không tìm thấy sinh viên
                </strong>

                <p>
                    Hãy thử từ khóa tìm kiếm khác.
                </p>

            </div>

            <% } %>


            <div class="footer">

                Tổng số:
                <strong>
                    <%= students != null ? students.size() : 0 %>
                </strong>
                sinh viên

            </div>

        </div>

    </main>

</div>

</body>
</html>